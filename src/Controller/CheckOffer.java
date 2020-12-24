package Controller;

import Exception.NoJobsException;
import Model.Job;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;


public class CheckOffer extends Controller implements Runnable{
    private HashSet<Job> saved = new HashSet<>();

    public CheckOffer(){}

    public void save(HashSet<Job> jobs) throws IOException{
        this.saved = jobs;
        try {
            FileController file = new FileController("PickedJobs.txt");
            file.readJobsFromFile(this.saved);
        }catch (IOException e)
        {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public void save(){
        try {
            FileController file = new FileController("PickedJobs.txt");
            file.readJobsFromFile(this.saved);
        }catch (IOException e)
        {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public static boolean HttpConn(String id)
    {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(idQuery(id)).openConnection();
            if (conn.getResponseCode() == 200) //controlla se la richiesta è ok (quando il codice di risposta è 200 la connessione è stata stabilita correttamente)
                return true;
        }catch (IOException ex)
        {
            System.out.println("Connessione non stabilita");
        }
        return false;
    }

    public void verify() throws NoJobsException {
    if(this.saved != null)
    {
            for (Job j : this.saved) {
                if (!HttpConn(j.getId())) { //se non è stata stabilita la connessione
                    System.out.println("Offerta modificata");
                } else
                    System.out.println("Offerta invariata");
            }
            return;

        } throw new NoJobsException("Non sono ancora stati salvati lavori");
    }

    public static boolean verify(Job job) {

        if (!HttpConn(job.getId())) {
            return false;
        } else
            return true;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        save();
        try {
            verify();
        } catch (NoJobsException e) {
            return;
        }
    }
}