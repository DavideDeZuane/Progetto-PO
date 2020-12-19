package GUI;

import Model.Job;
import Model.JobBoard;
import Model.StatsJobBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class StatsPanel extends JFrame {

    private JPanel statsPanel;
    private JLabel lblFullTimePercent;
    private JLabel lblJobTot;
    private JTextField txtPeriod;
    private JButton check;
    private JLabel lblKeyWordRepeat;
    private StatsJobBoard statsJobBoard;

    public StatsPanel(HashSet<Job> jobs, String keyWord){

        statsJobBoard = new StatsJobBoard();

        statsJobBoard.setJobs(jobs);
        statsJobBoard.setKeyWord(keyWord);

        lblJobTot.setText(String.valueOf(statsJobBoard.getNumOfJobs()));
        lblFullTimePercent.setText(String.valueOf(statsJobBoard.calculatePercentage()) + "%");
        lblKeyWordRepeat.setText("The key word " + statsJobBoard.getKeyWord() + " was repeated " +
                statsJobBoard.keyWords(statsJobBoard.getKeyWord()) + " times in the job descriptions.");

        add(statsPanel);
        setTitle("Stats");
        setSize(600, 300);
        setVisible(true);


        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (!statsJobBoard.getKeyWord().equals(""))
                        JOptionPane.showMessageDialog(statsPanel, "There were created " +
                                statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                                txtPeriod.getText() + " days with key word: " + statsJobBoard.getKeyWord());

                    else
                        JOptionPane.showMessageDialog(statsPanel, "There were created " +
                                statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                                txtPeriod.getText() + " days.");

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(statsPanel,"  Bro, you can't write this.");
                }
            }
        });
    }

}
