[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/FVZ-bAxQ)
# Compulsory Assignment 2 in DATS2300 - Algorithms and Data Structures

This assignment is a submission in Algorithms and Data Structures (in Norwegian). It was completed in collaboration with three other group members.

## Oppgavebeskrivelser
## Oppgave 1
I oppgave 1 skulle vi lage to konstruktører samt to metoder; én som sjekker antall elementer i tabellen og én som 
sjekker om den er tom. 
<br>
Først satte vi opp en tom konstruktør og lagde ferdig tomhets-sjekken og metoden som skulle telle antall elementer. 
Deretter lagde vi den siste konstruktøren med en for-løkke for å gå gjennom alle elementene i tabellen, med en if-test 
som sjekker om elementet vi tester er et null-element vil ikke løkka øke antallet, men hvis det ikke er et null-element, 
vil antallet øke. På den måten vil konstruktøren generere en dobbelt-lenket liste som består av ikke-null verdier 
som oppgaven ber om.

## Oppgave 2
I oppgave 2 gjorde vi sånn at toString() metoden returnerer en tegnstreng med listens verdier. Vi valgte å bruke 
StringBuilder for å bygge strengene effektivt og for å legge til klammeparanteser og komma. Vi impelmenterte en while 
løkke som legger veridene hvis pekeren ikke er null. If setningen sjekker om neste pekeren er null eller ikke, hvis den 
ikke er null da legger den komma etter verdien. Så avsluttes while løkka når det ikke er verdier igjen og legger til en 
klammeparantes. 

OmvendtString() metoden returnerer listens verdier i motsatt rekkefølge. Den begynner med halen altså siste noden og 
følger peker.forrige for å hente veridene fra listen baklengs. Verdiene legges inn i en StringBuilder, og til slutt 
returneres strengen med elementene omringet av klammeparanteser, akkurat som i toString() metoden. 

LeggInn() metoden legger inn ny verdi bakerst ved å følge pekeren hale.neste. Hver gang endringer blir gjennomført økes 
endringer.

## Oppgave 3
I oppgave 3 gjorde vi som følger for de ulike metodene:

finnNode()-metoden: Jeg sjekket om indeksen er gyldig ved å sammenligne den med 0 og antall.
Jeg delte søket i to deler: Søk fra hodet: Hvis indeksen er mindre enn halvparten av antall, starter jeg fra hode og går fremover ved hjelp av neste-pekerne.
Søk fra halen: Hvis indeksen er større eller lik halvparten, starter jeg fra hale og går bakover ved hjelp av forrige-pekerne.
Når metoden finner noden som tilsvarer indeksen, returneres denne noden.

hent()-metoden:Jeg sjekket først om den angitte indeksen er gyldig ved hjelp av indeksKontroll. Deretter brukte
jeg metoden finnNode(indeks) til å finne og returnere verdien til noden som ligger på den angitte indeksen. 

oppdater()-metoden: Jeg tok en sjekk for om indeksen er gyldig og en sjekk den nye verdien (nyverdi) er null. 
Videre brukte jeg metoden finnNode(indeks) for å hente noden på den spesifiserte indeksen.
Den gamle verdien ble lagret, og nodens verdi ble oppdatert med nyverdi. Til sist inkrementerte jeg endringer for å 
registrere at listen har blitt oppdatert, så returnere den gamle verdien.

subliste()-metode: Sjekket først om de angitte indeksene fra og til er gyldige.
Jeg opprettet så en ny instans av DobbeltLenketListe<T> kalt subListe for å lagre verdiene fra intervallet.
Hvis fra er lik til, returneres en tom liste. Metoden finnNode(fra) blir brukt for å få tak i noden som 
ligger på indeksen fra. En løkke blir så brukt for å iterere gjennom fra til til, som for hver iterasjon legger 
verdien fra noden til subListe ved å kalle subListe.leggInn(p.verdi).
Til slutt returnerer metoden en subListe, som nå inneholder verdiene fra det spesifiserte intervallet.

