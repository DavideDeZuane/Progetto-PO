package Controller;

import Model.Job;
import Model.WareHouse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

//todo migliorare il metodo query
//todo implementare il metodo fill per riempire wareHouse


//questa classe si occuperà di effettuare ed elaborare le chiamate all'Api
public class ApiController {

    private static final String baseUrl_id = "https://jobs.github.com/positions/%s.json";
    private static final String hostname =  "https://jobs.github.com/positions.json?";
    //formato base delle stringhe necessario per fare le chiamte con filtri
    //potrebbero essere anche messi dentro ad un file di configurazione

    private ObjectMapper mapper;  //effettua il parsing dei file Json
                                 //utilizzeremo lo stesso mapper per fare più azioni perchè è molto dispendioso a livello di risorse

    public ApiController(){
        mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public HashSet<Job> parsing(URL url) throws IOException{
        HashSet<Job> obj = new HashSet<>();
        try {
            obj = mapper.readValue(url, new TypeReference<HashSet<Job>>() {});
            if (obj.isEmpty())
                System.out.println("Non è stata trovata alcuna offerta di lavoro"); //metodo GUI che lo presenta all'utente

        }catch(Exception e){  //sarà un eccezione personalizzata
            System.out.println("Sto elaborando.....");
            obj.add(mapper.readValue(url, Job.class));
        }
        return obj;
    }

    public void fill(WareHouse temp, HashSet<Job> result){
        //temp.jobs.addAll(result);

    }

    //metodi per l'analisi e la creazione delle richieste
    //metodi statici che servono per la creazione delle richieste da fare aall'api

    public static URL createUrl(String url) throws MalformedURLException{
        try {
            URL obj = new URL(url);
            obj.toURI(); //converte l'URL in URI, se l'URL non è nel formato specificato generano un errore se convertiti in URI
            return obj;
        } catch(MalformedURLException | URISyntaxException ex){
            throw new MalformedURLException();
        }
    }

    /**
     *
     * @param s contiene i parametri
     * @param flags contiene quali filtri l'uente ha inserito
     * @return una
     */
    //metodo che si occuperà di riempire le richieste in base ai parametri passati dall'utente
    //è necessario stabilire un ordine con cui vengono passati i parametri

    public static URL query(String[] s, EnumSet<Parameters> flags, boolean flagFullTime) throws MalformedURLException{
        String temp = hostname;
        boolean first = true;
        int cont = 0;

        if(flags == null){
            throw new MalformedURLException();
        }

        if(flags.contains(Parameters.TYPE)) {
        //if(flagFullTime){
            if(!first)
                temp += "&";
            //if(s[cont] != "/n") {
            temp += "full_time=true";   //%s è il placeholder
            temp = String.format(temp, s[cont++]);
            first = false;
        }

        if(flags.contains(Parameters.DESCRIPTION)){
            if(!first)
                temp += "&";
            temp += "search=%s";
            temp = String.format(temp, s[cont++]);
            first = false;
        }

        if(flags.contains(Parameters.LOCATION)){  //se è presente la località non dobbiamo far inserire le coordinate
            if(!first)
                temp += "&";
            temp += "location=%s";
            temp = String.format(temp, s[cont++]);
            first = false;
        }
        if(flags.contains(Parameters.LATITUDINE)){  //<----
            temp += "lat=%s";
            temp = String.format(temp, s[cont++]);      //devono essere utilizzate entrambe obbligatoriamente se non si incerisce la località
            first = false;
        }
        if(flags.contains(Parameters.LONGITUDINE)) { //<----
            temp += "&long=%s";
            temp = String.format(temp, s[cont]);
            first = false;
        }

        return createUrl(temp);  //una volta creata la stringa genera un URL
    }

    public static String idQuery(String id){
        String temp = String.format(baseUrl_id, id);
        return temp;
    }

    public static boolean verifyOffer(String id) throws Exception{  //realizzabile anche con la classe HTTPClient
        //leggo gli id dal file su cui sono stati salvati e faccio delle richieste per verificare se le offerte sono ancora valide
            HttpURLConnection conn = (HttpURLConnection) new URL(idQuery(id)).openConnection();
            if(conn.getResponseCode() == 200) { //dopo lo status code 299 la richiesta o è reindirizzata o non può esser soddisfatta
                System.out.println("Le offerte selezionate precedentemente sono ancora presenti"); // presenta finestra pop-up sulla GUI
                return true;
            }
            System.out.println(conn.getResponseCode());
            System.out.println("Metodo per presentare una finestra pop-up che l'offerta non è più presente");
            return false;
    }

}
