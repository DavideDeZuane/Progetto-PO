<p align="center">
<img src="Images/Logo.png" width="175" height="175">
</p>

# Progetto JOBS GITHUB

L'obbiettivo del progetto è quello di creare un applicazione che permetta all'utente di monitorare 
offerte di lavoro e su tali offerte generare delle statistiche.

## L'Applicazione
L'API [GitHub Jobs](https://jobs.github.com/api) consente di cercare lavori in formato JSON, dove ad ogni **`chiave`** è associato un **`valore`**.
L'offerta di lavoro generica restituita dall'API ha la seguente forma:
```json
{
    "id" : "b1691d49-ee1d-4793-9c1d-7a10a9b8b84a",
    "type" : "Full Time",
    "url" : "https://jobs.github.com/positions/b1691d49-ee1d-4793-9c1d-7a10a9b8b84a",
    "created_at" : "Mon Dec 07 19:23:19 UTC 2020",
    "company" : "EME Hive",
    "company_url" : "http://emehive.co",
    "location" : "New York City",
    "title" : "Infrastructure Engineer for Social Livestreaming App",
    "description" : "<p>...</p>\n",
    "how_to_apply" : "<p>...</p>\n",
    "company_logo" : "https://....png"
}
```
Attraverso dei filtri abbiamo la possibilità di scegliere solo la categoria che ci interessa. I possibili filtri sono i seguenti:
>+ **Description**: un termine contenuto all'interno della descrizione dell'offerta.
>+ **Location**: città in cui siamo intenzionati a fare la ricerca delle offerte.
>+ **Latitudine** e **Longitudine**: utilizzabili in sostituzinone alla località a patto che vengano utilizzati entrambi.
>+ **Full Time**: per limitari i risultati a solo offerte di lavoro full time.

Tali filtri vanno inseriti con una certa sintassi all'interno della richiesta; un esempio è il seguente:
> https://jobs.github.com/positions.json?description=python&full_time=true&location=sf

Per un utente medio risulta difficoltoso riuscire a compilare una richiesta simile e leggerne il risultato. 
Per ovviere a questo problema abbiamo pensato di integrare nel nostro programma:
+ **Frontend**: un interfaccia che permetta all'utente di inserire facilmente i filtri da applicare alla ricerca;
+ **Backend**: un insieme di classi e strutture dati che permettono all'utente di visualizzare, in un formato a cui è abituato, il risultato della ricerca.


## UML
In prima istanza abbiamo realizzato dei Diagrammi UML per modellare il funzionamento 
della nostra Applicazione.

 + **Use-Case Diagram**

> ![Panel](Images/UML/UseCase.png)

Brevemente:
+ l'utente si interfaccia e interagiste con il frontend (un insieme di panelli della GUI) 
+ il risultato delle iterazione viene passato al backend che effettua richieste
     + interroga l'API fornita
     + ne elabora le risposte 
     + presenta il risultato dell'elelaborazione all'utente, offrendo alcune funzionalità come statistiche e salvare lavori.

---

+ **Class Diagram**

> ![Finestre Principali](Images/UML/DiagrammaClassi.png)

---

+ **Sequence Diagram** 
> ![](Images/UML/SequenceDiagramUpdate.png)

Sarà presente anche un file di configurazione contenente gli URL per effettuare le chiamte all'API.
In questo modo nell'eventualità che venga cambiato l'URL dell'API basterà cambiarlo nel file configurazione senza intervenire direttamente sul programma.

---

Questi diagrammi sono abbastanza lontani dalla realizzazione finale del progetto; questo perchè durante la fase di sviluppo siamo andati incontro
a limitazioni e ad aggiunte di funzionalità che hanno modificato le relazioni definite inizalmente.



