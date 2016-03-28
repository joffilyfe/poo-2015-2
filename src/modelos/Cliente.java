package modelos;

import java.util.ArrayList;

public class Cliente implements Comparable {
    private String nome;
    private String cpf;
    private ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return cpf;
    }

    // Retorna o último aluguel
    public Aluguel getUltimoAluguel() {
        if (this.alugueis.size() == 0) {
            return null;
        }

        return this.alugueis.get(this.alugueis.size() - 1);
    }

    // Retorna o status do último aluguel
    public boolean temAluguelAtivo() {
        if (this.alugueis.isEmpty())
            return false;

        Aluguel ultimo = this.alugueis.get(alugueis.size() - 1);
        return !ultimo.isFinalizado();
    }

    public void addAluguel(Aluguel novo) {
        this.alugueis.add(novo);
    }

    // Retorna todos os alugueis do cliente
    public ArrayList<Aluguel> getAlugueis() {
        return this.alugueis;
    }

    public void removeAluguel(Aluguel aluguel) {
        this.alugueis.remove(aluguel);
    }

    @Override
    public int compareTo(Object o) {
        String proximoNome = ((Cliente)o).getNome();
        return this.nome.compareTo(proximoNome);
    }

    @Override
    public String toString() {
        return this.nome;

    }
}
