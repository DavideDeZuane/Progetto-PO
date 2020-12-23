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
    private int buffer = -2;

    private Desktop desktop = Desktop.getDesktop();

    private PickedJobs pickedJobs = new PickedJobs();

    private FileController fileController = new FileController("PickedJobs.txt");

    private GuiJobsPanelMenagement guiJobsPanelMenagement;

    private final int widthPanel = 625;
    private final int heightPanel = 725;

    public JobsFoundPanel(PickedJobs job) throws IOException{

        this.job = job;

        guiJobsPanelMenagement = new GuiJobsPanelMenagement(jobsFoundPanel, "Jobs Found Panel");
        guiJobsPanelMenagement.createTable(this.tableJobs, this.job);
        guiJobsPanelMenagement.setPanel(this.widthPanel, this.heightPanel);

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

                    if(job.getCompany_url(guiJobsPanelMenagement.getTableJobs().getSelectedRow(), job.getJobs()).toString().equals("http://http")){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "The link is not available");
                    }
                    else{
                        desktop.browse(job.getCompany_url(guiJobsPanelMenagement.getTableJobs().getSelectedRow(), job.getJobs()).toURI());
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

                System.out.println(job.getJobs());
                System.out.println("-----------------------------------------------------------------");

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


                }


            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                guiJobsPanelMenagement.saveJob(job, pickedJobs);

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

                guiJobsPanelMenagement.showHowToApply(job);

            }
        });
    }
}