package Controller;

import Model.Job;
import Model.JobBoard;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

//todo migliorare il metodo query
//todo implementare il metodo fill per riempire wareHouse
//todo estrarre campo how_to_apply

public abstract class ApiController{

    private static String requestIdUrl = "https://jobs.github.com/positions/%s.json";
    private static String baseUrl = "https://jobs.github.com/positions.json?";
    private static final Properties prop = new Properties();
    private static final File configFile = new File("Resources/Configuration/config.properties");

    protected static EnumSet<Parameters> flags = EnumSet.noneOf(Parameters.class);

    private URL url;
    private final ObjectMapper mapper;

    public ApiController(){
        mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public abstract String[] setFilters(JTextField txtLocation, JTextField txtDescription, boolean banner);

    //getter e setter
    public ObjectMapper getMapper(){ return mapper; }
    public URL getUrl(){ return url; }
    public void setUrl(URL url){ this.url = url; }
    public File getConfigFile(){ return configFile; }

    public HashSet<Job> parsing() throws IOException{
        HashSet<Job> obj = new HashSet<>();
        try {
            obj = mapper.readValue(this.url, new TypeReference<HashSet<Job>>() {});
            //JOptionPane.showMessageDialog(jobsFoundPanel,"     Bro, jobs ain't found");
        }catch(Exception e){
            System.out.println("Sto elaborando.....");
            obj.add(mapper.readValue(this.url, Job.class));
        }
        return obj;
    }

    public void fill(JobBoard offerte, URL url){
        this.url = url;
        try{
            offerte.setJobs(parsing());
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
            }else{
                if(flags.contains(Parameters.LATITUDINE) && flags.contains(Parameters.LONGITUDINE)) {
                    temp += "lat=%s";
                    temp = String.format(temp, s[cont++]);
                    temp += "&long=%s";
                    temp = String.format(temp, s[cont]);
                }else{
                    System.out.println("Devi inserire entrambe, latitudine e longitudine");
                }
            }
        }
        return createUrl(temp);
    }

    public static String idQuery(String id){ return String.format(requestIdUrl, id); }

    public static boolean verifyOffer(String id) throws Exception{
        HttpURLConnection conn = (HttpURLConnection) new URL(idQuery(id)).openConnection();
        if(conn.getResponseCode() == 200) { //dopo lo status code 299 la richiesta o è reindirizzata o non può esser soddisfatta
            System.out.println("Le offerte selezionate precedentemente sono ancora presenti"); // presenta finestra pop-up sulla GUI
            return true;
        }
        System.out.println(conn.getResponseCode());
        System.out.println("Metodo per modificare la richiesta dicendo che è stata modificata");
        return false;
    }

    //metodi per leggere e modificare il file di configurazione
    public static String readConfigurationFile() throws IOException {
        FileInputStream ip = new FileInputStream(configFile);
        prop.load(ip);
        baseUrl = prop.getProperty("url");
        requestIdUrl = prop.getProperty("searchidurl");
        Date data = new Date();
        return prop.getProperty("message")+"\nHa effettuato l'accesso: " +prop.getProperty("user", "localhost")+
                "\nTramite un dispositivo: " +prop.getProperty("OS")+
                "\nIl giorno: " + data;
    }

    public void setConfigurationFile(String tmp) throws IOException{
        FileWriter writer = new FileWriter(getConfigFile());
        prop.setProperty("searchidurl", "https://jobs.github.com/positions/%s.json");
        prop.setProperty("url", "https://jobs.github.com/positions.json?");
        prop.setProperty("message", "Welcome to Femto");
        prop.setProperty("name", tmp);
        Date data = new Date();
        prop.store(writer, "Ultima modifica: " +data);

    }
}