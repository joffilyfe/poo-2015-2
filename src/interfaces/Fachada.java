package interfaces;

import aplicacao.Funcionalidade;

import java.util.GregorianCalendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Fachada {

    public static void main(String[] args) {

        // Instanciando uma nova fachada
        new Fachada();
    }


    public Fachada() {
        app();
    }

    public void popularLocadora() {
        Funcionalidade.cadastrarClienteFidelidade("Joffily", "1", 45890);
        Funcionalidade.cadastrarCliente("Maria", "2");
        Funcionalidade.cadastrarCliente("Joaquim", "3");
        Funcionalidade.cadastrarCarro("BMW", "A");
        Funcionalidade.cadastrarCarro("AUDI A8", "B");
        Funcionalidade.cadastrarCarro("Jaguar XK8", "C");
    }

    public void menu() {
        System.out.println("- 0: Sair");
        System.out.println("- 1: Alugar carro");
        System.out.println("- 2: Devolver carro");
        System.out.println("- 3: Listar clientes");
        System.out.println("- 4: Listar carros");
        System.out.println("- 5: Listar alugueis finalizados");
        System.out.println("- 6: Listar alugueis a serem finalizados hoje");
        System.out.println("- 7: Excluir carro");
        System.out.print("- Digite uma opção: ");
        System.out.println("");

    }

    public void menu_alugar() {
        System.out.println("---------------------------");
        System.out.println("- Alugar carro");
        System.out.println("---------------------------");
        System.out.println("Digite o CPF: ");
        System.out.println("Digite a placa: ");
        System.out.println("Digite o valor da diária: ");
        System.out.println("Digite a data de retirada: ");
        System.out.println("Digite a data de entrega: ");
    }

    public void menu_devolver() {
        System.out.println("---------------------------");
        System.out.println("- Devolver carro");
        System.out.println("---------------------------");
        System.out.println("Digite a placa: ");
    }

    public void app() {

        int entrada = -1;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Scanner teclado = new Scanner(System.in);

        popularLocadora();

        while (entrada != 0) {

            if (entrada == 1) {
                menu_alugar();

                String cpf = teclado.nextLine();
                String placa = teclado.nextLine();
                String diaria = teclado.nextLine();
                String inicio = teclado.nextLine();
                String fim = teclado.nextLine();
                GregorianCalendar inicioG = new GregorianCalendar();
                GregorianCalendar fimG = new GregorianCalendar();
                Double diariaD;

                try {
                    inicioG.setTime(formato.parse(inicio));
                    fimG.setTime(formato.parse(fim));
                    diariaD = Double.parseDouble(diaria);

                    Funcionalidade.alugarCarro(cpf, placa, diariaD, inicioG, fimG);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (entrada == 2) {
                menu_devolver();
                String placa = teclado.nextLine();

                try {
                    Funcionalidade.devolverCarro(placa);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (entrada == 3) {
                System.out.println(Funcionalidade.listarClientes());
            } else if (entrada == 4) {
                System.out.println(Funcionalidade.listarCarros());
            } else if (entrada == 5) {
                System.out.println(Funcionalidade.listarAlugueisFinalizados());
            } else if (entrada == 6) {
                System.out.println(Funcionalidade.listarAlugueishoje());
            } else if (entrada == 7) {
                String placa = teclado.nextLine();
                try {
                    Funcionalidade.excluirCarro(placa);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            try {
                menu();
                entrada = Integer.parseInt(teclado.nextLine());
            } catch (Exception e) {
                System.out.println("Opção errada, digite um número entre 0 e 7");
            }

        }

        System.out.println("Obrigado por usar nosso sistema, até logo.");
    }
}
