package GUI;

import Controller.FileController;
import Model.JobBoard;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.Desktop;

public class JobsFoundPanel extends JFrame {
    private JPanel jobsFoundPanel;
    private JButton btnShowSavedJobs;
    private JButton btnSave;
    private JButton btnSaveAll;
    private JButton btnInternetPage;
    private JButton btnExit;
    private JButton btnStats;
    private JTable tableJobs;
    private JPanel buttonPanel;
    private JButton btnHowToApply;
    private ImageIcon imageIcon;

    private JobBoard job;


    private int buffer = -2;

    //private final static int COLUMNS = 3;
    //private Object[] columnHeaders = {"Type","Company","Location"};


    private Desktop desktop = Desktop.getDesktop();

    private PickedJobs pickedJobs = new PickedJobs();

    private FileController fileController = new FileController("PickedJobs.txt");

    /*
    static {
        try {
            pickedJobs = new PickedJobs();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }*/

    GuiMenagement guiMenagement;

    public JobsFoundPanel(JobBoard job) throws IOException{

        this.job = job;

        guiMenagement = new GuiMenagement(jobsFoundPanel, "Jobs Found Panel");
        guiMenagement.createTable(tableJobs, this.job);

        fileController.readJobsFromFile(pickedJobs.getJobs());


        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new StatsJobsFoundPanel(job.getJobs(), job.getKeyWord());
            }
        });

        btnShowSavedJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!pickedJobs.getJobs().isEmpty()){
                    new JobsSavedPanel(pickedJobs);
                }else{
                    JOptionPane.showMessageDialog(jobsFoundPanel,"bro, there ain't saved jobs");
                }

            }
        });

        btnInternetPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                try {

                    if(job.getCompany_url(guiMenagement.getTableJobs().getSelectedRow(), job.getJobs()).toString().equals("http://http")){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "The link is not available");
                    }
                    else{
                        desktop.browse(job.getCompany_url(guiMenagement.getTableJobs().getSelectedRow(), job.getJobs()).toURI());
                    }

                }catch (IOException ioException) {
                    ioException.printStackTrace();

                } catch (URISyntaxException uriSyntaxException) {
                    JOptionPane.showMessageDialog(jobsFoundPanel, "The link is wrong");
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(jobsFoundPanel,"     Bro, jobs ain't found");
                }
            }
        });

        btnSaveAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
/*
                try {
                    PickedJobs pickedJobs = new PickedJobs();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }*/

                if(job.getJobs().isEmpty()){
                    JOptionPane.showMessageDialog(jobsFoundPanel, "Any job to save.");
                }else {
                    pickedJobs.addAll(job.getJobs());
                    JOptionPane.showMessageDialog(jobsFoundPanel, "Jobs saved successfully in " + pickedJobs.getFileName());
                }


            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //pickedJobs.addAll(job.getJobs());
/*
                try {

                    pickedJobs.setJobsFromFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }*/

                try {

                    if(job.getJobs().isEmpty()){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "Any job to save.");
                    }
                    else{
                        if(guiMenagement.getTableJobs().getSelectedRow() == -1){
                            JOptionPane.showMessageDialog(jobsFoundPanel, "You have not selected any job.");
                        }
                        else{//|| buffer == guiMenagement.getTableJobs().getSelectedRow()
                            if(pickedJobs.getJobs().contains(job.getJob(guiMenagement.getTableJobs().getSelectedRow(), job.getJobs())) ){
                                JOptionPane.showMessageDialog(jobsFoundPanel, "Job is already present in the data base");

                            }
                            else{
                                pickedJobs.add(job.getJob(guiMenagement.getTableJobs().getSelectedRow(), job.getJobs()));
                                //buffer = guiMenagement.getTableJobs().getSelectedRow();
                                JOptionPane.showMessageDialog(jobsFoundPanel, "Job saved successfully in " + pickedJobs.getFileName());
                            }
                            /*
                            if(!pickedJobs.add(job.getJob(guiMenagement.getTableJobs().getSelectedRow(), job.getJobs()))){
                                JOptionPane.showMessageDialog(jobsFoundPanel, "Job is already present in the data base");
                            }
                            else{
                                JOptionPane.showMessageDialog(jobsFoundPanel, "Job saved successfully in " + pickedJobs.getFileName());

                            }*/
                        }
                    }

                }catch(Exception exception){
                    JOptionPane.showMessageDialog(jobsFoundPanel,"     Bro, jobs ain't found");
                    exception.printStackTrace();
                }

            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiMenagement.dispose();
            }
        });

        btnHowToApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




                try {

                    if(job.getHow_to_apply(guiMenagement.getTableJobs().getSelectedRow(), job.getJobs()).toString().equals("")){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "The how to apply is not available");
                    }
                    else{

                        Object[] options = { "Copy on clip board", "Exit" };

                        int result = JOptionPane.showOptionDialog(jobsFoundPanel,job.getHow_to_apply(guiMenagement.getTableJobs().getSelectedRow(), job.getJobs()) ,"Information",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
                                null, options, options[0]);

                        if (result == JOptionPane.YES_OPTION){
                            StringSelection selection = new StringSelection(job.getHow_to_apply(guiMenagement.getTableJobs().getSelectedRow(), job.getJobs()));
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(selection, selection);
                            JOptionPane.showMessageDialog(jobsFoundPanel,"Text successfully copied to the clip board");
                        }

                    }

                }catch(Exception exception){
                    JOptionPane.showMessageDialog(jobsFoundPanel,"     Bro, jobs ain't found");
                }

            }
        });
    }
}


