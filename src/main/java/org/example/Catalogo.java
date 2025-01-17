package org.example;

public abstract class Catalogo{
    protected String isbn;
    protected String titolo;
    protected int annoDiPubblicazione;
    protected int numeroDiPagine;


    public Catalogo (String isbn, String titolo, int yearOfPublication, int numberOfPages) {
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
