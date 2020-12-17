package Model;

import Controller.ApiController;
import Controller.FileController;

import java.io.IOException;

/**
 * @author Chiara
 */
public class PickedJobs extends JobBoard{

    private FileController fileController;

    public PickedJobs() throws IOException {
        fileController = new FileController("PickedJobs.txt");

        //this.jobs = new HashSet<>();
        if (fileController.readJobsFromFile()!=null)
            jobs.addAll(fileController.readJobsFromFile());
    }
//bdihcbeihbcihebrihcbeibci
    //aggiunge un lavoro all'hash e salva
    public void add(Job job) throws IOException {
        for(Job j : jobs)
            fileController.saveJobsOnFile(this.jobs);
        if(!jobs.contains(job))
        {
            jobs.add(job);
            fileController.saveJobsOnFile(this.jobs);
        }
    }

    //elimina un lavoro
    public void deleteJob(String id) throws IOException {
        boolean found = false;
        for(Job j : jobs)
            if(j.getId().equals(id))
            {
                jobs.remove(j);
                fileController.saveJobsOnFile(jobs);
                return;
            }
    }

    public void Update() throws Exception {
        //scorre jobs e per ogni lavoro se non è più presente lo cancella
        for(Job j : jobs)
        {
            if (!ApiController.verifyOffer(j.getId()))
            {
                this.deleteJob(j.getId());
                fileController.saveJobsOnFile(jobs);
            }
        }

    }
}
