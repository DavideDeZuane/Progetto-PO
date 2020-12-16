package Controller;

import Model.Job;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.*;
import java.util.HashSet;

public class FileController {

    //questa classe apre i buffer di input e output per
    //salavre e leggere i dati sui lavori trovati e sui
    //lavori salvati dall'utente

    //private FileReader reader;
    //private FileWriter writer;
    private String filename;
    private File file;

    public FileController(String filename) throws IOException {
        //se il file non esiste, lo creo
        this.filename = filename;
        file = new File(filename);
        if(!file.exists())
            file.createNewFile();
    }

    public void saveJobsOnFile(HashSet<Job> jobs) throws IOException {
        if(jobs.isEmpty())
            System.out.println("Non ci sono lavori disponibili");
        else {
            JsonMapper mapper = new JsonMapper();
            //FileOutputStream stream = new FileOutputStream(file);
            //JsonGenerator g = mapper.getFactory().createGenerator(stream);
            //mapper.writeValue(g, jobs);
            //g.close();
            mapper.writeValue(file, jobs);
        }
    }

    public HashSet<Job> readJobsFromFile() throws IOException {
        if(file.length()==0)
        {
            System.out.println("Non ci sono lavori disponibili");
            return null;
        }
        else {
                ObjectMapper mapper = new ObjectMapper();
                //return mapper.readValue(file, new TypeReference<HashSet<Job>>(){});
                //InputStream input = new FileInputStream(filename);
                return mapper.readValue(file, new TypeReference<HashSet<Job>>(){});
            }
    }



    public void deleteAll () throws FileNotFoundException {
        //cancella tutti i lavori
        PrintWriter writer = new PrintWriter("jobs.txt");
        writer.print("");
        writer.close();
    }

    /*public void saveId(HashSet<Job> jobs) throws IOException {
        for(Job j : jobs)
        {
            writer.write(j.getId());
        }
    }*/

}
