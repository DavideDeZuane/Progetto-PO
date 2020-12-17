package GUI;

import Model.Job;
import Model.JobBoard;

import javax.swing.*;
import java.util.HashSet;

public class StatsPanel extends JFrame {

    private JPanel statsPanel;
    private JLabel fullTimePercent;
    private JLabel jobTot;
    private HashSet<Job> offerte = null;

    public StatsPanel(HashSet<Job> offerte, String keyWord){

        //IMPLEMENTARE KEYWORD STATS

        this.offerte = offerte;

        JobBoard jobBoard = new JobBoard();
        jobBoard.setJobs(offerte);

        jobTot.setText(String.valueOf(jobBoard.getNumOfJobs()));
        fullTimePercent.setText(String.valueOf(jobBoard.calculatePercentage()) + "%");

        add(statsPanel);
        setTitle("Stats");
        setSize(600, 300);
        setVisible(true);
    }
}
