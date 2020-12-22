package Controller;

import Model.Job;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
    private ApiController controller = new ApiController();

    /**
     * the constructor creates a new file if it does not exist
     * @param filename name of the file I write on
     * @throws IOException generates an exception if the I/O operations fail
     */
    public FileController(String filename) throws IOException {
        this.filename = filename;
        file = new File(filename);
        if(!file.exists())
            file.createNewFile();
    }

    /**
     *
     * @param jobs hashset of Job
     * @throws IOException generates an exception if the I/O operations fail
     */
    public void save(HashSet<Job> jobs) throws IOException {
        if(jobs.isEmpty())
            System.out.println("Non ci sono lavori disponibili");
        else {
            controller.getMapper().writeValue(file, jobs);
            ObjectWriter writer = controller.getMapper().writer(new DefaultPrettyPrinter());
            writer.writeValue(file, jobs);
            //FileController fin = new FileController(filename);
        }
    }

    /**
     *
     * @return a HashSet of Job
     * @throws IOException generates an exception if the I/O operations fail
     */
    public void readJobsFromFile(HashSet<Job> jobs) throws IOException {
        if(file.length()==0)
        {
            System.out.println("Non ci sono lavori disponibili");
            //return null;
        }
        else {
            ObjectMapper mapper = new ObjectMapper();
            jobs.addAll(mapper.readValue(file, new TypeReference<HashSet<Job>>() {}));
            //return mapper.readValue(file, new TypeReference<HashSet<Job>>() {});//controller.getMapper().readValue(file, new TypeReference<HashSet<Job>>() {});
        }
    }

    /**
     *
     * @throws FileNotFoundException generates an exception if the file is not found
     */
    public void deleteAll () throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("PickedJobs.txt");
        writer.print("");
        writer.close();
    }
}