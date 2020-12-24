package Controller;

import Exception.NoJobsException;
import Model.Job;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;


public class CheckOffer extends Controller implements Runnable{
    private HashSet<Job> saved = new HashSet<>();

    private int COLUMS = 5;

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
            Object[][] table = createTable();
            System.out.println(table.toString());
            permanenzatable(table);

            System.out.println(table);
        }catch(NoJobsException | IOException e){
            return;
        }

    }

/*
    public Object[][] readTable() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Update.txt")));
        Object[][] table = new Object[this.saved.size()][COLUMS];
        for (int i = 0; i < this.saved.size(); i++) {
            for (int j = 0; j < COLUMS; j++) {
                table[i][j] = in.readObject();
            }
        }
        return table;
    }*/


    public Object[][] createTable(){
        Job tmp = null;
        int count = 0;
        Iterator it = this.saved.iterator();
        Object[][] rowData = new Object[this.saved.size()][COLUMS];

        for(int i = 0; i < this.saved.size(); i++) {
            tmp = (Job) it.next();
            rowData[i][count++] = tmp.getType();
            rowData[i][count++] = tmp.getCompany();
            rowData[i][count++] = tmp.getLocation();
            rowData[i][count++] = tmp.getTitle();
            if(verify(tmp))
                rowData[i][count++] = "Offerta Invariata";
            else
                rowData[i][count] = "Offerta Cambiata";
            count = 0;
        }
        return rowData;
    }

    public void permanenzatable(Object[][] table) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Update.txt")));
        for(int i = 0; i < this.saved.size(); i++){
            for(int j = 0; j<COLUMS; j++) {
                out.writeObject(table[i][j].toString());
            }
        }
        out.close();
    }
}