package GUI;

import Model.Job;
import Model.StatsJobBoard;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class StatsJobsSavedPanel extends JFrame {

    private JPanel statsPanel;
    private JLabel lblFullTimePercent;
    private JLabel lblJobTot;
    private JTextField txtPeriod;
    private JButton check;
    private JLabel lblKeyWordRepeat;
    private JButton showChartButton;
    private JPanel statsJobsSavedPanel;
    private final StatsJobBoard statsJobBoard;
    JFreeChart chart;
    private DefaultPieDataset dataset;

    public StatsJobsSavedPanel(HashSet<Job> jobs){
        //super(jobs, keyWord);

        statsJobBoard = new StatsJobBoard();
        statsJobBoard.setJobs(jobs);

        dataset = new DefaultPieDataset();
        this.dataset.setValue("Full Time", statsJobBoard.calculatePercentage());
        this.dataset.setValue("Part time", 100.0-statsJobBoard.calculatePercentage());
        chart = ChartFactory.createPieChart("Pie Chart", dataset, true, true, true);

        lblJobTot.setText(String.valueOf(statsJobBoard.getNumOfJobs()));
        lblFullTimePercent.setText(String.valueOf(statsJobBoard.calculatePercentage()) + "%");

        ChartFrame frame = new ChartFrame("Chart", chart);
        frame.pack();
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setIgnoreNullValues(true);

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
                frame.setVisible(true);
            }
        });

    }
}
