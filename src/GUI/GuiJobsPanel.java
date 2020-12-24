package GUI;

import Model.JobBoard;
import Model.PickedJobs;

import javax.swing.*;
import java.util.Iterator;

/**
 * interface
 */
public interface GuiJobsPanel {

    /**
     * @param tableJobs object of JTable class
     * @param job object of JobBoard class
     */
    public abstract void createTable(JTable tableJobs, JobBoard job, int width, int height);

    /**
     * @return a JTable object
     */
    public abstract JTable getTableJobs();

    /**
     * @param tableJobs object of JTable class
     */
    public abstract void setTableJobs(JTable tableJobs);

    /**
     * @param iterator object of Iterator type
     * @param raws number of rows
     * @param columns number of columns
     * @return a matrix of Object
     */
    public abstract Object[][] setTable(Iterator iterator, int raws, int columns);

    /**
     * @param job object of PickedJob class
     */
    public abstract void showHowToApply(PickedJobs job);

    /**
     * @param job object of PickedJob class
     * @param pickedJobs object of PickedJob class
     */
    public abstract void saveJob(PickedJobs job, PickedJobs pickedJobs);
}
