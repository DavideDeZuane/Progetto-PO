package Controller;

import Model.Job;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;

public class CheckOffer extends ApiController implements Runnable{
    private HashSet<Job> saved;

    public CheckOffer(){}

    @Override
    public String[] setFilters(JTextField txtLocation, JTextField txtDescription, boolean banner) {
        String [] id = new String[10];
        return id;
    }

    public void fill(){
        try {
            FileController file = new FileController("PickedJobs.txt");
            this.saved = file.readJobsFromFile();
        }catch (IOException e)
        {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public boolean HttpConn (String id)
    {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(idQuery(id)).openConnection();
            if (conn.getResponseCode() == 200)
                return true;
        }catch (IOException ex)
        {
            System.out.println("Connessione non stabilita");
        }
        return false;
    }

    public void verify() {
        try {
            for (Job j : this.saved) {
                if (!HttpConn(j.getId())) {
                    System.out.println("Offerta modificata");
                } else
                    System.out.println("Offerta invariata");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        fill();
        verify();
    }
}
