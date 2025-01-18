package com.Bibliotecario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Archivio {

    private static final Logger logger = LoggerFactory.getLogger(Archivio.class);
    private static final Scanner scanner = new Scanner(System.in);
    private final Map<String,CatalogoArticolo>catalogo;

    public Archivio(){
        catalogo = new HashMap<>();
    }

    public static void main(String[] args ) {



    }


    private void AggiungiElemento(CatalogoArticolo articolo)throws IsbnGiàEsistente{
        logger.info("Aggiungi un elemento al catalogo");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Anno di Pubblicazione: ");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.print("Numero di pagine: ");
        int pagine = Integer.parseInt(scanner.nextLine());
        System.out.print("E un Libro o una Rivista? Inserisci (L) per Libro - Inserisci (R) per Rivista: ");
        String modello = scanner.nextLine().toUpperCase();

        if (modello.equals("L")){
            System.out.print("Autore: ");
            String autore = scanner.nextLine();
            System.out.print("Genere: ");
            String genere = scanner.nextLine();
            articolo = new Libro(isbn, titolo, anno, pagine, autore, genere);
        } else if (modello.equals("R")) {
            System.out.print("Periodicità (SETTIMANALE)-(MENSILE)-(SEMESTRALE): ");
            Rivista.Periodicità periodicità = Rivista.Periodicità.valueOf(scanner.nextLine().toUpperCase());
            articolo = new Rivista(isbn, titolo, anno, pagine, periodicità);
        }else {
            logger.error("Qualcosa è andato storto, ARTICOLO NON AGGIUNTO." +
                    " ricomincia facendo attenzione nel compilare tutti i campi richiesti");
            return;
        }
        AggiungiElemento(articolo);
    }


    public void RicercaPerIsbn() throws IsbnNonTrovato{
        logger.info("Ricerca un Articolo con il suo ISBN");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        CatalogoArticolo articolo = RicercaPerIsbn(isbn);
        System.out.println("Ricerca:" + articolo);
    }
    public CatalogoArticolo RicercaPerIsbn(String isbn)throws IsbnNonTrovato{
        if (!catalogo.containsKey(isbn)){
            throw new IsbnNonTrovato("Nessun Articolo trovato con ISBN" + isbn);}
        return catalogo.get(isbn);
    }
    public void RicercaPerAnno(){
        logger.info("Ricerca Articolo per Anno");
        System.out.print("Anno: ");
        int anno = Integer.parseInt(scanner.nextLine());
        List<CatalogoArticolo>articolo = RicercaperAnno(anno);
        articolo.forEach(System.out::println);
    }
    public List<CatalogoArticolo>RicercaperAnno(int anno){
        return catalogo.values()
                .stream()
                .filter(articolo ->articolo.getAnnoDiPubblicazione()== anno)
                .collect(Collectors.toList());
    }

    private void RicercaPerAutore(){
        logger.info("Ricerca Articolo per Autore");
        System.out.print("Autore: ");
        String autore = scanner.nextLine();
        List<Libro>libri = RicercaLibroPerAutore(autore);
        libri.forEach(System.out::println);
    }

    public List<Libro>RicercaLibroPerAutore(String autore){
        return catalogo.values()
                .stream()
                .filter(articolo ->articolo instanceof Libro && ((Libro)articolo)
                        .getAutore()
                        .equals(autore))
                .map(articolo->(Libro) articolo)
                .collect(Collectors.toList());
    }

    private void RimuoviElemento()throws IsbnNonTrovato{
        logger.info("Rimuovi un Articolo dal Catalogo");
        System.out.print("ISBN");
        String isbn = scanner.nextLine();
        RimuoviElemento(isbn);
        System.out.println("Articolo Rimosso correttamente!");
    }
    public void RimuoviElemento(String isbn)throws IsbnNonTrovato{
        if (!catalogo.containsKey(isbn)){
            throw new IsbnNonTrovato("Nessun Articolo trovato con ISBN" + isbn);
        }
        catalogo.remove(isbn);
        logger.info("Articolo Rimosso correttamente!");

    }

    private void AggiornaElemento()throws IsbnNonTrovato, IsbnGiàEsistente{
        logger.info("Aggiungi Articolo nel Catalogo");
        System.out.println("ISBN dell'articolo da aggiornare: ");
        String isbn = scanner.nextLine();
        RimuoviElemento(isbn);
        AggiungiElemento();
    }


}


