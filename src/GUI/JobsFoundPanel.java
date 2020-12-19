package GUI;

import Controller.ApiController;
import Controller.GuiApiController;
import Model.Job;
import Model.JobBoard;
import Model.PickedJobs;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Desktop;
import java.net.URL;
import java.util.stream.Collector;

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


    public JobsFoundPanel(JobBoard job, String keyWord) throws IOException {

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

                new StatsPanel(job.getJobs(), keyWord);
            }
        });

        btnShowSavedJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobsSavedPanel(keyWord);
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

            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // Job job = null;
/*

                if(job.){

                }*/
                //getJob(tableJobs.getSelectedRow());
                //pickedJobs.add();

                /*
                System.out.println(tableJobs.getSelectedRow()); //implementare l'accesso al'offerta
                System.out.println(tableJobs.getModel().getValueAt(tableJobs.getSelectedRow(), tableJobs.getSelectedColumn()).toString());
                System.out.println(tableJobs.getModel().getValueAt(tableJobs.getSelectedRow(), tableJobs.getSelectedColumn() + 1).toString());
                System.out.println(tableJobs.getModel().getValueAt(tableJobs.getSelectedRow(), tableJobs.getSelectedColumn() + 2).toString());
                */
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });

        btnHowToApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}


