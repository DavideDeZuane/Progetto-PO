package Controller;

import Model.Job;

import javax.swing.*;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashSet;

public abstract class Controller {
    protected static EnumSet<Parameters> flags = EnumSet.noneOf(Parameters.class);
    public static String idQuery(String id){ return String.format(ApiController.requestIdUrl, id); }
    public abstract void save(HashSet<Job> jobs) throws IOException;

}
