package GUI;

import Model.Job;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class JobsSavedPanel extends JFrame{

    private JPanel JobsSavedPanel;
    private JTable table1;
    private JButton btnStats;
    private JButton deleteButton;
    private JButton btnDeleteAll;
    private JButton btnExit;

    private String keyWord;

    public JobsSavedPanel(String keyWord){
        this.keyWord = keyWord;

        add(JobsSavedPanel);
        setTitle("Saved Jobs");
        setSize(600, 300);
        setVisible(true);

        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashSet<Job> offerte = null;
                new StatsPanel(offerte, keyWord);
            }
        });
    }
}
