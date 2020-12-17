package GUI;

import Controller.ApiController;
import Model.Job;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import java.awt.Desktop;
import java.net.URL;

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

    private final static int COLUMNS = 3;

    private URL url;

    private Object[] columnHeaders = {"Type","Company","Location"};
    private Object[][] rowData = null;

    private Desktop desktop = Desktop.getDesktop();


    private HashSet<Job> offerte = new HashSet<>();
    private ApiController controller = new ApiController();

    public JobsFoundPanel(URL url){
        this.url = url;




        //String authSubUrl = AuthSubUtil.getRequestUrl();

        try {
            offerte.addAll(controller.parsing(this.url));
        }catch(Exception e){
        }

        rowData = new String[offerte.size()][COLUMNS];

        this.tableJobs = new JTable(setTable(offerte.iterator()), columnHeaders);
        this.tableJobs.setPreferredScrollableViewportSize(new Dimension(500,50));
        this.tableJobs.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(this.tableJobs);
        add(scrollPane);
        setSize(600, 600);
        setVisible(true);

        add(jobsFoundPanel);
        setTitle("Jobs Found");
        setSize(600, 700);
        setResizable(false);
        setVisible(true);

        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatsPanel();
            }
        });

        btnShowSavedJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobsSavedPanel();
            }
        });

        btnInternetPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try { //implementare l'url dell'offerta selezionata
                    desktop.browse(new URI("http://bing.com"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
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
                System.out.println(tableJobs.getSelectedRow()); //implementare l'accesso al'offerta
                System.out.println(tableJobs.getModel().getValueAt(tableJobs.getSelectedRow(), tableJobs.getSelectedColumn()).toString());
                System.out.println(tableJobs.getModel().getValueAt(tableJobs.getSelectedRow(), tableJobs.getSelectedColumn() + 1).toString());
                System.out.println(tableJobs.getModel().getValueAt(tableJobs.getSelectedRow(), tableJobs.getSelectedColumn() + 2).toString());

            }
        });

        btnExit.addActionListener(new ActionListener() {
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
}


