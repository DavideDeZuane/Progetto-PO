package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobsSavedPanel extends JFrame{

    private JPanel JobsSavedPanel;
    private JTable table1;
    private JButton btnStats;
    private JButton deleteButton;
    private JButton btnDeleteAll;
    private JButton btnExit;

    public JobsSavedPanel(){

        add(JobsSavedPanel);
        setTitle("Saved Jobs");
        setSize(600, 300);
        setVisible(true);

        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatsPanel();
            }
        });
    }
}
