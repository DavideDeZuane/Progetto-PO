package Model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Chiara
 */
public class PickedJobs extends JobBoard{

    public PickedJobs() throws IOException {
        //se il file non esiste, lo creo
        //apro il file e carico i lavori salvati
        /*try
        {
            int next;
            FileReader reader = new FileReader("job.txt");
            reader.close();
        }catch (FileNotFoundException e)
        {
            System.out.println("Il file è in fase di creazione...");
        }
        finally {
            FileWriter writer = new FileWriter("jobs.txt");
            writer.close();
        }*/
    }

    public void saveOnFile() throws IOException {
        //copia la attuale versione di picked jobs su file
        /*FileWriter writer = new FileWriter("jobs.txt");
        writer.write(jobs.toString());*/

    }

    public void deleteAll () throws FileNotFoundException {
        /*PrintWriter writer = new PrintWriter("jobs.txt");
        writer.print("");
        writer.close();*/
        //cancella tutti i lavori
    }

    public void add(Job job)
    {

        //aggiunge un lavoro al'hash e salva
    }

    public void deleteJob(Job job)
    {

        //elimina un lavoro
    }
}
