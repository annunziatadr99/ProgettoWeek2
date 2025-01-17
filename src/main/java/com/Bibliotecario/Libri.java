package com.Bibliotecario;

public class Libri extends CatalogoArticolo {
    private String autore;
    private String genere;

    public Libri(String isbn, String titolo, int annoDiPubblicazione, int numeroDiPagine) {
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


