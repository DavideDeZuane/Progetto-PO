package Controller;

import Exception.JsonMismatch;
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

public class ApiController extends Controller{

    private URL url;
    private final ObjectMapper mapper;

    private JobBoard jobBoard = new JobBoard();

    /**
     * constructor, it initialises a ObjectMapper that will be used in the whole project
     */
    public ApiController(){
        mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    /**
     * this method gets a object of ObjectMapper type
     * @return a object of ObjectMapper type
     */
    public ObjectMapper getMapper(){ return mapper; }

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
            jobs.addAll(mapper.readValue(this.url, new TypeReference<HashSet<Job>>() {}));
        }catch(JsonMismatch e){
            jobs.add(mapper.readValue(this.url, Job.class));
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
     * this method allows to make a customized query by adding to the default query key-value pairs with a specific API syntax,
     * the flag contains the parameters that the user has selected (bit flag)
     * @param s is a String array that contains the parameters we want to add to the query
     * @return a URL that will allow to make a customized query
     */
    public static URL query(String[] s) throws MalformedURLException{
        String temp = baseUrl;
        boolean first = true;
        int cont = 0;
        if(flags != null){
            if (flags.contains(Parameters.TYPE)) {
                temp += "full_time=%s";                                                //%s is a placeholder for the parameters
                temp = String.format(temp, "true");                                    //format replaces the placeholder with a value
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