package com.Bibliotecario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Archivio {

    private static final Logger logger = LoggerFactory.getLogger(Archivio.class);
    private static final Scanner scanner = new Scanner(System.in);
    private final Map<String,CatalogoArticolo>catalogo;

    public Archivio(){
        catalogo = new HashMap<>();
    }

    public static void main(String[] args ) {



    }


    private void aggiungiElemento(CatalogoArticolo articolo)throws IsbnGiàEsistente{
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

        CatalogoArticolo Articolo;
        if (modello.equals("L")){
            System.out.print("Autore: ");
            String autore = scanner.nextLine();
            System.out.print("Genere: ");
            String genere = scanner.nextLine();
            Articolo = new Libro(isbn, titolo, anno, pagine, autore, genere);
        } else if (modello.equals("R")) {
            System.out.print("Periodicità (SETTIMANALE)-(MENSILE)-(SEMESTRALE): ");
            Rivista.Periodicità periodicità = Rivista.Periodicità.valueOf(scanner.nextLine().toUpperCase());
            Articolo = new Rivista(isbn, titolo, anno, pagine, periodicità);
        }else {
            logger.error("Qualcosa è andato storto, ARTICOLO NON AGGIUNTO." +
                    " ricomincia facendo attenzione nel compilare tutti i campi richiesti");
            return;
        }
        aggiungiElemento(Articolo);
    }
}


