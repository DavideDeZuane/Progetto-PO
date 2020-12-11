package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PickedJobs {
    BufferedWriter writer = new BufferedWriter(new FileWriter("PickedJobs.txt"));

    public PickedJobs() throws IOException {

    }
}
