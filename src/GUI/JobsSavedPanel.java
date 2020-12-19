package GUI;

import Model.Job;
import Model.JobBoard;
import Model.StatsJobBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class JobsSavedPanel extends JFrame{

    private JPanel jobsSavedPanel;
    private JTable table1;
    private JButton btnStats;
    private JButton deleteButton;
    private JButton btnDeleteAll;
    private JButton btnExit;
    private JobBoard job = new JobBoard();
    private HashSet<Job> jobs;
    private String keyWord;

    public JobsSavedPanel(String keyWord){


        add(jobsSavedPanel);
        setTitle("Saved Jobs");
        setSize(600, 300);
        setVisible(true);

        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashSet<Job> offerte = null;

                try{
                    new StatsPanel(jobs, keyWord);
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(jobsSavedPanel,"     Bro, jobs ain't saved");
                }

            }
        });
    }
}
