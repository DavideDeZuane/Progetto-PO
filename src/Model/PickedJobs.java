package Model;

import Controller.FileController;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author Chiara
 */
public class PickedJobs extends JobBoard {

    private String fileName = "PickedJobs.txt";
    private FileController fileController = new FileController(fileName);

    public PickedJobs() throws IOException {
        super();
        //fileController = new FileController(fileName);
        if (this.jobs == null) {
            fileController.readJobsFromFile(this.jobs);
        }
    }

    //aggiunto da ben
    /*
    public boolean setJobsFromFile() throws IOException {
        fileController = new FileController(fileName);

        if(fileController.readJobsFromFile() == null){
            return false;
        }
        else{
            jobs.addAll(fileController.readJobsFromFile());
            return true;
        }
    }*/

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //bdihcbeihbcihebrihcbeibci
    //aggiunge un lavoro all'hash e salva
    public void add(Job job) throws IOException {

        for (Job j : jobs)
            fileController.save(this.jobs);
            if (!jobs.contains(job)) {
                jobs.add(job);
                fileController.save(this.jobs);
            }



    }

    //elimina un lavoro
    public void deleteJob(String id) throws IOException {
        boolean found = false;
        for (Job j : jobs)
            if (j.getId().equals(id)) {
                jobs.remove(j);
                fileController.save(jobs);
                return;
            }
    }

    public boolean add(PickedJobs job, int index) throws IOException {
        boolean flag = true;

        for(Job j : this.getJobs()){
            if(j.getId().equals(job.getJob(index, job.getJobs()).getId())){
                flag = false;
            }
        }

        return flag;
    }

    public void addAll(PickedJobs job){

        for(Job j : job.getJobs()){
            jobs.add(j);
        }

        try {
            fileController.save(jobs);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        /*
        boolean flag = true;

        for(Job i : job.getJobs()){
            flag = true;
            for(Job j : this.getJobs()){
                if(j.getId().equals(i.getId())) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                try {
                    add(i);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

        }*/

    }

    /*
    public void Update() throws Exception {
        //scorre jobs e per ogni lavoro se non è più presente lo cancella
        for (Job j : jobs) {
            if (!CheckOffer.verify(j)) {
                this.deleteJob(j.getId());
                fileController.save(jobs);
            }
        }
    }*/
/*
    public Boolean verifyJobsPresent(){

        try {
            if(fileController.readJobsFromFile() == null)
                return false;
            else{
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }
*/
    public void deleteAll(){
        try {
            fileController.empty();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public Job searchForJob(String id)
    {
        for(Job j : this.jobs)
        {
            if(j.getId().equals(id)) return j;
        }
        return null;
    }*/
}