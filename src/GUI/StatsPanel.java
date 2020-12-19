package GUI;

import Model.Job;
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
    private HashSet<Job> offers;

    private StatsJobBoard statsJobBoard = new StatsJobBoard();

    private String keyWord;

    public StatsPanel(HashSet<Job> offers, String keyWord){
        this.setOffers(offers);
        this.setKeyWord(keyWord);


        statsJobBoard.setJobs(this.getOffers());

        lblJobTot.setText(String.valueOf(statsJobBoard.getNumOfJobs()));
        lblFullTimePercent.setText(String.valueOf(statsJobBoard.calculatePercentage()) + "%");

        add(statsPanel);
        setTitle("Stats");
        setSize(600, 300);
        setVisible(true);


        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (!getKeyWord().equals(""))
                        JOptionPane.showMessageDialog(statsPanel, "There were created " +
                                statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                                txtPeriod.getText() + " days with key word: " + getKeyWord());

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

    public HashSet<Job> getOffers() {
        return offers;
    }

    public void setOffers(HashSet<Job> offers) {
        this.offers = offers;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
