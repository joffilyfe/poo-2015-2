package interfaces;

import modelos.Carro;
import modelos.Cliente;
import modelos.Locadora;
import aplicacao.Funcionalidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Main {

    public static void main(String[] args) {

//        Locadora l = new Locadora("Unidas");
//        Cliente ultimo = new Cliente("Zebra", "9");
//        Cliente primeiro = new Cliente("Ana", "1");
//        Cliente segundo = new Cliente("Barbosa", "1");
//
//        l.addCliente(segundo);
//        l.addCliente(ultimo);
//        l.addCliente(primeiro);
//
//        System.out.println(l.getClientes());
//
//        System.out.println(Funcionalidade.getClientes());

//        GregorianCalendar hoje = new GregorianCalendar();
//        GregorianCalendar tres = new GregorianCalendar();
//
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");
//
//        try {
//            tres.setTime(formato.parse("30/03/2016 00:00"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//
//        System.out.println( (tres.getTimeInMillis() - hoje.getTimeInMillis()) / (1000 * 60 * 60));


        ArrayList<Carro> carros = new ArrayList<>();

        Carro um = new Carro("primeiro", "1");
        Carro dois = new Carro("segundo", "1");
        Carro tres = new Carro("segundo", "1");

        carros.add(um);
        carros.add(dois);

        System.out.println(carros);
//        carros.remove(0);


        carros.remove(tres);
        System.out.println(carros);


    }
}
