package com.Bibliotecario;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {

    private static final Logger logger = LoggerFactory.getLogger(Archivio.class);
    private static final Scanner scanner = new Scanner(System.in);
    private final Map<String,CatalogoArticolo>catalogo;

    public Archivio(){
        catalogo = new HashMap<>();
    }

    public static void main(String[] args ) {
        Archivio archivio = new Archivio();
        while (true){
            archivio.stampaMenu();
            int menu = Integer.parseInt(scanner.nextLine());
            try {
                switch (menu) {
                    case 1:
                        archivio.aggiungiElemento();
                        break;
                    case 2:
                        archivio.ricercaPerIsbn();
                        break;
                    case 3:
                        archivio.rimuoviElemento();
                        break;
                    case 4:
                        archivio.ricercaPerAnno();
                        break;
                    case 5:
                        archivio.ricercaPerAutore();
                        break;
                    case 6:
                        archivio.stampaStatistiche();
                        break;
                    case 0:
                        logger.info("Uscita!");
                    default:
                        logger.error("Scelta non valida!");
                }

            }catch (Exception e){
                logger.error("Errore ", e );
            }
        }
    }

    private void stampaMenu() {
        System.out.println("Gestione Catalogo Bibliotecario:");
        System.out.println("1. Aggiungi Elemento");
        System.out.println("2. Ricerca per ISBN");
        System.out.println("3. Rimuovi Elemento");
        System.out.println("4. Ricerca per Anno");
        System.out.println("5. Ricerca per Autore");
        System.out.println("6. Stampa Statistiche");
        System.out.println("0. Esci");
    }



    private void aggiungiElemento() {
        try {
            logger.info("Aggiungi un elemento al catalogo");
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Titolo: ");
            String titolo = scanner.nextLine();
            System.out.print("Anno di Pubblicazione: ");
            int anno = Integer.parseInt(scanner.nextLine());
            System.out.print("Numero di pagine: ");
            int pagine = Integer.parseInt(scanner.nextLine());
            System.out.print("E' un Libro o una Rivista? Inserisci (L) per Libro - Inserisci (R) per Rivista: ");
            String modello = scanner.nextLine().toUpperCase();

            CatalogoArticolo articolo;
            if (modello.equals("L")) {
                System.out.print("Autore: ");
                String autore = scanner.nextLine();
                System.out.print("Genere: ");
                String genere = scanner.nextLine();
                articolo = new Libro(isbn, titolo, anno, pagine, autore, genere);
            } else if (modello.equals("R")) {
                System.out.print("Periodicità (SETTIMANALE)-(MENSILE)-(SEMESTRALE): ");
                Rivista.Periodicità periodicità = Rivista.Periodicità.valueOf(scanner.nextLine().toUpperCase());
                articolo = new Rivista(isbn, titolo, anno, pagine, periodicità);
            } else {
                logger.error("Qualcosa è andato storto, ARTICOLO NON AGGIUNTO. Ricomincia facendo attenzione nel compilare tutti i campi richiesti.");
                return;
            }
            aggiungiElemento(articolo);
        } catch (IsbnGiàEsistente e) {
            logger.error("Errore: " + e.getMessage());
        }
    }
    public void aggiungiElemento(CatalogoArticolo articolo) throws IsbnGiàEsistente {
        if (catalogo.containsKey(articolo.getIsbn())) {
            throw new IsbnGiàEsistente("Un articolo con ISBN " + articolo.getIsbn() + " già esiste.");
        }
        catalogo.put(articolo.getIsbn(), articolo);
        logger.info("Articolo  con questo  ISBN è stato  aggiunto corretamente!");
    }


    private void ricercaPerIsbn() throws IsbnNonTrovato{
        logger.info("Ricerca un Articolo con il suo ISBN");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        CatalogoArticolo articolo = ricercaPerIsbn(isbn);
        System.out.println("Ricerca:" + articolo);
    }
    public CatalogoArticolo ricercaPerIsbn(String isbn)throws IsbnNonTrovato{
        if (!catalogo.containsKey(isbn)){
            throw new IsbnNonTrovato("Nessun Articolo trovato con ISBN" + isbn);}
        return catalogo.get(isbn);
    }


    private void ricercaPerAnno(){
        logger.info("Ricerca Articolo per Anno");
        System.out.print("Anno: ");
        int anno = Integer.parseInt(scanner.nextLine());
        List<CatalogoArticolo>articolo = ricercaperAnno(anno);
        articolo.forEach(System.out::println);
    }
    public List<CatalogoArticolo>ricercaperAnno(int anno){
        return catalogo.values()
                .stream()
                .filter(articolo ->articolo.getAnnoDiPubblicazione()== anno)
                .collect(Collectors.toList());
    }


    private void ricercaPerAutore() {
        logger.info("Ricerca Articolo per Autore");
        System.out.print("Autore: ");
        String autore = scanner.nextLine().trim().toLowerCase();
        List<Libro> libri = ricercaLibroPerAutore(autore);
        if (libri.isEmpty()) {
            logger.info("Nessun libro trovato per l'autore: " + autore);
        } else {
            libri.forEach(System.out::println);
        }
    }

    public List<Libro>ricercaLibroPerAutore(String autore){
        return catalogo.values()
                .stream()
                .filter(articolo ->articolo instanceof Libro && ((Libro)articolo)
                        .getAutore()
                        .equalsIgnoreCase(autore))
                .map(articolo->(Libro) articolo)
                .collect(Collectors.toList());
    }


    private void rimuoviElemento()throws IsbnNonTrovato{
        logger.info("Rimuovi un Articolo dal Catalogo");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        rimuoviElemento(isbn);
        System.out.println("Articolo Rimosso correttamente!");
    }
    public void rimuoviElemento(String isbn)throws IsbnNonTrovato{
        if (!catalogo.containsKey(isbn)){
            throw new IsbnNonTrovato("Nessun Articolo trovato con ISBN" + isbn);
        }
        catalogo.remove(isbn);
        logger.info("Articolo {} Rimosso correttamente!",isbn);

    }

    public void stampaStatistiche() {
        long numLibri = catalogo.values().stream().filter(articolo -> articolo instanceof Libro).count();
        long numRiviste = catalogo.values().stream().filter(articolo -> articolo instanceof Rivista).count();
        Optional<CatalogoArticolo> maxPagineArticolo = catalogo.values().stream().max(Comparator.comparingInt(CatalogoArticolo::getNumeroDiPagine));
        double avgPagine = catalogo.values().stream().mapToInt(CatalogoArticolo::getNumeroDiPagine).average().orElse(0);

        logger.info("Numero totale di libri: {}", numLibri);
        logger.info("Numero totale di riviste: {}", numRiviste);
        maxPagineArticolo.ifPresent(articolo -> logger.info("Articolo con il maggior numero di pagine: {}", articolo));
        logger.info("Numero medio di pagine: {}", avgPagine);
    }
}


