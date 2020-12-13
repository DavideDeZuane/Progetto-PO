package Controller;

import Model.Job;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;

//todo File di configurazione per il base URL
//todo Formattare la Stringa per le chi,atae all'API

//bits Flag
enum PARAMS{
    ID(1),
    TYPE(2),
    COMPANY(4),
    LOCATION(8),
    TITLE(16),
    DESCRIPTION(32);

    private int id;
    PARAMS(final int id){ this.id = id; }
    public int getId(){ return id; }

}

//questa classe si occuperà di effettuare ed elaborare le chiamate all'Api
public class ApiController {
    //formato base delle stringhe necessario per fare le chiamte con filtri
    private static final String baseUrl_id = "https://jobs.github.com/positions/%s.json";
    private static final String hostname =  "https://jobs.github.com/positions.json/";

    private ObjectMapper mapper; //necessario per convertire il file Json in Oggetti o Array di oggetti
    private URL url;

    public ApiController(String url) throws MalformedURLException{
        mapper = new ObjectMapper();
        setUrl(url);
    }
    public Object parsing() throws IOException{
        return mapper.readValue(this.url, new TypeReference<HashSet<Job>>(){});
    }

    public Job parsing(Boolean flag) throws IOException{
        return mapper.readValue(this.url, Job.class);
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

}