package GUI;

import Model.JobBoard;
import Model.PickedJobs;

import javax.swing.*;
import java.util.Iterator;

public interface GuiJobsPanel {
    public abstract void createTable(JTable tableJobs, JobBoard job);
    public abstract JTable getTableJobs();
    public abstract void setTableJobs(JTable tableJobs);
    public abstract Object[][] setTable(Iterator iterator, int raws, int columns);
    public abstract void showHowToApply(PickedJobs job);
    public abstract void saveJob(PickedJobs job, PickedJobs pickedJobs);
}
