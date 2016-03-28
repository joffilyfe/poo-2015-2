package modelos;

import java.util.ArrayList;

/**
 * Created by joffily on 26/03/16.
 */
public class Carro {
    private String placa;
    private String modelo;
    private boolean alugado;
    private ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();

    public Carro(String modelo, String placa) {
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getPlaca() {
        return this.placa;
    }

    public boolean isAlugado() {
        return this.alugado;
    }

    public Aluguel getAluguel() {
        if (this.alugueis.isEmpty())
            return null;

        return this.alugueis.get(this.alugueis.size() - 1);
    }

    public void setAlugado() {
        this.alugado = true;
    }

    public void setDevolvido() {
        this.alugado = false;
    }

    public ArrayList<Aluguel> getAlugueis() {
        return this.alugueis;
    }

    public void addAluguel(Aluguel novo) {
        this.alugueis.add(novo);
    }

    public void removeAluguel(Aluguel aluguel) {
        this.alugueis.remove(aluguel);
    }

    @Override
    public String toString() {
        return this.modelo + " - " + this.placa;
    }
}
