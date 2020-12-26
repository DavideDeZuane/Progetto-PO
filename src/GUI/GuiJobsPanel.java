package GUI;

import Controller.ApiController;
import Model.Job;
import Model.JobBoard;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.util.Iterator;

public interface GuiJobsPanel {
    public void createTable(JTable tableJobs, JobBoard job, int width, int height);
    public void createTable(JTable tableJobs, JobBoard job, int width, int height, Object[] columnHeaders);
    public Object[][] readTable(int numJobs) throws IOException, ClassNotFoundException;
    public JTable getTableJobs();
    public void setTableJobs(JTable tableJobs);
    public void setPanel(int width, int height);
    public Object[][] setTable(Iterator iterator, int raws, int columns);
    public void search(PickedJobs job, PickedJobs pickedJobs, JTextField txtLocation1, JTextField txtLocation2, JTextField txtDescription, JCheckBox fullTime);
    public void showHowToApply(PickedJobs job, int index);
    public void showInternetPage(PickedJobs job, int index);
    public void saveJob(PickedJobs job, PickedJobs pickedJobs, int index);
    public void saveAllJobs(PickedJobs job, PickedJobs pickedJobs);
    public void deleteJobs(PickedJobs job, int index);
    public void deleteAllJobs(PickedJobs job);
}