## FrameWork
I prinicipali framework utilizzati per la realizzazione dell'applicazione sono i seguenti:
+ [Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) 
+ [Jackson JSON](https://github.com/FasterXML/jackson/wiki/Jackson-Release-2.12)
+ [Jsoup](https://jsoup.org)
+ [JFreeChart](https://sourceforge.net/projects/jfreechart/files/1.%20JFreeChart/1.0.19/)




## Funzionamento
Appena avviatà l'applicazione l'utente troverà la seguente finestra:

![](Images/Funzionamento/SchermataPrincipale.png)



| Indice      | Descrizione |  Key |  Value |
| :-----------: | ----------- | :-----------: | :-----------: |
| **1.**      | La Checkbox permette di selezionare se si vuole ridurre la ricerca a soli lavori Full Time; non inserendo alcune spunta verrano presentati all'utente lavori con una qualsiasi tipologia di duarata della prestazione lavorativa. | Full Time | True |
| **2.**   |   Il Campo di testo Description di testo permette di inserire una parola chiave da ricercare all'interno della descrizione dell'offerta di lavoro. Esempio: inserendo Java otterremo come risultato tutte le offerte di lavoro che all'interno della loro descrizione contengono la parola chiave Java.| Description | keywork |
| **3.**   | Il Campo di testo Location di testo permette di filtrare le offerte inserendo: o il nome di una città o il suo codice postale o le sue iniziali. | Location| City |
| **4.**      |  Il bottone **Show Saved Jobs** permette all'utente di avere un elenco di lavori da tenere d'occhio | / | / |
| **5.**   |  Il bottone **Search** permette di visualizzare il risultato dell'interrogazione da parte del nostro progrmma all'API,forendoci tutti i lavori che rispettando i filtri inseirti dall'utente| / | / |

--- 

Il seguente è un esempio di una richiesta in cui come parametri sono stati inseiri:
+ Full Time: True
+ Description: Java
+ Location: Berlino

L'URL che l'utente avrebbe dovuto creare sarebbe stato il seguente:
> https://jobs.github.com/positions.json?full_time=true&description=java&location=Berlino 

Grazie al programma si astrae questa fase e premendo il tasto search otteniamo la seguente finestra:
    
![Jobs Found](Images/Funzionamento/LavoriTrovati.png)

Anche qui sono presenti altri tasti che permettono all'utente di utilizzare le seguenti funzionalità.
| Indice      | Funzionalità | 
| :-----------: | ----------- |
| **6.** | Stats permette di visualizzare statistiche relative ai lavori trovati; esempi sono percentuali di richieste full time e non, informazioni sulla data di creazione dell'offera e quante volte compare la parola chiave all'interno delle descrizioni dei lavori trovati. |
| **7.** | Mostra le istruzioni ,oppure i link utili come per esmpio link ad un form o contatti email (a seconda dell'azienda) che consentono all'utente di effettuare la domanda per candidarsi al ruolo ricercato. |
| **8.** | Save permette all'utente di salvare le rischieste a cui è interessato, in modo da poterle in un qualsiasi momento senza cercarle nuovamente. |
| **9.** | Permette di visualizzare i lavori salvati fino a questo momento dall'utente. |
| **10.** | Crea un collegamento diretto con la pagina web della compagnia, permettendo all'utente di informarsi sull'azione e sul settore in cui agisce. |
| **11.** |Permette di salvare tutti i lavori trovati |
| **12.** | Fa ritornare il programma alla finestra precedente, in cui è necessario inserire i filtri |


## Approfondimenti

**Mulithreading**
Nel nostro progetto abbiamo implementato il multithreaing per verificare se tra un avvio dell'applicazione e l'altro le offerte che l'utente stava
osservando sono scadute.
Per evitare che questa verifica fosse troppo dispendiosa a livello di tempo abbiamo deciso di eseguirla su un thread differente per massimizzare
l'utilizzo della CPU e quindi diminuire i tempi di attesa.

Link utili per approfondire il multithreading:

+ <https://www.javatpoint.com/multithreading-in-java>
+ <https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html>

    

## Developers

| **Autori**  | **Email**| **GitHub**  | **Contributo**  | LinkedIn |
|---|---|---|:---:|---|
|  Chiara Gobbi | <s1093786@studenti.univpm.it> |  [chiaragii](https://github.com/chiaragii) | 1/3   | <https://www.linkedin.com/in/chiara-gobbi-1900931bb>  |
|  Daniele Benfatto |<s1092454@studenti.univpm.it>   | [benFactotum99](https://github.com/benFactotum99)  |   1/3 |  <https://www.linkedin.com/in/daniele-benfatto-247830201> |
|  Davide De Zuane | <davide@girori.net>  |  [DavideDeZuane](https://github.com/DavideDeZuane) | 1/3  |  <https://www.linkedin.com/in/davide-de-zuane-021372201> |
