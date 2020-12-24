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

    /**
     * constructor
     */
    public ApiController(){
        mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    /**
     * this method gets a object of JobBoard type
     * @return a object of JobBoard type
     */
    public JobBoard getJobBoard() {
        return jobBoard;
    }

    /**
     * this method sets a object of JobBoard type
     * @param jobBoard object of the JobBoard class
     */
    public void setJobBoard(JobBoard jobBoard) {
        this.jobBoard = jobBoard;
    }

    /**
     * this method gets a object of ObjectMapper type
     * @return a object of ObjectMapper type
     */
    public ObjectMapper getMapper(){ return mapper; }

    /**
     * this method gets a Url
     * @return a URL type
     */
    public URL getUrl(){ return url; }

    /**
     * this method sets a URL object
     * @param url url that will be set
     */
    public void setUrl(URL url){ this.url = url; }

    /**
     * this method reads a json from a url and commutes the json to objects, if there is only an object, the method saves it to a Job.class,
     * if there are many objects the method saves them to a HashSet
     * @param jobs HashSet structure that will be populated
     * @throws IOException if the mapper fails to read the json
     */
    public void save(HashSet<Job> jobs) throws IOException{
        try {
            //jobs.clear();
            jobs.addAll(mapper.readValue(this.url, new TypeReference<HashSet<Job>>() {})); //mappo json array

        }catch(Exception e){
            jobs.add(mapper.readValue(this.url, Job.class)); //mappo json object
        }

    }

    /**
     * this method fills a HashSet of Job type by using the save method
     * @param offer HashSet of Job type that will be filled
     * @param url url that the save method will get the json from
     */
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
     * this method commutes a string to a URL object after verifying all the syntax is correct
     * @param url String object that will be commute to a URL
     * @return a URL object
     * @throws MalformedURLException if the String is not in the correct format
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
     * this method allows to make a customized query
     * @param s is a String array that contains the parameters we want to add to the query
     * @return a URL that will allow to make a customized query
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