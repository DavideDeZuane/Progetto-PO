package Controller;

import Model.Job;
import Model.JobBoard;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;

//todo migliorare il metodo query
//todo implementare il metodo fill per riempire wareHouse
//todo estrarre campo how_to_apply

public class ApiController extends Controller{

    private URL url;
    private final ObjectMapper mapper;

    private JobBoard jobBoard = new JobBoard();

    public ApiController(){
        mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public JobBoard getJobBoard() {
        return jobBoard;
    }

    public void setJobBoard(JobBoard jobBoard) {
        this.jobBoard = jobBoard;
    }



    //getter e setter
    public ObjectMapper getMapper(){ return mapper; }
    public URL getUrl(){ return url; }
    public void setUrl(URL url){ this.url = url; }


    //metodo parsing aggiornato
    public void save(HashSet<Job> jobs) throws IOException{
        try {
            jobs.clear();
            jobs.addAll(mapper.readValue(this.url, new TypeReference<HashSet<Job>>() {})); //mappo json array

        }catch(Exception e){
            jobs.add(mapper.readValue(this.url, Job.class)); //mappo json object
        }

    }

    public void fill(JobBoard offer, URL url){
        this.url = url;
        try{
            save(offer.getJobs());
        }catch(IOException e){
            System.out.println("Qualche problema");
        }

    }

    //metodi per creare le richieste url
    /**
     * Ritorna un oggetto URL a partire da una Stringa
     * dopo aver veridicato che la sintassi sia corretta
     * @param url Stringa che si vuole trasformare in URL
     * @return Ritorna un oggetto URL da poter utilizzare
     * @throws MalformedURLException se c'e un errore nella sintassi
     */
    public static URL createUrl(String url) throws MalformedURLException{
        try {
            URL obj = new URL(url);
            obj.toURI(); //converte l'URL in URI, se l'URL non è nel formato specificato generano un errore se convertiti in URI
            return obj;
        } catch(MalformedURLException | URISyntaxException ex){
            System.out.println("C'è un errore nella sintassi");
            throw new MalformedURLException();
        }
    }

    /**
     * Questo metodo grazie all'utilizzo di un Bit Flag permette di generare una chiamata con filtri
     * @param s contiene i parametri da inserire come valori nella chiamata
     * //@param flags contiene quali filtri l'uente ha inserito e quindi quali
     *              chiavi aggiungere alla chiamata
     * @return Ritorna un URL per poter effettuare una chiamata filtrata
     */
    public static URL query(String[] s) throws MalformedURLException{
        String temp = baseUrl;
        boolean first = true;
        int cont = 0;
        if(flags != null){
            if (flags.contains(Parameters.TYPE)) {
                temp += "full_time=%s";
                temp = String.format(temp, "true");
                first = false;
            }

            if (flags.contains(Parameters.DESCRIPTION)) {
                if (!first)
                    temp += "&";
                temp += "search=%s";
                temp = String.format(temp, s[cont++]);
                first = false;
            }

            if(flags.contains(Parameters.LOCATION)){
                if (!first)
                    temp += "&";
                temp += "location=%s";
                temp = String.format(temp, s[cont]);
            }
        }
        return createUrl(temp);
    }
}