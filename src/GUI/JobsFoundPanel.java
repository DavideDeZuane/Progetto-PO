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

    private PickedJobs job;

    private PickedJobs pickedJobs = new PickedJobs();

    private FileController fileController = new FileController("PickedJobs.txt");

    private GuiJobsPanelMenagement guiJobsPanelMenagement;

    private final int widthPanel = 625;
    private final int heightPanel = 725;

    private final int widthTable = 625;
    private final int heightTable = 600;

    public JobsFoundPanel(PickedJobs job, PickedJobs pickedJobs) throws IOException{

        this.job = job;
        this.pickedJobs = pickedJobs;

        guiJobsPanelMenagement = new GuiJobsPanelMenagement(jobsFoundPanel, "Jobs Found Panel");
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