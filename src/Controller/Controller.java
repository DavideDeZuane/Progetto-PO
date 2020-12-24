package Controller;

import Model.Job;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Properties;

public abstract class Controller {
    protected static EnumSet<Parameters> flags = EnumSet.noneOf(Parameters.class);
    public static String idQuery(String id){ return String.format(ApiController.requestIdUrl, id); }
    public abstract void save(HashSet<Job> jobs) throws IOException;


    protected static String requestIdUrl = "https://jobs.github.com/positions/%s.json";
    protected static String baseUrl = "https://jobs.github.com/positions.json?";
    private static final Properties prop = new Properties();
    private static final File configFile = new File("Resources/Configuration/config.properties");

    /**
     * this method reads from the configFile
     * @throws IOException if the I/O operations fail or get interrupted
     */
    public static void readProp() throws IOException{
        FileInputStream ip = new FileInputStream(configFile);
        prop.load(ip);
        baseUrl = prop.getProperty("url");
        requestIdUrl = prop.getProperty("searchidurl");
        ip.close();
    }

    /**
     * this method sets the configFile
     * @param tmp String that represents the name of the user
     * @throws IOException if the I/O operations fail or get interrupted
     */
    public static void setProp (String tmp) throws IOException{
        FileWriter writer = new FileWriter(configFile);
        prop.setProperty("message", "Welcome to Femto");
        prop.setProperty("name", tmp);
        Date data = new Date();
        prop.store(writer, "Ultima modifica: "+ data);
    }
}