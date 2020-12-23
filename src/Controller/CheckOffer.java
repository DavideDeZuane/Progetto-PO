package Controller;

import Exception.NoJobsException;
import GUI.GuiJobsPanelMenagement;
import Model.Job;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;



public class CheckOffer extends Controller implements Runnable{
    private HashSet<Job> saved = new HashSet<>();

    private Object[][] table;

    public CheckOffer(){}


    private final int COLUMNS = 5;
    //private GuiJobsPanelMenagement guiJobsPanelMenagement = new GuiJobsPanelMenagement();


    public Object[][] getTable() {
        return table;
    }

    public void setTable(Object[][] table) {
        this.table = table;
    }

    public void save(HashSet<Job> jobs) throws IOException{
        //saved = new HashSet<>();
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
        if(this.saved.isEmpty())
        {
            throw new NoJobsException("Non sono ancora stati salvati lavori");
            /*
            for (Job j : this.saved) {
                if (!HttpConn(j.getId())) { //se non è stata stabilita la connessione
                    System.out.println("Offerta modificata");
                } else
                    System.out.println("Offerta invariata");
            }*/
        }
    }

/*
    Object[][] rowData = new String[raws][columns];

        for(int i = 0; i < raws; i++) {
        tmp = (Job) iterator.next();
        rowData[i][count++] = tmp.getType();
        rowData[i][count++] = tmp.getCompany();
        rowData[i][count++] = tmp.getLocation();
        rowData[i][count++] = tmp.getTitle();

        if(checkOffer.verify(tmp)){
            rowData[i][count++] = "Unchanged Offert";
        }
        else{
            rowData[i][count++] = "Modified Offert";
        }


        count = 0;
    }
        return rowData;*/

    public boolean verify(Job job) {

        if (HttpConn(job.getId())) {
            return true;
        } else
            return false;
    }

    @Override
    public void run() {



        System.out.println(Thread.currentThread().getName());
        save();
        //try {
        try {


            verify();
            table = upDateTable();




            System.out.println(upDateTable().toString());

        } catch (NoJobsException e) {
            e.printStackTrace();
        }


        /*} catch (NoJobsException e) {
            return;
        }*/





    }

    public Object[][] upDateTable(){
        Job tmp = null;
        int count = 0;

        Iterator iterator = this.saved.iterator();

        Object[][] rowData = new String[this.saved.size()][COLUMNS];

        for(int i = 0; i < this.saved.size(); i++) {
            tmp = (Job) iterator.next();
            rowData[i][count++] = tmp.getType();
            rowData[i][count++] = tmp.getCompany();
            rowData[i][count++] = tmp.getLocation();
            rowData[i][count++] = tmp.getTitle();

            if(this.verify(tmp)){
                rowData[i][count++] = "Unchanged Offert";
            }
            else{
                rowData[i][count++] = "Modified Offert";
            }


            count = 0;
        }
        return rowData;
    }
}