package GUI;

import Controller.ApiController;
import Model.Job;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class JobsFoundPanel extends JFrame{
    private JPanel jobsFoundPanel;
    private JButton btnShowSavedJobs;
    private JButton btnSave;
    private JButton btnSaveAll;
    private JButton btnInternetPage;
    private JButton btnExit;
    private JButton btnStats;
    private JTable tableJobs;
    private URL url;

    private Object[] columnHeaders = {"Type","Company","Location"};

    private Object[][] rowData = null;


    private HashSet<Job> offerte = new HashSet<>();
    private ApiController controller = new ApiController();

    public JobsFoundPanel(URL url){

        this.url = url;

        //setLayout(new FlowLayout());

        try {
            offerte.addAll(controller.parsing(this.url));
        }catch(Exception e){
        }


        for(Job l: offerte)
            System.out.println(l);


        rowData = new String[offerte.size()][3];

        Job tmp = null;
        Iterator iterator = offerte.iterator();
        int count = 0;

        for(int i = 0; i < offerte.size(); i++) {
            tmp = (Job) iterator.next();
            rowData[i][count++] = tmp.getType();
            rowData[i][count++] = tmp.getCompany();
            rowData[i][count++] = tmp.getLocation();
            count = 0;
        }



        this.tableJobs = new JTable(rowData, columnHeaders);
        //this.tableJobs.setPreferredScrollableViewportSize(new Dimension(500,50));
        this.tableJobs.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(this.tableJobs);
        add(scrollPane);



        add(this.jobsFoundPanel);
        add(this.tableJobs);
        setTitle("Jobs Found");
        setSize(600, 300);
        setVisible(true);



        btnShowSavedJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobsSavedPanel();
            }
        });

        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatsPanel();
            }
        });
    }
}
