package Model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StatsJobBoard extends JobBoard{

    /**
     * constructor
     */
    public StatsJobBoard(){
        super();
    }


    /**
     * this method calculates the percentage of full time jobs
     * @return the percentage of full time jobs
     */
    public float calculatePercentage() {
        int cont = 0;
        if(checkJobSize())
            return -1;
        else
        {
            for (Job j : jobs)
            {
                if (j.getType().equals("Full Time"))
                    cont++;
            }
        }
        return fullTimePercentage = (float) ((cont*100)/ jobs.size());
    }

    /**
     * this method finds out how many jobs were created starting from a specified number of days ago
     * @param period number of days
     * @return the number of jobs that were created a specified number of days ago
     */
    public int dateOfCreation(int period) {
        int recent = 0;
        if(checkJobSize())
            return -1;
        else
        {
            for (Job j : jobs)
            {
                Date date = new Date(j.getCreated_at());
                long diffInMillies = Math.abs(new Date(System.currentTimeMillis()).getTime() - date.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if(diff<=period)
                    recent++;
            }
        }
        return recent;
    }

    /**
     * this method creates a vector of 30 doubles, from index 0 to index 30 of the vector there is a correspondent number witch represents
     * the number of jobs created on that day assuming that today is day 0
     * @return a vector of doubles
     */
    public double[] dateOfCreationBis()
    {
        if (checkJobSize())
            return null;
        else
            {
        double [] vector = new double[30];
        for(Job j : jobs)
        {
            int period = 30;
            Date date = new Date(j.getCreated_at());
            long diffInMillies = Math.abs(new Date(System.currentTimeMillis()).getTime() - date.getTime());
            int diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff<30)
                vector[diff]++;
        }
        return vector;
            }
    }

    /**
     * this method takes as input a string witch represents a word the user wants to search for, the method returns the number of occurrences
     * of that word in the jobs descriptions
     * @param word word the user wants to search for in the jobs descriptions
     * @return the number of occurrences of a certain word
     */
    public int keyWords (String word)
    {
        int count = 0;
        if(super.checkJobSize())
            return -1;
        else
        {
            for (Job j : super.jobs)
            {

                String s =j.getDescription();
                s = s.replaceAll("\\<.*?\\>", "");
                s = s.replaceAll("\\n","");
                s = s.replaceAll("[.,]","");

                String a[] = s.split(" ");


                for (String value : a) {

                    {
                        value=value.toLowerCase();
                    }
                    if (word.equals(value))
                        count++;
                }
            }
        }
        return count;
    }

}