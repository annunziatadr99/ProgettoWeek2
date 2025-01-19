package com.Bibliotecario;

public class Rivista extends CatalogoArticolo {
    public enum Periodicità{
        SETTIMANALE,MENSILE,SEMESTRALE
    }
    private Periodicità periodicità;

    public Rivista(String isbn, String titolo, int annoDiPubblicazione, int numeroDiPagine, Periodicità periodicità) {
        super(isbn, titolo, annoDiPubblicazione, numeroDiPagine);
        this.periodicità = periodicità;
    }

    public Periodicità getPeriodicità(){
        return periodicità;
    }

    public String toString(){
        return super.toString() + "Periodicità: " +  periodicità;
    }
}
