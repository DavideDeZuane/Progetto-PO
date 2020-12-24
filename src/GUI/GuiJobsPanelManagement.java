package GUI;

import Controller.ApiController;
import Controller.CheckOffer;
import Controller.GuiController;
import Model.Job;
import Model.JobBoard;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

public class GuiJobsPanelManagement extends GuiManagement implements GuiJobsPanel{

    private JTable tableJobs;
    private final static int COLUMNS = 4;

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
                    JOptionPane.showMessageDialog(super.getPanel(), "     Bro, jobs ain't found");
            }
            else{
                if(txtLocation1.getText().equals("")){
                    JOptionPane.showMessageDialog(super.getPanel(), " You must insert the location");
                }
                else{
                    JOptionPane.showMessageDialog(super.getPanel(), "You can't insert the same location in the fields");
                }
            }
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(super.getPanel(), "Invalid filters");
        }

    }

    public void showHowToApply(PickedJobs job, int index){
        try {

            if(job.getHow_to_apply(index, job.getJobs()).toString().equals("")){
                JOptionPane.showMessageDialog(super.getPanel(), "The how to apply is not available");
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

        }catch(Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"     Bro, jobs ain't found");
        }
    }

    public void showInternetPage(PickedJobs job, int index){

        try {

            if(job.getCompany_url(index, job.getJobs()).toString().equals("http://http")){
                JOptionPane.showMessageDialog(super.getPanel(), "The link is not available");
            }
            else{
                desktop.browse(job.getCompany_url(index, job.getJobs()).toURI());
            }

        }catch (IOException ioException) {
            ioException.printStackTrace();

        } catch (URISyntaxException uriSyntaxException) {
            JOptionPane.showMessageDialog(super.getPanel(), "The link is wrong");
        }catch(Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"     Bro, jobs ain't found");
        }
    }

    public void saveJob(PickedJobs job, PickedJobs pickedJobs, int index){

        try {

            if(job.getJobs().isEmpty()){
                JOptionPane.showMessageDialog(super.getPanel(), "Any job to save.");
            }
            else{
                if(getTableJobs().getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(super.getPanel(), "You have not selected any job.");
                }
                else{
                    //pickedJobs.add(job.getJob(index, job.getJobs()));

                    //pickedJobs.add(job, index);

                    if(pickedJobs.add(job, index)){
                        pickedJobs.add(job.getJob(index, job.getJobs()));
                        JOptionPane.showMessageDialog(super.getPanel(), "Job saved successfully in " + pickedJobs.getFileName());
                    }
                    else{
                        JOptionPane.showMessageDialog(super.getPanel(), "Job is already present in the data base");
                    }

                    /*
                    boolean flag = true;

                    for(Job j : pickedJobs.getJobs()){
                        if(j.getId().equals(job.getJob(index, job.getJobs()).getId())){
                            flag = false;
                        }
                    }

                    if(flag){
                        pickedJobs.add(job.getJob(index, job.getJobs()));
                        JOptionPane.showMessageDialog(super.getPanel(), "Job saved successfully in " + pickedJobs.getFileName());
                    }
                    else{
                        JOptionPane.showMessageDialog(super.getPanel(), "Job is already present in the data base");
                    }*/
                }
            }

        }catch(Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"     Bro, jobs ain't found");
            exception.printStackTrace();
        }
    }

    public void saveAllJobs(PickedJobs job, PickedJobs pickedJobs){

        if(job.getJobs().isEmpty()){
            JOptionPane.showMessageDialog(super.getPanel(), "Any job to save.");
        }else {

            pickedJobs.addAll(job);

            /*

            boolean flag = true;

            for(Job i : job.getJobs()){
                flag = true;
                for(Job j : pickedJobs.getJobs()){
                    if(j.getId().equals(i.getId())) {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    try {
                        pickedJobs.add(i);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }

            }*/

            JOptionPane.showMessageDialog(super.getPanel(), "Jobs saved successfully in " + pickedJobs.getFileName());
        }
    }

    public void deleteJobs(PickedJobs job, int index){

        Object[] options = { "Yes", "No" };

        int result = JOptionPane.showOptionDialog(null, "           Are you sure?", "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);

        if (result == JOptionPane.YES_OPTION){
            try {
                job.deleteJob(job.getJob(index, job.getJobs()).getId());
                dispose();
                new JobsSavedPanel(job);

            } catch (IOException exception) {
                exception.printStackTrace();
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