## Oppgave 4
I oppgave 4 skulle vi finne indeksen til et gitt element, og også finne ut om et element fantes i lista. For metoden indeksTil( T verdi) sjekket vi først om verdien var lik null, og returnerte -1 i så fall, da lista ikke skulle inneholde null-verdier. Så satte vi en lokal Node<T> current lik hode, og brukte en for-løkke til å gå gjennom lista, og sjekke om i er lik current. For-løkka skulle gå så lenge current ikke er lik null, altså så lenge vi ikke har nådd enden av lista. I for-løkka sammenlignes verdi med current.verdi og om de er like, returneres i, altså indeksen til dette elementet. Om elementet ikke var lik current.verdi, betyr dette at vi ikke har funnet det i lista enda, og vi setter current til å bli current.neste, så vi sammenligner neste element i lista med vår verdi. Om vi går gjennom hele for-løkka uten å finne en instans av vår verdi returnerer metoden -1, altså kan vi ikke finne indeksen til dette elementet. I metoden inneholder(T verdi) bruker vi indeksTil() for å finne ut om lista inneholder vår verdi. Om metoden finner indeksen til det gitt elementet, betyr det at lista inneholder det elementet. Metoden returnerer dermed om indeksTil() er ikke lik -1, altså betyr det at elementet er i lista. 

## Oppgave 5
Det første steget i oppgave 5 var å sjekke gyldigheten av den angitte indeksen og verdien før innsetting.

Deretter vurderte jeg hvordan metoden skulle håndtere ulike situasjoner avhengig av hvor i listen en node settes inn:
Tom liste: Nye noden blir hode og halen.
Innsetting i starten (0): Nye noden sin neste-peker peker til det tidligere hode. 
Det tidligere hode får sin forrige-peker på den nye noden. Nye noden blir hode.
Innsetting på slutten: Halen sin neste-peker settes til den nye noden, og den nye noden sin 
forrige-peker settes til den gamle halen. Nye noden blir halen.
Innsetting i midten: Vi finner noden på den gitte posisjonen. Den nye noden kobles til både noden før og etter ved å 
oppdatere deres pekere, slik at den nye noden plasseres riktig i listen.
Til slutt økte jeg antallet elementer i listen med 1 og inkrementerte en teller som sporer hvor mange ganger listen har blitt endret.

## Oppgave 6
For metoden fjern(int indeks) sjekket jeg først om den angitte indeksen var gyldig. Deretter håndterte jeg de spesifikke tilfellene
der en node kan fjernes:

Listen har kun én node: hode og hale til null.
Fjerne første node: oppdatere hode til å peke på neste node og nullstilte den forrige pekeren.
Fjerning av siste node: oppdatere hale til å peke på forrige node og nullstilte neste pekeren.
Fjerne en node i midten: brukte en hjelpemetoden finnNode til å finne noden, og justerte pekerne til nodene rundt.
Til slutt oppdaterte jeg tellere for å reflektere endringen, og returnerte verdien fra den fjernede noden.

For metoden fjern(T verdi), startet jeg med å håndtere nullverdier. Deretter la jeg til en løkke som søker gjennom listen for å 
finne noden som inneholder verdien, hvis den ikke finnes, returneres false.

Når noden er funnet, håndterte jeg de samme scenriene som i metoden over, listen inneholder én node, noden er hode, noden er halen og noden
er et sted midt i listen. Jeg nullstilte til slutt referansene for å lette garbage collection, oppdaterte tellere, og returnerte true for å indikere 
en vellykket fjerning.

## Oppgave 7
I oppgave 7 implementerte vi nullstill() metoden. Pekeren i denne metoden begynner fra hode. Med while løkka sjekkes
peker om den er null eller ikke, hvis den ikke er null nullstilles nodene ved hjelp av peker.forrige og peker.neste. 
Og peker går videre til neste node helt til alle forbindelser mellom nodene blir brytet opp.

