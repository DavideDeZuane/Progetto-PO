package Controller;

import Model.Job;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

//todo usare il mapper di ApiController
//todo classe per leggere da file configuration file

public class FileController {

    //questa classe apre i buffer di input e output per
    //salavre e leggere i dati sui lavori trovati e sui
    //lavori salvati dall'utente

    private String filename;
    private File file;
    private ApiController controller;

    /**
     *
     * @param filename nome del file su cui scrivo
     * @throws IOException genera un'eccezione se le I/O operations falliscono
     */
    public FileController(String filename) throws IOException {
        this.filename = filename;
        file = new File(filename);
        if(!file.exists())
            file.createNewFile();
    }

    /**
     *
     * @param jobs hashset di Job
     * @throws IOException genera un'eccezione se le I/O operations falliscono
     */
    public void saveJobsOnFile(HashSet<Job> jobs) throws IOException {
        if(jobs.isEmpty())
            System.out.println("Non ci sono lavori disponibili");
        else {
            //controller = new ApiController();
            /*JsonMapper mapper = new JsonMapper();
            mapper.writeValue(file, jobs);*/
            /*ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, jobs);*/
            controller = new ApiController();
            controller.getMapper().writeValue(file, jobs);
        }
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public HashSet<Job> readJobsFromFile() throws IOException {
        if(file.length()==0)
        {
            System.out.println("Non ci sono lavori disponibili");
            return null;
        }
        else {
                /*ObjectMapper mapper = new ObjectMapper();
                //return mapper.readValue(file, new TypeReference<HashSet<Job>>(){});
                //InputStream input = new FileInputStream(filename);
                return mapper.readValue(file, new TypeReference<>() {
                });*/
                controller = new ApiController();
                return controller.getMapper().readValue(file, new TypeReference<HashSet<Job>>() {});
            }
    }



    public void deleteAll () throws FileNotFoundException {
        //cancella tutti i lavori
        PrintWriter writer = new PrintWriter("jobs.txt");
        writer.print("");
        writer.close();
    }

    /*public void  readFile(String filename) throws FileNotFoundException {

    }*/


}
