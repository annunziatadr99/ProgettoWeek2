package com.Bibliotecario;

public class Libro extends CatalogoArticolo {
    private String autore;
    private String genere;

    public Libro(String isbn, String titolo, int annoDiPubblicazione, int numeroDiPagine, String autore, String genere) {
        super(isbn, titolo, annoDiPubblicazione, numeroDiPagine);
    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    public String toString() {
        return super.toString() + ", Autore: " + autore + ", Genere: " + genere;
    }
}


