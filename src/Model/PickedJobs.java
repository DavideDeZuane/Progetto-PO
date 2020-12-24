package Model;

import Controller.FileController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;


public class PickedJobs extends JobBoard {

    private String fileName = "PickedJobs.txt";
    private FileController fileController = new FileController(fileName);

    /**
     * constructor of the class
     * @throws IOException
     */
    public PickedJobs() throws IOException {
        super();
        //fileController = new FileController(fileName);
        if (this.jobs == null) {
            fileController.readJobsFromFile(this.jobs);
        }
    }

    /**
     * this method prints out the file name
     * @return the file name where the jobs will be printed on
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * this method sets the name of the file the jobs will be printed on
     * @param fileName file name where the jobs will be printed on
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * this method adds a jobs to the HashSet and saves it to the file
     * @param job job to add to the hashset
     * @throws IOException is the I/O operations fail or get interrupted
     */
    public void add(Job job) throws IOException {

        for (Job j : jobs)
            fileController.save(this.jobs);
            if (!jobs.contains(job)) {
                jobs.add(job);
                fileController.save(this.jobs);
            }

    }

    /**
     * this method deletes a jobs from the hashset on from the txt file
     * @param id id of the job that will be deleted
     * @throws IOException is the I/O operations fail or get interrupted
     */
    public void deleteJob(String id) throws IOException {
        boolean found = false;
        for (Job j : jobs)
            if (j.getId().equals(id)) {
                jobs.remove(j);
                fileController.save(jobs);
                return;
            }
    }

    /**
     * this method adds all the jobs to the hashset
     * @param jobs HashSet of type Job to fill
     */
    public void addAll(HashSet<Job> jobs){
        for(Job j : jobs){
            try {
                this.add(j);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * this method deletes all the jobs from the txt file
     */
    public void deleteAll(){
        try {
            fileController.empty();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}