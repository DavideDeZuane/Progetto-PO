package GUI;

import Controller.FileController;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * this constructor makes a panel and implements some buttons
 * - the show saved button shows the user the jobs he saved, if there are no jobs saved, the user will be warned by a message
 * - the internet page button shows the user the internet page of the job the user clicked on
 * - the save button allows the user to save a job
 * - the save all button allows the user to save all the jobs that appears to a panel
 * - the exit button allows the user to exit the panel
 * - the how to apply button allows the user to take a look at the how to apply field of the job the user selected
 */
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

    private PickedJobs job;
    private int buffer = -2;

    private Desktop desktop = Desktop.getDesktop();

    private PickedJobs pickedJobs = new PickedJobs();

    private FileController fileController = new FileController("PickedJobs.txt");

    private GuiJobsPanelManagement guiJobsPanelManagement;

    private final int widthPanel = 625;
    private final int heightPanel = 725;


    private final int widthTable = 625;
    private final int heightTable = 600;

    public JobsFoundPanel(PickedJobs job) throws IOException{

        this.job = job;

        guiJobsPanelManagement = new GuiJobsPanelManagement(jobsFoundPanel, "Jobs Found Panel");
        guiJobsPanelManagement.createTable(this.tableJobs, this.job, this.widthTable, this.heightTable);
        guiJobsPanelManagement.setPanel(this.widthPanel, this.heightPanel);

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

                    if(job.getCompany_url(guiJobsPanelManagement.getTableJobs().getSelectedRow(), job.getJobs()).toString().equals("http://http")){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "The link is not available");
                    }
                    else{
                        desktop.browse(job.getCompany_url(guiJobsPanelManagement.getTableJobs().getSelectedRow(), job.getJobs()).toURI());
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


                guiJobsPanelManagement.saveJob(job, pickedJobs);
                /*
                if(job.getJobs().isEmpty()){
                    JOptionPane.showMessageDialog(jobsFoundPanel, "Any job to save.");
                }else {

                    pickedJobs.addAll(job.getJobs());

                    if(buffer == pickedJobs.getNumOfJobs()){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "Jobs are already present");
                    }
                    else{
                        JOptionPane.showMessageDialog(jobsFoundPanel, "Jobs saved successfully in " + pickedJobs.getFileName());
                        buffer = pickedJobs.getNumOfJobs();
                    }
                }*/
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                guiJobsPanelManagement.saveJob(job, pickedJobs);

            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelManagement.dispose();
            }
        });

        btnHowToApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                guiJobsPanelManagement.showHowToApply(job);

            }
        });
    }
}