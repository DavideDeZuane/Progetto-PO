package Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

//todo File di configurazione per il base URL
//todo Formattare la Stringa per le chiamate all'API

//bits Flag per fare la query


//questa classe si occuperà di effettuare ed elaborare le chiamate all'Api
public class ApiController {
    //formato base delle stringhe necessario per fare le chiamte con filtri
    private static final String baseUrl_id = "https://jobs.github.com/positions/%s.json";
    private static final String hostname =  "https://jobs.github.com/positions.json?";

    private ObjectMapper mapper; //necessario per convertire il file Json in Oggetti o Array di oggetti
    private URL url;

    public ApiController(String url) throws MalformedURLException{
        mapper = new ObjectMapper();
        setUrl(url);
    }
    public Object parsing() throws IOException{
        return mapper.readValue(this.url, new TypeReference<HashSet<Lavoro>>(){});
    }

    public Lavoro parsing(Boolean flag) throws IOException{
        return mapper.readValue(this.url, Lavoro.class);
    }


    //metodi per l'analisi e la creazione delle richieste

    public void setUrl(String url) throws MalformedURLException {
        if(isValidUrl(url))
            this.url = new URL(url);
        else
            System.out.println("Vi sono errori di sintassi nell'URL");
    }


    public boolean isValidUrl(String url) {
        try {
            URL obj = new URL(url);
            obj.toURI(); //converte l'URL in URI, se l'URL non è nel formato specificato generano un errore se convertiti in URI
            this.url = obj;
            return true;
        } catch (MalformedURLException  | URISyntaxException ex){
            return false;
        }
    }
    public URL getUrl(){ return url;}


    //metodo che si occuperà di riempire le richieste in base ai parametri passati dall'utente
    public static String query(String[] s, EnumSet<PARAMETERES> flags){
        String temp = hostname;
        boolean first = true;
        int cont = 0;
        if(flags.contains(PARAMETERES.ID)) {
            temp = String.format(baseUrl_id, s);
            return temp;
        }
        if(flags.contains(PARAMETERES.TYPE)) {
            if(!first)
                temp += "&";
            temp +="type=%s";   //%s è il placeholder
            temp = String.format(temp, s[cont++]);
            first = false;
        }
        if(flags.contains(PARAMETERES.TITLE)){
            if(!first)
                temp += "&";
            temp += "title=%s";
            temp = String.format(temp, s[cont++]);
            first = false;
        }
        if(flags.contains(PARAMETERES.COMPANY)){
            if(!first)
                temp += "&";
            temp += "company=%s";
            temp = String.format(temp, s[cont++]);
            first = false;
        }
        if(flags.contains(PARAMETERES.DESCRIPTION)){
            if(!first)
                temp += "&";
            temp += "description=%s";
            temp = String.format(temp, s[cont++]);
            first = false;
        }
        if(flags.contains(PARAMETERES.LOCATION)){
            if(!first)
                temp += "&";
            temp += "location=%s";
            temp = String.format(temp, s[cont++]);
            first = false;
        }
        return temp;
    }

}
