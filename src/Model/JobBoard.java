package Model;

import java.net.URL;
import java.util.HashSet;
import java.util.Vector;

public class JobBoard {

    protected HashSet<Job> jobs;
    protected int numOfJobs;
    protected float fullTimePercentage;
    protected String dateOfCreation;
    protected String keyWord;

    /**
     * constructor
     */
    public JobBoard(){
        jobs = new HashSet<>();
        numOfJobs=0;
        fullTimePercentage=-1;
    }

    /**
     * this method gets the HashSet that contains all the jobs
     * @return the HashSet of jobs
     */
    public HashSet<Job> getJobs() {
        return jobs;
    }

    /**
     *
     * @param index index of the hashset
     * @param offers HashSet that contains all the offers
     * @return a certain Job at the specified index of the HashSet offers
     */
    public Job getJob(int index, HashSet<Job> offers) {
        Vector<Job> jobs = new Vector<>();
        jobs.addAll(offers);
        return jobs.elementAt(index);
    }

    /**
     * this method sets the HashSet of type Job
     * @param temp HashSet of type Job
     */
    public void setJobs(HashSet<Job> temp){ this.jobs=temp; }

    /**
     * this method gets the key word
     * @return the key word
     */
    public String getKeyWord(){
        return this.keyWord;
    }

    /**
     * this method sets the key word
     * @param keyWord key word to set
     */
    public void setKeyWord(String keyWord){
        this.keyWord = keyWord;
    }


    /**
     * this method prints out the HashSet of Job
     * @return the HashSet of Job
     */
    @Override
    public String toString() {
        return "JobBoard{" +
                "jobs=" + jobs +
                '}';
    }

    /**
     * this method gets the number of jobs in the HashSet
     * @return the number of jobs contained in the HashSet
     */
    public int getNumOfJobs() {
        return jobs.size();
    }

    /**
     * this method checks if the HashSet is empty
     * @return true is the HashSet is empty, false otherwise
     */
    public boolean checkJobSize ()
    {
        return jobs.size() == 0;
    }

    /**
     * this method gets the company url of Job at a specified index
     * @param index index of a Job
     * @param offer HashSet of type Job
     * @return the company url of a Job at the specified index
     */
    public URL getCompany_url(int index, HashSet<Job> offer) {
        Vector<Job> jobs = new Vector<>();
        jobs.addAll(offer);
        return jobs.elementAt(index).getCompany_url();
    }

    /**
     *
     * this method gets the how to apply of Job at a specified index
     * @param index index of a Job
     * @param offer HashSet of type Job
     * @return the how to apply of a Job at the specified index
     */
    public String getHow_to_apply(int index, HashSet<Job> offer) {
        Vector<Job> jobs = new Vector<>();
        jobs.addAll(offer);
        return jobs.elementAt(index).getHow_to_apply();
    }
}