package Model;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
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

    public void setJobs(HashSet<Job> temp){ this.jobs=temp; }

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

    //questa funzione calcola la percentuale di lavori full time rispetto a quelli part time
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

    //questa funzione genera le statistiche in base a quanti lavori sono stati caricati sull'hashset period giorni fa
    public int dateOfCreation(int period) throws ParseException {
        int recent = 0;
        if(checkJobSize())
            return -1;
        else
        {
            for (Job j : jobs)
            {

                //DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss ZZZ yyyy");
               // Date date = df.parse(j.getCreated_at());
                Date date = new Date(j.getCreated_at());
                long diffInMillies = Math.abs(new Date(System.currentTimeMillis()).getTime() - date.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if(diff<=period)
                    recent++;
                /*DateFormat alfrescoDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date dataRispostaDate = alfrescoDateFormat.parse(j.getCreated_at());*/
                /*DateFormatter formatter = new DateFormatter();
                formatter.stringToValue(j.getCreated_at());*/
            }
        }
        return recent;
    }

    /*//questa funzione genera statistiche in base a quale parole chiavi l'utente immette
    // restituendo il numero di occorrenze di quella parola chiave nella description del lavoro
    public int keyWords (String word)
    {
        int count = 0;

        if(jobs.size()==0)
            return -1;
        else
        {
            for (Job j : jobs)
            {

                String s =j.getDescription();
                //s = System.Text.RegularExpressions.Regex.Replace(s, "<[^>]*>","");
                s = s.replaceAll("\\<.*?\\>", "");
                s = s.replaceAll("\\n","");
                s = s.replaceAll("[.,]","");
                // split the string by spaces in a
                String a[] = s.split(" ");
                for (String value : a) {
                    if (word.equals(value))
                        count++;
                }

            }
        }
        return count;

    }*/

    //questa funzione genera statistiche in base a quale parole chiavi l'utente immette
    // restituendo il numero di occorrenze di quella parola chiave nella description del lavoro
    //(case sensitive case)
    public int keyWords (String word/*, boolean cs*/)
    {
        int count = 0;
        if(checkJobSize())
            return -1;
        else
        {
            for (Job j : jobs)
            {

                String s =j.getDescription();
                //s = System.Text.RegularExpressions.Regex.Replace(s, "<[^>]*>","");
                s = s.replaceAll("\\<.*?\\>", "");
                s = s.replaceAll("\\n","");
                s = s.replaceAll("[.,]","");
                // split the string by spaces in a
                String a[] = s.split(" ");

                // search for pattern in a

                for (String value : a) {
                    // if match found increase count
                    //if (!cs)
                    {
                        //word=word.toLowerCase();
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
