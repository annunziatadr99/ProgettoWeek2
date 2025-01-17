package com.Bibliotecario;

public abstract class CatalogoArticolo {
    protected String isbn;
    protected String titolo;
    protected int annoDiPubblicazione;
    protected int numeroDiPagine;


    public CatalogoArticolo(String isbn, String titolo, int annoDiPubblicazione, int numeroDiPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroDiPagine = numeroDiPagine;
    }
    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public int getNumeroDiPagine() {
        return numeroDiPagine;
    }


    public String toString() {
        return "ISBN: " + isbn + ", Titolo: " + titolo + ", Anno: " + annoDiPubblicazione + ", Pagine: " + numeroDiPagine;
    }
}
