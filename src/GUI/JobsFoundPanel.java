package GUI;

import Controller.ApiController;
import Model.Job;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

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

    private URL[] companyUrl = null;

    private final static int COLUMNS = 3;
    private String keyWord = null; //utilizzata nelle statistiche

    private URL url;

    private Object[] columnHeaders = {"Type","Company","Location"};
    private Object[][] rowData = null;

    private Desktop desktop = Desktop.getDesktop();


    private HashSet<Job> offerte = new HashSet<>();
    private ApiController controller = new ApiController();

    public JobsFoundPanel(URL url, String keyWord){
        this.url = url;
        setKeyWord(keyWord);

        try {
            offerte.addAll(controller.parsing(this.url));
        }catch(Exception e){
        }

        companyUrl = new URL[offerte.size()];
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

                new StatsPanel(offerte, getKeyWord());
            }
        });

        btnShowSavedJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobsSavedPanel(getKeyWord());
            }
        });

        btnInternetPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                try {

                    if(companyUrl[tableJobs.getSelectedRow()].toString().equals("http://http")){
                        JOptionPane.showMessageDialog(jobsFoundPanel, "The link is not available");
                    }
                    else{
                        desktop.browse(companyUrl[tableJobs.getSelectedRow()].toURI());
                    }

                }catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    JOptionPane.showMessageDialog(jobsFoundPanel, "The link is wrong");
                    //uriSyntaxException.printStackTrace();
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
    }

    public String getKeyWord(){
        return this.keyWord;
    }

    public void setKeyWord(String keyWord){
        this.keyWord = keyWord;
    }

    public Object[][] setTable(Iterator iterator){
        Job tmp = null;
        int count = 0;

        for(int i = 0; i < offerte.size(); i++) {
            tmp = (Job) iterator.next();
            rowData[i][count++] = tmp.getType();
            rowData[i][count++] = tmp.getCompany();
            rowData[i][count++] = tmp.getLocation();
            companyUrl[i] = tmp.getCompany_url();
            count = 0;
        }

        return rowData;
    }
}