## Oppgave 8
I oppgave 8 skulle vi lage en konstruktør og tre metoder.
<br>
Først lagde vi iteratorene i oppgaven ved å kjøre de private konstruktørene i metodene public Iterator<T>. 
<br>
Så lagede vi metoden public T next() ved hjelp av en if-test som sjekker om det har blitt gjort endringer i tabellen, 
og om det er flere elementer i lista etter den siste som har blitt gjennomgått. Deretter setter vi kanFjerne til true og 
lagrer verdien til "denne" for så å sette "denne" til den neste verdien i tabellen. Til slutt returnerer vi verdien vi 
allerede har lagret. 
<br>
Vi lagde konstruktøren private DobbeltLenketListe(int indeks) til slutt. De to siste linjene av konstruktøren 
private DobbeltLenketListeIterator() ville være likt som den vi skulle lage, ettersom konstruktørene skulle gjøre nesten 
samme oppgave; den som allerede var kodet inn i oppgaven starter fra første node i listen, mens den vi skulle lage søker 
bare etter noden på den gitte indeksen. Derfor ble den første linjen endret slik at den setter "denne" til noden som er 
på den gitte indeksen.

## Oppgave 9
I oppgave 9 skulle vi lage en kode som skulle fjerne en node.

Vi skrev først inn to if-tester der den første sjekket om man kan endre listen, og den andre sjekket om det har blitt 
gjort endringer i lista. Om lista er endret etter iteratoren ble laget, vil metoden kaste en feilmelding. Vi lager så en variabel skalFjernes som lagrer verdien som skal fjernes. Så har vi en if-test som sjekker om denne-pekeren er null. denne-pekeren er lik nodens neste-peker. Om denne-pekeren er null, betyr det at vi prøver å fjerne hale-noden, og if-testen oppdaterer alle pekerne i forhold til dette. Vi har også en indre if-test som sjekker om lista er tom, og oppdaterer hode-noden i samsvar med dette. Else-delen av den ytre if-testen sjekker om noden vi prøver å fjerne er hodenoden eller en indre node, og håndterer pekerne i forhold til dette. Til slutt reduserer vi antall, nullstiller skalFjerne, og øker endringer og iteratorendringer slik at antall viser riktig antall elementer i tabellen og endringer og iteratorendringer viser antall endringer gjort i tabellen.

## Oppgave 10
I oppgave 10 skulle vi finne en sorteringsmetode som brukte sammenlikneren c, og uten bruk av hjelpestrukturer. Metoden fungerer ved å først hente antall elementer i lista og lagre dette i en lokal variabel antall. Så går en for-løkke gjennom hvert element i lista, og vi henter elementet på indeks i og lagrer det i en lokal variabel element. Så setter vi en lokal variabel j til å være en mindre enn i. Vi starter så en while-loop som sammenlikner element og elementet på indeks j, så lenge j er større enn eller lik 0. Om sammenlikningen gir et negativt tall, betyr dette at elementet på indeks j er større enn element, så vi flytter elementet fra indeks j til en plass større og minker j med en så element kan sammenliknes de forrige elementene i lista. Denne prosessen fortsetter fram til element ikke er mindre enn elementet på plass j, og da hopper vi ut av while-loopen, og setter element på den neste plassen, der den nå hører til. i øker nå med en, slik at element settes til det neste elementet i lista, og vi sammenlikner dette elementet med de forrige elementene i lista.

For å finne kompleksiteten til en metode som dette kan vi kjøre den på en liste med 1000 elementer og så en liste med 2000 elementer. Deretter kan man sammenligne tiden det tok å kjøre metoden på hver av listene. Om tiden dobles, vet man at kompleksiteten er lineær, om den er konstant vil ikke tiden endres, etc. Her firedoblet tiden seg, noe som betyr at kompleksiteten til algoritmen er kvadratisk, altså O(n^2). 
