package Model;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StatsJobBoard extends JobBoard{

    public StatsJobBoard(){
        super();
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
                //DateTimeFormatter f = DateTimeFormatter.forPattern("EEE MMM dd hh:mm:ss ZZZ yyyy");
                //LocalDateTime dateTime = f.parseLocalDateTime("2012-01-10 23:13:26");
                //DateFormat df = new SimpleDateFormat;//
                //DateFormat.LONG
                //Date date = df.parse(j.getCreated_at());
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

    public int[]  dateOfCreationBis()
    {
        if (checkJobSize())
            return null;
        else
            {
        int [] vector = new int[30];
        for(Job j : jobs)
        {
            int period = 30;
            Date date = new Date(j.getCreated_at());
            long diffInMillies = Math.abs(new Date(System.currentTimeMillis()).getTime() - date.getTime());
            int diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff<29)
                vector[diff]++;
        }
        return vector;
            }
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
        if(super.checkJobSize())
            return -1;
        else
        {
            for (Job j : super.jobs)
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