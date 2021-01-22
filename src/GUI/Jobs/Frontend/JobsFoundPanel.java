package GUI.Jobs.Frontend;

import Controller.FileController;
import GUI.Jobs.Backend.GuiJobsPanelManagement;
import GUI.Stats.Frontend.StatsJobsFoundPanel;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

    private PickedJobs pickedJobs = new PickedJobs();

    private FileController fileController = new FileController("PickedJobs.txt");

    private GuiJobsPanelManagement guiJobsPanelMenagement;

    private final int widthPanel = 625;
    private final int heightPanel = 725;

    private final int widthTable = 625;
    private final int heightTable = 600;

    /**
     * this constructor makes a panel and implements some buttons
     * - the show saved button shows the user the jobs he saved, if there are no jobs saved, the user will be warned by a message
     * - the internet page button shows the user the internet page of the job the user clicked on
     * - the save button allows the user to save a job
     * - the save all button allows the user to save all the jobs that appears to a panel
     * - the exit button allows the user to exit the panel
     * - the how to apply button allows the user to take a look at the how to apply field of the job the user selected
     */
    public JobsFoundPanel(PickedJobs job, PickedJobs pickedJobs) throws IOException{

        this.job = job;
        this.pickedJobs = pickedJobs;

        guiJobsPanelMenagement = new GuiJobsPanelManagement(jobsFoundPanel, "Jobs Found Panel");
        guiJobsPanelMenagement.createTable(this.tableJobs, this.job, this.widthTable, this.heightTable);
        guiJobsPanelMenagement.setPanel(this.widthPanel, this.heightPanel);

        //fileController.readJobsFromFile(pickedJobs.getJobs());


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
                guiJobsPanelMenagement.showInternetPage(job, guiJobsPanelMenagement.getTableJobs().getSelectedRow());
            }
        });

        btnSaveAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelMenagement.saveAllJobs(job, pickedJobs);
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelMenagement.saveJob(job, pickedJobs, guiJobsPanelMenagement.getTableJobs().getSelectedRow());
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelMenagement.dispose();
            }
        });

        btnHowToApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelMenagement.showHowToApply(job, guiJobsPanelMenagement.getTableJobs().getSelectedRow());
            }
        });
    }
}