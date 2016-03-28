package aplicacao;

import com.sun.tools.corba.se.idl.ExceptionEntry;
import modelos.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Funcionalidade {

    private static Locadora locadora = new Locadora("Unidas");
    private static double valorHora = 3.50;

    public Funcionalidade() {}

    public static ArrayList<Cliente> getClientes() {
        return locadora.getClientes();
    }

    public static Cliente cadastrarCliente(String nome, String cpf) {
        Cliente novo = new Cliente(nome, cpf);
        locadora.addCliente(novo);
        return novo;
    }

    public static ClienteFidelidade cadastrarClienteFidelidade(String nome, String cpf, int numero) {
        ClienteFidelidade novo = new ClienteFidelidade(nome, cpf, numero);
        locadora.addCliente(novo);
        return novo;
    }

    public static Carro cadastrarCarro(String modelo, String placa) {
        Carro novo = new Carro(modelo, placa);
        locadora.addCarro(novo);
        return novo;
    }

    // Responsável por gerar um novo aluguel
    public static Aluguel alugarCarro(String cpf, String placa, Double diaria, GregorianCalendar inicio, GregorianCalendar fim) throws Exception {

        if (cpf.equals(""))
            throw new Exception("O CPF não pode ser vazio");
        if (placa.equals(""))
            throw new Exception("A placa não pode ser vazia");

        Cliente cliente = locadora.getCliente(cpf);

        if (cliente == null)
            throw new Exception("Cliente não encontrado");

        if (cliente.temAluguelAtivo())
            throw new Exception("Este cliente possui um aluguel não finalizado.");

        Carro carro = locadora.getCarro(placa);

        if (carro == null)
            throw new Exception("Este carro não existe");

        if (carro.isAlugado())
            throw new Exception("Este carro está alugado no momento.");

        long horas;
        double valorHoraDiaria = diaria / 24;
        horas = (fim.getTimeInMillis() - inicio.getTimeInMillis()) / (1000 * 60 * 60);
        double valorTotal = valorHoraDiaria * horas;

        Aluguel aluguel = new Aluguel(carro, cliente, valorTotal, inicio, fim);

        // Gravando os dados nos modelos
        carro.setAlugado();
        carro.addAluguel(aluguel);
        cliente.addAluguel(aluguel);
        locadora.addAluguel(aluguel);

        return aluguel;
    }

    // Responsável por fechar um aluguel
    public static void devolverCarro(String placa) throws Exception {
        double horasAlugadas = 0.0;
        double horasAMais = 0.0;
        double horasAMenos = 0.0;

        Carro carro = locadora.getCarro(placa);

        if (carro == null)
            throw new Exception("Este carro não existe.");

        if (!carro.isAlugado())
            throw new Exception("Este carro não esta alugado.");

        Aluguel aluguel = carro.getAluguel();
        horasAlugadas = (aluguel.getFim().getTimeInMillis() - aluguel.getInicio().getTimeInMillis()) / (1000 * 60 * 60);
        double valorHora = aluguel.getValor()/horasAlugadas;

        GregorianCalendar hoje = new GregorianCalendar();

        if (hoje.after(aluguel.getFim())) {
            horasAMais = (aluguel.getFim().getTimeInMillis() - hoje.getTimeInMillis()) / (1000 * 60 * 60);
        }

        if (hoje.before(aluguel.getFim())) {
            horasAMenos = (hoje.getTimeInMillis() - aluguel.getFim().getTimeInMillis()) / (1000 * 60 * 60);
        }

        aluguel.setFinalizado();
        carro.setDevolvido();


        System.out.println(aluguel + "\n" + "Valor R$ " + aluguel.getValor());
        System.out.println("Multa R$ " + horasAMais * valorHora);
        System.out.println("Devolução R$ " + Math.abs(horasAMenos * valorHora));
    }

    // Responsável por imprimir todos os clientes
    public static String listarClientes() {

        ArrayList<Cliente> clientes = locadora.getClientes();
        String saida = "";

        for (Cliente c : clientes) {
            saida += "\n" + c.getNome() + " - CPF: " + c.getCpf();
            if (c instanceof ClienteFidelidade) {
                saida += " Cartão: " + ((ClienteFidelidade)c).getNumeroCartao();
            }
            if (c.getUltimoAluguel() != null) {
                saida += "\n" + c.getUltimoAluguel() + "\n--------------";
            } else {
                saida += "\nSem alugueis..\n--------------";
            }
        }

        return saida;
    }

    // Responsável por listar todos os carros
    public static String listarCarros() {
        ArrayList<Carro> carros = locadora.getCarros();
        String saida = "";

        for (Carro c : carros) {
            saida += "\n";
            saida += c;

            if (c.isAlugado()) {
                saida += "alugado para: " + c.getAluguel().getCliente().getNome();
            }

            saida += "\n--------";
        }

        return saida;
    }

    // Responsável por listar todos os alugueis já finalizados
    public static String listarAlugueisFinalizados() {
        String saida = "";
        ArrayList<Aluguel> alugueis = locadora.getAlugueis();

        for (Aluguel a : alugueis) {
            if (a.isFinalizado()) {
                saida += "\n" + a;
            }
        }

        return saida;
    }

    // Responsável por listar todos os alugueis a serem finalizados hoje
    public static String listarAlugueishoje() {
        String saida = "";

        ArrayList<Aluguel> alugueis = locadora.getAlugueis();

        for (Aluguel a : alugueis) {
            if (a.isFinalizado() == false) {
                if (a.finalizaHoje()) {
                    saida += "\n" + a;
                }
            }
        }

        return saida;
    }


    // Responsável por remover o carro e os alugueis daquele carro
    public static void excluirCarro(String placa) throws Exception {

        Carro carro = locadora.getCarro(placa);

        if (carro == null)
            throw new Exception("Carro não localizado.");

        if (carro.isAlugado())
            throw new Exception("O carro está alugado no momento.");


        for (Aluguel a : carro.getAlugueis()) {
            locadora.removeAluguel(a);
            a.getCliente().removeAluguel(a);
            carro.removeAluguel(a);
        }

        locadora.removeCarro(carro);
    }
}
