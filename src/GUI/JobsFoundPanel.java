package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobsFoundPanel extends JFrame{
    private JPanel jobsFoundPanel;
    private JTable table1;
    private JButton btnShowSavedJobs;
    private JButton btnSave;
    private JButton btnSaveAll;
    private JButton btnInternetPage;
    private JButton btnExit;
    private JButton btnStats;

    public JobsFoundPanel(){

        add(jobsFoundPanel);
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
