package GUI;

import Model.Job;
import Model.StatsJobBoard;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class StatsJobsFoundPanel extends JFrame {

    private JPanel statsJobsFoundPanel;
    private JLabel lblFullTimePercent;
    private JLabel lblJobTot;
    private JTextField txtPeriod;
    private JButton check;
    private JLabel lblKeyWordRepeat;
    private JButton showChartButton;
    private final StatsJobBoard statsJobBoard;
    private DrawCharts drawCharts;
    private JFreeChart barChart;
    private JFreeChart pieChart;

    public StatsJobsFoundPanel(HashSet<Job> jobs, String keyWord){

        statsJobBoard = new StatsJobBoard();
        statsJobBoard.setJobs(jobs);
        statsJobBoard.setKeyWord(keyWord);

        drawCharts = new DrawCharts (jobs, barChart, pieChart, statsJobBoard);
        drawCharts.drawPieChart();
        drawCharts.drawBarChart();

        lblJobTot.setText(String.valueOf(statsJobBoard.getNumOfJobs()));
        lblFullTimePercent.setText(String.valueOf(statsJobBoard.calculatePercentage()) + "%");
        lblKeyWordRepeat.setText("The key word " + statsJobBoard.getKeyWord() + " was repeated " +
                statsJobBoard.keyWords(statsJobBoard.getKeyWord()) + " times in the job descriptions.");

        add(statsJobsFoundPanel);
        setTitle("Stats jobs found");
        setSize(600, 300);
        setVisible(true);

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (!statsJobBoard.getKeyWord().equals(""))
                        JOptionPane.showMessageDialog(statsJobsFoundPanel, "There were created " +
                                statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                                txtPeriod.getText() + " days with key word: " + statsJobBoard.getKeyWord());

                    else
                        JOptionPane.showMessageDialog(statsJobsFoundPanel, "There were created " +
                                statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                                txtPeriod.getText() + " days.");

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(statsJobsFoundPanel,"  Bro, you can't write this.");
                }
            }
        });

        showChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fr = new JFrame("Chart");
                fr.getContentPane().add(new ChartPanel(drawCharts.getPieChart()), BorderLayout.WEST);
                fr.getContentPane().add(new ChartPanel(drawCharts.getBarChart()), BorderLayout.EAST);
                fr.pack();
                fr.setVisible(true);
            }
        });
    }
}