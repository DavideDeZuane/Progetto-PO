package GUI;

import Model.StatsJobBoard;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Model.Job;
import Model.StatsJobBoard;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class GuiStatsPanelMenagement extends GuiMenagement implements GuiStatsPanel{

    private JPanel statsJobsFoundPanel;
    private JLabel lblFullTimePercent;
    private JLabel lblJobTot;
    private JTextField txtPeriod;
    private JButton check;
    private JLabel lblKeyWordRepeat;
    private JButton showChartButton;
    private final StatsJobBoard statsJobBoard;
    private JFreeChart chart;
    private DefaultPieDataset dataset;
    private XYSeries series;
    private XYSeriesCollection seriesCollection;
    private DrawCharts drawCharts;
    private JFreeChart barChart;
    private JFreeChart pieChart;


    JFreeChart barchart;

    public GuiStatsPanelMenagement(JPanel panel, String namePanel, HashSet<Job> jobs, String keyWord, StatsJobBoard statsJobBoard){
        super(panel, namePanel);
        this.statsJobBoard = statsJobBoard;
        this.statsJobBoard.setJobs(jobs);
        this.statsJobBoard.setKeyWord(keyWord);

    }

    public GuiStatsPanelMenagement(JPanel panel, String namePanel, HashSet<Job> jobs, StatsJobBoard statsJobBoard){
        super(panel, namePanel);
        this.statsJobBoard = statsJobBoard;
        this.statsJobBoard.setJobs(jobs);

    }

    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent, JLabel lblKeyWordRepeat){
        lblJobTot.setText(String.valueOf(statsJobBoard.getNumOfJobs()));
        lblFullTimePercent.setText(String.valueOf(statsJobBoard.calculatePercentage()) + "%");

        if(!this.statsJobBoard.getKeyWord().equals("")){
            lblKeyWordRepeat.setText("The key word " + statsJobBoard.getKeyWord() + " was repeated " +
                    statsJobBoard.keyWords(statsJobBoard.getKeyWord()) + " times in the job descriptions.");
        }
        else{
            lblKeyWordRepeat.setText("There aren't key word.");
        }


    }

    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent){
        lblJobTot.setText(String.valueOf(statsJobBoard.getNumOfJobs()));
        lblFullTimePercent.setText(String.valueOf(statsJobBoard.calculatePercentage()) + "%");
    }

    public void setPanel(int width, int height){
        add(super.getPanel());
        setTitle(super.getNamePanel()); //600, 300
        setSize(width, height);
        setResizable(false);
        setVisible(true);
    }

    public void check(JTextField txtPeriod, String keyWord){

        statsJobBoard.setKeyWord(keyWord);

        try {
            if (!this.statsJobBoard.getKeyWord().equals(""))
                JOptionPane.showMessageDialog(super.getPanel(), "There were created " +
                        statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                        txtPeriod.getText() + " days with key word: " + statsJobBoard.getKeyWord());

            else
                JOptionPane.showMessageDialog(super.getPanel(), "There were created " +
                        statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                        txtPeriod.getText() + " days.");

        }catch (Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"  Bro, you can't write this.");
        }
    }

    public void check(JTextField txtPeriod){

        try {
            JOptionPane.showMessageDialog(super.getPanel(), "There were created " +
                    statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                    txtPeriod.getText() + " days.");

        }catch (Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"  Bro, you can't write this.");
        }
    }

    public void setCharts(){

        drawCharts = new DrawCharts (this.statsJobBoard.getJobs(), barChart, pieChart, statsJobBoard);
        drawCharts.drawPieChart();
        drawCharts.drawBarChart();


    }

    public void showChart(){
        drawCharts.setPanel();
    }
}
