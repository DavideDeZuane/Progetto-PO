package GUI;

import Model.JobBoard;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class JobsFoundPanel extends JFrame{
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

    private JobBoard job = new JobBoard();

    private final static int COLUMNS = 3;

    private Object[] columnHeaders = {"Type","Company","Location"};

    private Desktop desktop = Desktop.getDesktop();
    private PickedJobs pickedJobs = new PickedJobs();


    public JobsFoundPanel(JobBoard job) throws IOException {

        this.job = job;

        //creazione da codice e impostazioni tabella
        this.tableJobs = new JTable(this.job.setTableJobs(this.job.getJobs().iterator(), this.job.getNumOfJobs(), COLUMNS), columnHeaders);
        this.tableJobs.setPreferredScrollableViewportSize(new Dimension(500,50));
        this.tableJobs.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(this.tableJobs);
        add(scrollPane);
        setSize(600, 600);
        setVisible(true);


        //impostazioni del panel
        add(jobsFoundPanel);
        setTitle("Jobs Found");
        setSize(600, 725);
        setResizable(false);
        setVisible(true);

        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new StatsPanel(job.getJobs(), job.getKeyWord());
            }
        });

        btnShowSavedJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobsSavedPanel(job.getKeyWord());
            }
        });

        btnInternetPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                try {

                    if(job.getCompany_url(tableJobs.getSelectedRow(), job.getJobs()).toString().equals("http://http")){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "The link is not available");
                    }
                    else{
                        desktop.browse(job.getCompany_url(tableJobs.getSelectedRow(), job.getJobs()).toURI());
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

                try {
                    PickedJobs pickedJobs = new PickedJobs();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

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

                try {
                    PickedJobs pickedJobs = new PickedJobs();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try {

                    if(job.getJobs().isEmpty()){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "Any job to save.");
                    }
                    else{
                        if(tableJobs.getSelectedRow() == -1){
                            JOptionPane.showMessageDialog(jobsFoundPanel, "You have not selected any job.");
                        }
                        else {
                            pickedJobs.add(job.getJob(tableJobs.getSelectedRow(), job.getJobs()));
                            JOptionPane.showMessageDialog(jobsFoundPanel, "Job saved successfully in " + pickedJobs.getFileName());
                        }
                    }

                }catch(Exception exception){
                    JOptionPane.showMessageDialog(jobsFoundPanel,"     Bro, jobs ain't found");
                }

            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnHowToApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if(job.getHow_to_apply(tableJobs.getSelectedRow(), job.getJobs()).toString().equals("")){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "The how to apply is not available");
                    }
                    else{
                        //desktop.browse(job.getCompany_url(tableJobs.getSelectedRow(), job.getJobs()).toURI());

                        //job.getHow_to_apply(tableJobs.getSelectedRow(), job.getJobs())

                        JOptionPane.showMessageDialog(jobsFoundPanel, job.getHow_to_apply(tableJobs.getSelectedRow(), job.getJobs()));


                    }

                }catch(Exception exception){
                    JOptionPane.showMessageDialog(jobsFoundPanel,"     Bro, jobs ain't found");
                }

            }
        });
    }
}


