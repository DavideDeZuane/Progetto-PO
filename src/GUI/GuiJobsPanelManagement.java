package GUI;

import Model.Job;
import Model.JobBoard;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Iterator;

public class GuiJobsPanelManagement extends GuiManagement implements GuiJobsPanel{

    private JTable tableJobs;
    private final static int COLUMNS = 4;

    private int buffer = -2;

    private Object[] columnHeaders = {"Type","Company","Location","Title"};

    /**
     * constructor
     * @param panel panel of JPanel type
     * @param namePanel name of the panel
     */
    public GuiJobsPanelManagement(JPanel panel, String namePanel){
        super(panel, namePanel);
        //this.panel = panel;
        //this.namePanel = namePanel;
    }

    /**
     * this method creates a table
     * @param tableJobs object of JTable class
     * @param job object of JobBoard class
     */
    public void createTable(JTable tableJobs, JobBoard job, int width, int height){
        this.tableJobs = tableJobs;
        this.tableJobs = new JTable(this.setTable(job.getJobs().iterator(), job.getNumOfJobs(), COLUMNS), columnHeaders);
        this.tableJobs.setPreferredScrollableViewportSize(new Dimension(500,50));
        this.tableJobs.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(this.tableJobs);
        add(scrollPane);
        setSize(width, height);
        setVisible(true);
    }

    /**
     * this method gets a object of JTable type
     * @return a object of JTable type
     */
    public JTable getTableJobs() {
        return tableJobs;
    }

    /**
     * this method sets a object of JTable class
     * @param tableJobs object of JTable class
     */
    public void setTableJobs(JTable tableJobs) {
        this.tableJobs = tableJobs;
    }

    /**
     * this method sets a panel by specifying the width and the height
     * @param width integer that represents the width of the table
     * @param height integer that represents the height of the table
     */
    public void setPanel(int width, int height){
        add(super.getPanel());
        setTitle(super.getNamePanel());
        setSize(width, height);
        setResizable(false);
        setVisible(true);

    }

    /**
     * this method sets a table with the type, the company, the location and the title of a job
     * @param iterator object of Iterator type
     * @param raws number of rows
     * @param columns number of columns
     * @return
     */
    public Object[][] setTable(Iterator iterator, int raws, int columns){
        Job tmp = null;
        int count = 0;

        Object[][] rowData = new String[raws][columns];

        for(int i = 0; i < raws; i++) {
            tmp = (Job) iterator.next();
            rowData[i][count++] = tmp.getType();
            rowData[i][count++] = tmp.getCompany();
            rowData[i][count++] = tmp.getLocation();
            rowData[i][count++] = tmp.getTitle();
            count = 0;
        }
        return rowData;
    }

    /**
     * this method shows the how_to_apply of a selected Job of the table
     * @param job object of PickedJob class
     */
    public void showHowToApply(PickedJobs job){
        try {

            if(job.getHow_to_apply(getTableJobs().getSelectedRow(), job.getJobs()).toString().equals("")){
                JOptionPane.showMessageDialog(super.getPanel(), "The how to apply is not available");
            }
            else{
                Object[] options = { "Copy on clip board", "Exit" };

                int result = JOptionPane.showOptionDialog(super.getPanel(),job.getHow_to_apply(getTableJobs().getSelectedRow(), job.getJobs()) ,"Information",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
                        null, options, options[0]);

                if (result == JOptionPane.YES_OPTION){
                    StringSelection selection = new StringSelection(job.getHow_to_apply(getTableJobs().getSelectedRow(), job.getJobs()));
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                    JOptionPane.showMessageDialog(super.getPanel(),"Text successfully copied to the clip board");
                }

            }

        }catch(Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"     Bro, jobs ain't found");
        }
    }

    /**
     * this method allows the user to pick e job and saves it to file
     * @param job object of PickedJob class
     * @param pickedJobs object of PickedJob class
     */
    public void saveJob(PickedJobs job, PickedJobs pickedJobs){

        try {

            if(job.getJobs().isEmpty()){
                JOptionPane.showMessageDialog(super.getPanel(), "Any job to save.");
            }
            else{
                if(getTableJobs().getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(super.getPanel(), "You have not selected any job.");
                }
                else{
                    if(pickedJobs.getJobs().contains(job.getJob(getTableJobs().getSelectedRow(), job.getJobs())) ){
                        JOptionPane.showMessageDialog(super.getPanel(), "Job is already present in the data base");

                    }
                    else{
                        pickedJobs.add(job.getJob(getTableJobs().getSelectedRow(), job.getJobs()));
                        JOptionPane.showMessageDialog(super.getPanel(), "Job saved successfully in " + pickedJobs.getFileName());
                    }
                }
            }

        }catch(Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"     Bro, jobs ain't found");
            exception.printStackTrace();
        }
    }

    public void saveAllJobs(PickedJobs job, PickedJobs pickedJobs){

        //this.buffer = buffer;

        if(job.getJobs().isEmpty()){
            JOptionPane.showMessageDialog(super.getPanel(), "Any job to save.");
        }else {

            pickedJobs.addAll(job.getJobs());

            if(this.buffer == pickedJobs.getNumOfJobs()){
                JOptionPane.showMessageDialog(super.getPanel(), "Jobs are already present");
            }
            else{
                JOptionPane.showMessageDialog(super.getPanel(), "Jobs saved successfully in " + pickedJobs.getFileName());
                this.buffer = pickedJobs.getNumOfJobs();
            }


        }
    }

}