package no.oslomet.cs.algdat;

import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {
    // Innebygd (Trenger ikke endres)

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    private Node<T> hode;
    private Node<T> hale;
    private int antall;
    private int endringer;

    public void fraTilKontroll(int fra, int til) {
        if (fra < 0) throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ.");
        if (til > antall) throw new IndexOutOfBoundsException("til(" + til + ") er større enn antall(" + antall + ")");
        if (fra > til)
            throw new IllegalArgumentException("fra(" + fra + ") er større enn til(" + til + ") - Ulovlig intervall.");
    }

    // Oppgave 0
    public static int gruppeMedlemmer() {
        return 4; // Returner hvor mange som er i gruppa deres
    }

    // Oppgave 1
    public DobbeltLenketListe() {
        antall = 0;
        endringer = 0;
        hode = null;
        hale = null;
    }

    public DobbeltLenketListe(T[] a) {
        endringer++;
        if (a==null) throw new NullPointerException("Array is null");
        for (T t : a) {
            Node<T> nyNode = new Node<>(t);
            if (nyNode.verdi != null) {
                if (tom()) {
                    hode = nyNode;
                } else {
                    hale.neste = nyNode;
                    nyNode.forrige = hale;
                }
                hale = nyNode;
                antall++;
            }
        }
    }

    @Override public int antall() { return antall; }

    @Override public boolean tom() { return antall == 0; }

    // Oppgave 2
    @Override
    public String toString() {
        // Lager ny Stringbuilder object
        StringBuilder sb = new StringBuilder();
        // Legger til klammeparantes ved hjelp av stringbuilder
        sb.append("[");

        // Oppretter en peker som begynner fra starten av lista som vi kaller hode
        Node<T> peker = hode;
        while (peker != null) { // Legger til verdien av den nåværende noden i StringBuilder
            sb.append(peker.verdi);
            // Hvis neste peker ikke er null legges komma og et mellomrom etter verdien og fortsetter til det er ikke neste verdi
            if (peker.neste != null) {
                sb.append(", ");
            }
            // Oppdaterer peker til neste node i listen
            peker = peker.neste;
        }
        // Lukker klemmeparantesen ved hjelp av stringbuilder
        sb.append("]");
        return sb.toString();
    }

    public String omvendtString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        //oppretter en peker som peker siste verdien i listen som vi kaller hale
        Node<T> peker = hale;
        //opretter en løkke som itererer
        while (peker != null) {
            sb.append(peker.verdi);//legger til verdien av den nåværende noden i StringBuilder

            if (peker.forrige != null) {//hvis noden ikke er tom vil stringbuilder legge til komma fordi den går videre
                sb.append(", ");        // til forrige noden
            }
            peker = peker.forrige;
        }

        sb.append("]");
        return sb.toString();
    }

    @Override public boolean leggInn(T verdi) {
        //hvis veriden er null kaster den feilmelding
        if (verdi == null) {
            throw new NullPointerException("Det er ikke lov å legge inn nullelement");
        }
        //oppretter en ny node
        Node<T> nyNode = new Node<>(verdi);

        //hvis hode er null blir hode og hale lik nynode, hvis ikke så legges det nynode som blir til den nye halen.
        if (hode == null) {
            hode = nyNode;
            hale = nyNode;
        } else {
            hale.neste = nyNode;
            nyNode.forrige = hale;
            hale = nyNode;
        }
        //om kondisjonene virker så øker endringer og hvis koden er ferdig med å sjekke så returneres true
        antall++;
        endringer++;
        return true;
    }

    // Oppgave 3
    private Node<T> finnNode(int indeks) {
        // Sjekker om indeksen er gyldig
        if (indeks < 0 || indeks >= antall) {
            throw new IndexOutOfBoundsException("Ugyldig indeks: " + indeks);
        }
        Node<T> p; // Peker til nåværende element
        // Søker indeksen fra begynnelsen til den finner gitt indeks
        if (indeks < antall / 2) {
            p = hode;
            for (int i = 0; i < indeks; i++) {
                p = p.neste;
            }
        } // Søker indeksen fra slutten
        else {
            p = hale;
            for (int i = antall - 1; i > indeks; i--) {
                p = p.forrige;
            }
        } return p; // Returnerer noden til gitt indeks
    }

    @Override public T hent(int indeks) {
        // Henter ut verdien for metoden over
        indeksKontroll(indeks, false); // false: indeks=antall er ulovlig
        return finnNode(indeks).verdi;
    }

    @Override public T oppdater(int indeks, T nyverdi) {
        // Sjekk for gyldig indeks
        indeksKontroll(indeks, false);
        // Sjekker om nyverdi==null
        if (nyverdi == null) {
            throw new NullPointerException("Ikke tillatt med null-verdier!");
        }
        Node<T> p = finnNode(indeks); // Hente noden som ligger på den angitte indeksen
        T gammelverdi = p.verdi; // Lagrer den gamle verdien
        p.verdi = nyverdi; // Nodens verdi oppdatert med nyverdi
        endringer++; // En endring i listen
        return gammelverdi;
    }

    // Returerer en ny liste, som inneholder verdiene fra intervallet [fra, til⟩
    public Liste<T> subliste(int fra, int til) {
        fraTilKontroll(fra,til); // Sjekker om indeksene er lovlige
        DobbeltLenketListe<T> subListe = new DobbeltLenketListe<>(); // Dobbeltlenket for å legge inn verdier
        // Returnerer tom liste
        if (fra == til)
            return subListe;
        Node<T> p = finnNode(fra); // Peker som blir satt på indeksen "fra"
        // Itererer gjennom "[fra, til⟩" og legger inn verdiene i sublisten
        for(int i = fra; i < til; i++) {
            subListe.leggInn(p.verdi);
            p = p.neste;
        }
        return subListe;
    }

    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        // Sjekker om verdien vi prøver å finne er null, og returnerer i så fall -1,
        // da lista ikke kan inneholde null-verdier
        if (verdi == null) return -1;

        Node<T> current = hode;

        // Gå gjennom lista så lenge current ikke er lik null, da det betyr at vi har nådd enden av lista
        for (int i = 0; current !=null; i++){
            // Sjekker om verdi er lik current.verdi, og returnerer i så fall indeksen til verdien
            if (verdi.equals(current.verdi)) return i;

            // Om vi ikke fant verdien vi lette etter, settes current til å peke på neste node i lista
            current = current.neste;
        }
        // Returnerer -1 om vi går gjennom lista og ikke finner verdien i lista
        return -1;
    }

    @Override
    public boolean inneholder(T verdi) {
        // Sjekker om verdien vår finnes i lista ved å søke etter indeksen til verdien
        // dersom den ikke er i lista returnerer den -1
        return indeksTil(verdi) != -1;
    }

    // Oppgave 5
    @Override public void leggInn(int indeks, T verdi) {
        // Sjekk for gyldig indeks
        indeksKontroll(indeks, true);
        // Sjekk for null verdier
        if (verdi == null) {
            throw new NullPointerException("Ikke tillatt med null-verdier!"); }
        // Alternativt: Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        Node<T> nyNode = new Node<>(verdi); // Ny node med gitt verdi
        // Tom liste
        if (antall == 0) {
            // Hode og hale peker på den nye noden
            hode = hale = nyNode;
        }
        // Innsetting i starten av listen
        else if (indeks == 0) {
            nyNode.neste = hode; // nyNode.neste peker på hode (første verdi)
            hode.forrige = nyNode; // Hode.forrige peker på nyNode
            hode = nyNode; // Oppdater hode til å peke på nyNode
        }
        // Innsetting bakerst i listen
        else if (indeks == antall) {
            nyNode.forrige = hale; // Nynode.forrige peker på hale (siste verdi)
            hale.neste = nyNode; // Hale.neste peker på nyNode
            hale = nyNode; // Oppdater hale til å peke på nyNode
        }
        // Innsetting et sted midt i listen
        else {
            Node<T> p = finnNode(indeks); // nyNode til venstre for p
            nyNode.forrige = p.forrige; // nyNode.forrige peker på p sin tidligere forrige
            nyNode.neste = p; // nyNode peker på p
            p.forrige.neste = nyNode; // Noden før nyNode peker med sin neste-peker til nyNode
            p.forrige = nyNode; // Oppdater p.forrige til å peke på nyNode
        }
        antall++; // Ny verdi i listen
        endringer++; // En endring i listen
    }

    // Oppgave 6
    @Override public T fjern(int indeks) {
        // Sjekk for gyldig indeks
        indeksKontroll(indeks,false);
        Node<T> p = finnNode(indeks); // Finner noden med riktig indeks
        T verdi = p.verdi; // Lagrer verdien før vi sletter den

        // Kun en node i listen
        if (antall == 1) {
            hode = hale = null; // Hode og hale til null
        }
        // Første verdi fjernes (indeks == 0)
        else if (p == hode) {
            hode = hode.neste; // Hode peker på neste noden
            hode.forrige = null; // hode.forrige til null (skal ikke ha noe forrige)
        }
        // Siste fjernes (indeks == hale)
        else if (p == hale) {
            hale = hale.forrige; // Hale peker til nest siste noden (hale.forrige)
            hale.neste = null; // hale.neste til null (fjerne peker til gamle halen)
        }
        // Fjern fra i midten
        else {
            p.forrige.neste = p.neste; // Forrige node før p sin neste peker på noden etter p
            p.neste.forrige = p.forrige; // Neste node etter p sin forrige peker på noden før p
            p.neste = p.forrige = null; // Nulstriller pekerne
        }
        antall--; // En verdi mindre i listen
        endringer++; // Ny endring i listen
        return verdi;
    }

    @Override public boolean fjern(T verdi) {
        if (verdi == null) return false; // Sjekk om angitte verdien er null
        Node<T> p = hode;

        // Helt til vi finner noden som har samme verdi som gitt verdi
        while (p != null && !p.verdi.equals(verdi)) {
            p = p.neste;
        }
        // Verdien er ikke i listen
        if (p == null) {
            return false;
        }
        // Kun en node i listen
        else if (antall == 1) {
            // Noden fjernes ved å sette hode og hale til null
            hode = hale = null;
        }
        // Første fjernes
        else if (p == hode) {
            hode = hode.neste; // Hode settes til noden etter
            if (hode != null)
                hode.forrige = null; // Skal ikke ha noe forrige
            else hale = null; // Ellers halen til null
        }
        // Siste fjernes
        else if (p == hale) {
            hale = hale.forrige; // Hale peker til nest siste noden
            hale.neste = null; // hale.neste til null (fjerne peker til gamle halen)
        }
        // Verdi i midten fjernes
        else {
            p.forrige.neste = p.neste; // p.forrige sin neste-peker til noden etter p
            p.neste.forrige = p.forrige; // sin forrige-peker satt til noden før p
        }
        // Sørger for at gamle referansene til noden er fjernet
        p.verdi = null;
        p.forrige = p.neste = null;
        antall--; // En verdi mindre i listen
        endringer++; // Ny endring i listen
        return true;
    }

    // Oppgave 7
    @Override
    public void nullstill() {
        //opretter en peker som peker til hode
        Node<T> peker = hode;
        //hvis peker er ikke tom itererer while løkke
        while (peker != null) {
            Node<T> next = peker.neste; //noden lagrer neste peker
            peker.forrige = null; //fjerner pekeren til forrige node
            peker.neste = null; //fjerner peker til neste node
            peker = next; //går videre til neste node
        }
        hode = null;
        hale = null;
        antall = 0;
        endringer++;

        /* a) Denne måten tok 19 ms, siden denne implenteringen unngår gjentatte funksjonskall og utfører færre
           operasjoner er den mer effektiv.
           b) Denne måten tok 89 ms
           while (antall > 0) {
                fjern(0);
           } */
    }

    // Oppgave 8
    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false); //sjekker om indeksen er gyldig eller ikke
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean kanFjerne;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode; // Starter på første i lista
            kanFjerne = false; // Settes true når next() kalles
            iteratorendringer = endringer; // Teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);         // Setter "denne" til noden med den gitte indeksen
            kanFjerne = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (!(iteratorendringer == endringer)) {
                throw new ConcurrentModificationException("Listen har blitt endret.");
            } else if (!hasNext()){
                throw new NoSuchElementException("Ingen flere elementer i lista.");
            }
            kanFjerne = true;
            T verdi = denne.verdi;
            denne = denne.neste;
            return verdi;
        }

        // Oppgave 9:
        @Override
        public void remove() {
            if (!kanFjerne) {
                throw new IllegalStateException("Kan ikke fjerne uten å kalle next() først!");
            }

            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Listen er endret etter at iteratoren ble opprettet!");
            }

            Node<T> skalFjernes;

            // hvis "denne" er null, betyr det at vi er ved slutten av listen.
            // i dette tilfellet skal hale noden fjernes

            // hvis denne-pekeren er null betyr det at vi fjerner hale-noden
            if (denne == null) {
                skalFjernes = hale;

                //setter da halepekeren til heller å peke på den forrige noden
                hale = hale.forrige;

                //hvis hale nå peker på et tidligere element, så settes dette elementets nestepeker til null
                if (hale != null) {
                    hale.neste = null;
                } else {
                    hode = null; // Hvis hale blir null, bør hode også være null, da lista er tom
                }
            } else {
                //om vi ikke skal fjerne halenoden, betyr det at vi skal fjerne noden før denne
                skalFjernes = denne.forrige;

                // hvis noden vi skal fjerne er hode noden
                if (skalFjernes == hode) {
                    hode = denne;
                    hode.forrige = null;
                } else {
                    skalFjernes.forrige.neste = denne;
                    denne.forrige = skalFjernes.forrige;
                }

            }

            //har nå fjernet elementet, så vi nullstiller skalfjerne-noden
            skalFjernes.forrige = null;
            skalFjernes.neste = null;


            antall--;
            endringer++;
            iteratorendringer++;
            kanFjerne = false;
        }
    }

    // Oppgave 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        // Henter antall elementer i listen
        int antall = liste.antall();
        // Går gjennom hvert element i lista
        for (int i = 1; i < antall; i++){

            // Henter elementet på indeks i
            T element = liste.hent(i);

            // Setter j til å være en mindre enn i, sånn at j peker på elementet rett før i
            int j = i-1;

            // Starter en while-løkke så lenge j er større enn eller lik 0, så vi ikke går utenfor lista
            //og sammenlikningen mellom element og elementet på indeks j er mindre enn 0
            //c.compare(element, liste.hent(j)) <0 betyr at element er mindree enn elementet på plass j,
            // altså må element flyttes lengre bak i lista
            while (j >= 0 && c.compare(element, liste.hent(j)) <0){
                //flytter elementet fra indeks j til plass j+1, sånn at det blir plass til det nye elementet som skal inn
                liste.oppdater(j+1, liste.hent(j));
                // Minker j med 1 så element kan sammenliknes med de forrige elementene i lista
                j--;
            }
            liste.oppdater(j+1, element);
            // Setter element på den riktige plassen i den sorterte delen av lista
            // Når while-løkka er ferdi vil j+1 være indeksen der element skal plasseres
        }
    }
}