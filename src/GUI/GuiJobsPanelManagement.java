package GUI;

import Controller.ApiController;
import Controller.CheckOffer;
import Controller.FileController;
import Controller.GuiController;
import Model.Job;
import Model.JobBoard;
import Model.PickedJobs;

import Exception.GuiOptionPaneException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Iterator;

import static Controller.CheckOffer.verify;

public class GuiJobsPanelManagement extends GuiManagement implements GuiJobsPanel{

    private JTable tableJobs;
    private final static int COLUMNS = 4;

    private int COLUMSUPDATE = 5;


    CheckOffer checkOffer = new CheckOffer();


    private Object[] columnHeaders = {"Type","Company","Location","Title"};

    private ApiController apiController = new ApiController();
    private GuiController guiController = new GuiController();

    private Desktop desktop = Desktop.getDesktop();

    public GuiJobsPanelManagement(JPanel panel, String namePanel){
        super(panel, namePanel);

        //apiController = new ApiController();
        //guiController = new GuiController();
    }

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

    //overloading
    public void createTable(JTable tableJobs, JobBoard job, int width, int height, Object[] columnHeaders){
        this.tableJobs = tableJobs;


        try {
            this.tableJobs = new JTable(this.readTable(job.getJobs().size()), columnHeaders);
            this.tableJobs.setPreferredScrollableViewportSize(new Dimension(500,50));
            this.tableJobs.setFillsViewportHeight(true);


            JScrollPane scrollPane = new JScrollPane(this.tableJobs);
            add(scrollPane);
            setSize(width, height);
            setVisible(true);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(super.getPanel(), "Bro, please wait! I am checking jobs status.");

            //exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //this.tableJobs = new JTable(this.setTable(job.getJobs().iterator(), job.getNumOfJobs(), COLUMNS), columnHeaders);

    }

    public Object[][] readTable(int numJobs) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Update.txt")));
        Object[][] table = new Object[numJobs][5];
        for (int i = 0; i < numJobs; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j] = in.readObject();
            }
        }
        return table;
    }

    public JTable getTableJobs() {
        return tableJobs;
    }

    public void setTableJobs(JTable tableJobs) {
        this.tableJobs = tableJobs;
    }

    public void setPanel(int width, int height){
        add(super.getPanel());
        setTitle(super.getNamePanel());
        setSize(width, height);
        setResizable(false);
        setVisible(true);

    }

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

    public void search(PickedJobs job, PickedJobs pickedJobs, JTextField txtLocation1, JTextField txtLocation2, JTextField txtDescription, JCheckBox fullTime){

        job.getJobs().clear();

        boolean flag = false;

        try {

            job.setKeyWord(txtDescription.getText());


            if(!txtLocation1.getText().equals(txtLocation2.getText())) {
                if (!txtLocation1.getText().equals("")) {
                    apiController.setUrl(ApiController.query(guiController.setFilters(txtLocation1, txtDescription, fullTime.isSelected())));
                    apiController.save(job.getJobs());
                    flag = true;
                }

                if (!txtLocation2.getText().equals("")) {
                    apiController.setUrl(ApiController.query(guiController.setFilters(txtLocation2, txtDescription, fullTime.isSelected())));
                    apiController.save(job.getJobs());
                    flag = true;
                }
            }
            else{
                flag=false;
            }

            if(flag == true){
                if (!job.getJobs().isEmpty()) {
                    new JobsFoundPanel(job, pickedJobs);

                } else
                    throw new GuiOptionPaneException("     Bro, jobs ain't found");
            }
            else{
                if(txtLocation1.getText().equals("")){
                    throw new GuiOptionPaneException(" You must insert the location");
                }
                else{
                    throw new GuiOptionPaneException("You can't insert the same location in the fields");
                }
            }
        }catch (IOException exception) {
            JOptionPane.showMessageDialog(super.getPanel(), "Invalid filters");
        }catch (GuiOptionPaneException guiOptionPaneException) {
            JOptionPane.showMessageDialog(super.getPanel(), guiOptionPaneException.getMessage());
        }

    }

    public void showHowToApply(PickedJobs job, int index){
        try {

            if(job.getHow_to_apply(index, job.getJobs()).toString().equals("")){
                throw new GuiOptionPaneException("The how to apply is not available");
            }
            else{
                Object[] options = { "Copy on clip board", "Exit" };

                int result = JOptionPane.showOptionDialog(super.getPanel(),job.getHow_to_apply(index, job.getJobs()) ,"Information",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
                        null, options, options[0]);

                if (result == JOptionPane.YES_OPTION){
                    StringSelection selection = new StringSelection(job.getHow_to_apply(index, job.getJobs()));
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                    JOptionPane.showMessageDialog(super.getPanel(),"Text successfully copied to the clip board");
                }

            }

        }catch(GuiOptionPaneException guiOptionPaneException){
            JOptionPane.showMessageDialog(super.getPanel(), guiOptionPaneException.getMessage());

        }catch(Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"     Bro, jobs ain't found");
        }
    }

    public void showInternetPage(PickedJobs job, int index){

        try {

            if(job.getCompany_url(index, job.getJobs()).toString().equals("http://http")){
                throw new GuiOptionPaneException("The link is not available");
            }
            else{
                desktop.browse(job.getCompany_url(index, job.getJobs()).toURI());
            }

        }catch (IOException ioException) {
            ioException.printStackTrace();

        } catch (URISyntaxException uriSyntaxException) {
            JOptionPane.showMessageDialog(super.getPanel(), "The link is wrong");
        }catch (GuiOptionPaneException guiOptionPaneException){
            JOptionPane.showMessageDialog(super.getPanel(), guiOptionPaneException.getMessage());
        }catch(Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"     Bro, jobs ain't found");
        }
    }

    public void saveJob(PickedJobs job, PickedJobs pickedJobs, int index){

        try {
            if(job.getJobs().isEmpty()){
                throw new GuiOptionPaneException("Any job to save.");
            }
            else{
                if(getTableJobs().getSelectedRow() == -1){
                    throw new GuiOptionPaneException("You have not selected any job.");
                }
                else{
                    if(pickedJobs.add(job, index)){
                        pickedJobs.add(job.getJob(index, job.getJobs()));
                        throw new GuiOptionPaneException("Job saved successfully in " + pickedJobs.getFileName());
                    }
                    else{
                        throw new GuiOptionPaneException("Job is already present in the data base");
                    }
                }
            }
        }catch (GuiOptionPaneException guiOptionPaneException){
            JOptionPane.showMessageDialog(super.getPanel(), guiOptionPaneException.getMessage());
        }catch(Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"     Bro, jobs ain't found");
            exception.printStackTrace();
        }
    }

    public void saveAllJobs(PickedJobs job, PickedJobs pickedJobs){

        try {
            if (job.getJobs().isEmpty()) {
                throw new GuiOptionPaneException("Any job to save.");
            } else {

                pickedJobs.addAll(job);

                throw new GuiOptionPaneException("Jobs saved successfully in " + pickedJobs.getFileName());
            }
        }catch (GuiOptionPaneException guiOptionPaneException){
            JOptionPane.showMessageDialog(super.getPanel(), guiOptionPaneException.getMessage());
        }
    }

    public void deleteJobs(PickedJobs job, int index){

        Object[] options = { "Yes", "No" };

        if(index == -1){
            JOptionPane.showMessageDialog(super.getPanel(),"          Unselected job.");
        }
        else {
            int result = JOptionPane.showOptionDialog(null, "           Are you sure?", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);

            if (result == JOptionPane.YES_OPTION) {
                try {

                    job.deleteJob(job.getJob(index, job.getJobs()).getId());

                    //correzione
                    FileController fileController = new FileController("PickedJobs.txt");
                    fileController.save(job.getJobs());

                    dispose();
                    if(!job.getJobs().isEmpty()){
                        new JobsSavedPanel(job);
                    }
                    else{
                        fileController.empty();
                    }
                    //job.getJobs().clear();


                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void deleteAllJobs(PickedJobs job){
        Object[] options = { "Yes", "No" };

        int result = JOptionPane.showOptionDialog(null, "           Are you sure?", "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);

        if (result == JOptionPane.YES_OPTION) {
            job.deleteAll();
            job.getJobs().clear();
            dispose();
        }
    }

}
