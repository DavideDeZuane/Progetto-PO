package Model;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

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
    public JobBoard(){
        jobs = new HashSet<>();
        numOfJobs=0;
        fullTimePercentage=-1;
    }

    public HashSet<Job> getJobs() {
        return jobs;
    }

    /**
     *
     * @param index
     * @param offerte
     * @return
     */
    public Job getJob(int index, HashSet<Job> offerte) {
        Vector<Job> jobs = new Vector<>();
        jobs.addAll(offerte);
        return jobs.elementAt(index);
    }

    public void setJobs(HashSet<Job> temp){ this.jobs=temp; }

    public String getKeyWord(){
        return this.keyWord;
    }

    public void setKeyWord(String keyWord){
        this.keyWord = keyWord;
    }


    @Override
    public String toString() {
        return "JobBoard{" +
                "jobs=" + jobs +
                '}';
    }

    /**
     *
     * @return
     */
    public int getNumOfJobs() {
        return jobs.size();
    }

    public boolean checkJobSize ()
    {
        return jobs.size() == 0;
    }

    public URL getCompany_url(int index, HashSet<Job> offerte) {
        Vector<Job> jobs = new Vector<>();
        jobs.addAll(offerte);
        return jobs.elementAt(index).getCompany_url();
    }

    public String getHow_to_apply(int index, HashSet<Job> offerte) {
        Vector<Job> jobs = new Vector<>();
        jobs.addAll(offerte);
        return jobs.elementAt(index).getHow_to_apply();
    }


    /**
     *
     * @param iterator
     * @param raws
     * @param columns
     * @return
     */
    public Object[][] setTableJobs(Iterator iterator, int raws, int columns){
        Job tmp = null;
        int count = 0;

        Object[][] rowData = new String[raws][columns];

        for(int i = 0; i < raws; i++) {
            tmp = (Job) iterator.next();
            rowData[i][count++] = tmp.getType();
            rowData[i][count++] = tmp.getCompany();
            rowData[i][count++] = tmp.getLocation();
            count = 0;
        }
        return rowData;
    }



}
