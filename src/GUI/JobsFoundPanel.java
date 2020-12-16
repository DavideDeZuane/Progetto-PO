package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class JobsFoundPanel extends JFrame{
    private JPanel jobsFoundPanel;
    private JTable table1;
    private JButton btnShowSavedJobs;
    private JButton btnSave;
    private JButton btnSaveAll;
    private JButton btnInternetPage;
    private JButton btnExit;
    private JButton btnStats;
    private URL url;

    public JobsFoundPanel(URL url){

        this.url = url;

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
