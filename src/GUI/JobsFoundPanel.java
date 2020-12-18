package GUI;

import Controller.ApiController;
import Controller.GuiApiController;
import Model.Job;
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

    private final static int COLUMNS = 3;

    //private String keyWord = null; //utilizzata nelle statistiche

    private URL url;

    private Object[] columnHeaders = {"Type","Company","Location"};
    private Object[][] rowData = null;

    private Desktop desktop = Desktop.getDesktop();


    private HashSet<Job> offerte = new HashSet<>();
    private ApiController controller = new GuiApiController();


    private PickedJobs pickedJobs = new PickedJobs();


    public JobsFoundPanel(URL url, String keyWord) throws IOException {
        this.url = url;

        try {
            offerte.addAll(controller.parsing(url));
        }catch(Exception e){
        }


        //tabella aggiunta da codice e non dal form per motivi di grafica
        //companyUrl = new URL[offerte.size()];
        rowData = new String[offerte.size()][COLUMNS];

        this.tableJobs = new JTable(setTable(offerte.iterator()), columnHeaders);
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

                new StatsPanel(offerte, keyWord);
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

                    if(getUrl(tableJobs.getSelectedRow()).toString().equals("http://http")){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "The link is not available");
                    }
                    else{
                        desktop.browse(getUrl(tableJobs.getSelectedRow()).toURI());
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
                getJob(tableJobs.getSelectedRow());
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

            }
        });

        btnHowToApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public Object[][] setTable(Iterator iterator){
        Job tmp = null;
        int count = 0;

        for(int i = 0; i < offerte.size(); i++) {
            tmp = (Job) iterator.next();
            rowData[i][count++] = tmp.getType();
            rowData[i][count++] = tmp.getCompany();
            rowData[i][count++] = tmp.getLocation();
            count = 0;
        }
        return rowData;
    }

    public URL getUrl(int index) {
        Vector<Job> jobs = new Vector<>();
        jobs.addAll(offerte);
        return jobs.elementAt(index).getCompany_url();
    }

    public Job getJob(int index) {
        Vector<Job> jobs = new Vector<>();
        jobs.addAll(offerte);
        return jobs.elementAt(index);
    }
}


