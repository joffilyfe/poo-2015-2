package modelos;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Aluguel {
    private int id;
    private double valor;
    private boolean finalizado = false;
    private Carro carro;
    private Cliente cliente;
    private GregorianCalendar inicio;
    private GregorianCalendar fim;
    private static int contador = 0;

    public Aluguel(Carro carro, Cliente cliente, double valor, GregorianCalendar inicio, GregorianCalendar fim) {
        this.id = contador++;
        this.carro = carro;
        this.cliente = cliente;
        this.valor = valor;
        this.inicio = inicio;
        this.fim = fim;
    }


    public boolean isFinalizado() {
        return this.finalizado;
    }

    public GregorianCalendar getInicio() {
        return this.inicio;
    }

    public GregorianCalendar getFim() {
        return this.fim;
    }

    public double getValor() {
        return this.valor;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setFinalizado() {
        this.finalizado = true;
    }

    public Carro getCarro() {
        return this.carro;
    }

    public boolean finalizaHoje() {

        GregorianCalendar amanha = new GregorianCalendar();
        GregorianCalendar ontem = new GregorianCalendar();
        ontem.set(Calendar.DAY_OF_MONTH, -1);
        ontem.set(Calendar.HOUR_OF_DAY, 0);
        amanha.add(Calendar.DAY_OF_MONTH, 1);
        amanha.set(Calendar.HOUR_OF_DAY, 0);

        // Se for antes de amanhã e depois de ontem == hoje!
        if (this.fim.before(amanha) || this.fim.after(ontem))
            return true;

        return false;
    }


    @Override
    public String toString() {
        return "Cliente: " + this.cliente + ", Carro: " + this.carro + ", valor: R$ " + this.valor + ", finalizado: " + this.finalizado;
    }
}
