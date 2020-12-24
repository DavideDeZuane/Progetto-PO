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


public class FileController {

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
     * this method saves as json the jobs contained in a HashSet to file
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
     * this method reads the json from file and populates a HashSet with it
     * @param jobs HashSet that will be filled
     * @throws IOException generates an exception if the I/O operations fail
     */
    public void readJobsFromFile(HashSet<Job> jobs) throws IOException {
        if(file.length()==0)
        {
            System.out.println("Non ci sono lavori disponibili sul file");
            //return null;
        }
        else {
            ObjectMapper mapper = new ObjectMapper();
            jobs.addAll(mapper.readValue(file, new TypeReference<HashSet<Job>>() {}));
        }
    }

    /**
     * this method deletes all the jobs from file
     * @throws FileNotFoundException if the file is not found
     */
    public void empty () throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("PickedJobs.txt");
        writer.print("");
        writer.close();
    }
}