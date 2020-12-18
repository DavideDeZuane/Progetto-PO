package GUI;

import Model.Job;

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

    private String keyWord;

    public JobsSavedPanel(String keyWord){
        setKeyWord(keyWord);

        add(jobsSavedPanel);
        setTitle("Saved Jobs");
        setSize(600, 300);
        setVisible(true);

        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashSet<Job> offerte = null;

                try{
                    new StatsPanel(offerte, getKeyWord());
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(jobsSavedPanel,"     Bro, jobs ain't saved");
                }

            }
        });
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
