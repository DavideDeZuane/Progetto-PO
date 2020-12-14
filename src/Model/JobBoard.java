package Model;

import java.util.HashSet;

/**
 * @author Chiara
 */
public class JobBoard {

    protected HashSet<Job> jobs;
    protected int numOfJobs;
    protected float fullTimePercentage;
    protected String dateOfCreation;
    protected String keyWord;

    /**
     * constructor
     */
    public JobBoard() {
    }

    public HashSet<Job> getJobs() {
        return jobs;
    }

    public int getNumOfJobs() {
        return numOfJobs;
    }

    public float typeStats ()
    {
        //Iterator<Job> it = jobs.iterator();
        return fullTimePercentage;
    }

    //todo elaborare le statistiche:
    //- % tipo
    //- datacreazione
    //- parole chiave

}
