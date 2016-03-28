package modelos;

import java.util.ArrayList;
import java.util.Collections;

public class Locadora {
    private String nome;
    private ArrayList<Carro> carros = new ArrayList<Carro>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Aluguel> alugueis = new ArrayList<>();

    public Locadora(String nome) {
        this.nome = nome;
    }

    public ArrayList<Cliente> getClientes() {
        Collections.sort(this.clientes);
        return this.clientes;
    }

    public void addCliente(Cliente novo) {
        this.clientes.add(novo);
    }

    // Busca um cliente pelo cpf
    public Cliente getCliente(String cpf) {
        for (Cliente c : this.clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }

        return null;
    }

    // Busca carro pela placa
    public Carro getCarro(String placa) {
        for (Carro c : this.carros) {
            if (c.getPlaca().equals(placa)) {
                return c;
            }
        }

        return null;
    }

    // Retorna todos os carros cadastrados na locadora
    public ArrayList<Carro> getCarros() {
        return this.carros;
    }

    // Retorna todos os alugueis realizados na locadora
    public ArrayList<Aluguel> getAlugueis() {
        return this.alugueis;
    }

    public void addAluguel(Aluguel novo) {
        this.alugueis.add(novo);
    }

    public void removeAluguel(Aluguel aluguel) {
        this.alugueis.remove(aluguel);
    }


    public void removeCarro(Carro carro) {
        this.carros.remove(carro);
    }
    public void addCarro(Carro carro) {
        this.carros.add(carro);
    }
}
