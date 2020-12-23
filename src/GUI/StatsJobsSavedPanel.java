package GUI;

import Model.Job;
import Model.StatsJobBoard;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class StatsJobsSavedPanel extends JFrame{

    private JPanel statsPanel;
    private JLabel lblFullTimePercent;
    private JLabel lblJobTot;
    private JTextField txtPeriod;
    private JButton check;
    private JLabel lblKeyWordRepeat;
    private JButton showChartButton;
    private JPanel statsJobsSavedPanel;
    private StatsJobBoard statsJobBoard = new StatsJobBoard();
    private DefaultCategoryDataset dataset2;
    private JFreeChart jFreeChart;
    private DrawCharts drawCharts;
    private JFreeChart barChart;
    private JFreeChart pieChart;

    public StatsJobsSavedPanel(HashSet<Job> jobs) {

        statsJobBoard = new StatsJobBoard();
        statsJobBoard.setJobs(jobs);

        //ChartFrame chartFrame = new ChartFrame("Date of Creation", jFreeChart, true);

        lblJobTot.setText(String.valueOf(statsJobBoard.getNumOfJobs()));
        lblFullTimePercent.setText(String.valueOf(statsJobBoard.calculatePercentage()) + "%");

        drawCharts = new DrawCharts (jobs, barChart, pieChart, statsJobBoard);
        drawCharts.drawPieChart();
        drawCharts.drawBarChart();

        add(statsJobsSavedPanel);
        setTitle("Stats jobs saved");
        setSize(600, 300);
        setVisible(true);

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    JOptionPane.showMessageDialog(statsJobsSavedPanel, "There were created " +
                            statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                            txtPeriod.getText() + " days.");

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(statsPanel,"  Bro, you can't write this.");
                }
            }
        });

        showChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fr = new JFrame("Charts");
                fr.getContentPane().add(new ChartPanel(drawCharts.getPieChart()), BorderLayout.WEST);
                fr.getContentPane().add(new ChartPanel(drawCharts.getBarChart()), BorderLayout.EAST);
                fr.pack();
                fr.setVisible(true);
            }
        });

    }
}