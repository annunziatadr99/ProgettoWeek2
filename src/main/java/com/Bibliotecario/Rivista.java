package com.Bibliotecario;

public class Rivista extends CatalogoArticolo {
    public enum Periodicità{
        Settimanale,Mensile,Semestrale
    }
    private Periodicità periodicità;

    public Rivista(String isbn, String titolo, int annoDiPubblicazione, int numeroDiPagine, Periodicità periodicità) {
        super(isbn, titolo, annoDiPubblicazione, numeroDiPagine);
    }

    public Periodicità getPeriodicità(){
        return periodicità;
    }

    public String toString(){
        return super.toString() + "Periodicità: " +  periodicità;
    }
}
