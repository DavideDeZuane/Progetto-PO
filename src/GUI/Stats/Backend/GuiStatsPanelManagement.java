package GUI.Stats.Backend;

import GUI.Stats.Frontend.DrawCharts;
import GUI.GuiManagement;
import Model.Job;
import Model.StatsJobBoard;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import Exception.GuiOptionPaneException;

import javax.swing.*;
import java.util.HashSet;

public class GuiStatsPanelManagement extends GuiManagement implements GuiStatsPanel {

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
    private JFreeChart barchart;

    /**
     * constructor
     * @param panel panel
     * @param namePanel name of the panel
     * @param jobs HashSet of Job
     * @param keyWord key word
     * @param statsJobBoard object of StatsJobBoard type
     */
    public GuiStatsPanelManagement(JPanel panel, String namePanel, HashSet<Job> jobs, String keyWord, StatsJobBoard statsJobBoard){
        super(panel, namePanel);
        this.statsJobBoard = statsJobBoard;
        this.statsJobBoard.setJobs(jobs);
        this.statsJobBoard.setKeyWord(keyWord);

    }

    /**
     * constructor
     * @param panel panel
     * @param namePanel name of the panel
     * @param jobs HashSet of Job
     * @param statsJobBoard object of StatsJobBoard type
     */
    public GuiStatsPanelManagement(JPanel panel, String namePanel, HashSet<Job> jobs, StatsJobBoard statsJobBoard){
        super(panel, namePanel);
        this.statsJobBoard = statsJobBoard;
        this.statsJobBoard.setJobs(jobs);

    }

    /**
     * this method checks if the user typed in a key word, if so the method will tell the user how many times that key word
     * was repeated in the jobs descriptions
     * @param lblJobTot label where with the total number of jobs
     * @param lblFullTimePercent label with the percentage of full time jobs
     * @param lblKeyWordRepeat label with the number of times that the keyword is repeated
     */
    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent, JLabel lblKeyWordRepeat){
        lblJobTot.setText(String.valueOf(statsJobBoard.getNumOfJobs()));
        lblFullTimePercent.setText(String.valueOf(statsJobBoard.calculatePercentage()) + "%");

        if(!this.statsJobBoard.getKeyWord().equals("")){
            lblKeyWordRepeat.setText("The key word " + statsJobBoard.getKeyWord() + " was repeated " +
                    statsJobBoard.keyWords(statsJobBoard.getKeyWord()) + " times in the job descriptions.");
        }
        else{
            lblKeyWordRepeat.setText("No key word");
        }
    }

    /**
     * this method sets the text of the two labels
     * @param lblJobTot label where with the total number of jobs
     * @param lblFullTimePercent label with the percentage of full time jobs
     */
    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent){
        lblJobTot.setText(String.valueOf(statsJobBoard.getNumOfJobs()));
        lblFullTimePercent.setText(String.valueOf(statsJobBoard.calculatePercentage()) + "%");
    }

    /**
     * this method sets a panel with the specified dimentions
     * @param width width of the panel
     * @param height height of the panel
     */
    public void setPanel(int width, int height){
        add(super.getPanel());
        setTitle(super.getNamePanel()); //600, 300
        setSize(width, height);
        setResizable(false);
        setVisible(true);
    }

    /**
     * this method allows the user to type in a keyword and a number of days and returns the occurrences of the key word in the jobs description
     * and the number of jobs that were created in the last "number typed" days
     * @param txtPeriod label where the user will type in a number witch represents the days
     * @param keyWord key word the user will type in
     */
    public void check(JTextField txtPeriod, String keyWord){

        statsJobBoard.setKeyWord(keyWord);

        try {
            if (!this.statsJobBoard.getKeyWord().equals(""))
                throw new GuiOptionPaneException("There were created " +
                        statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                        txtPeriod.getText() + " days with key word: " + statsJobBoard.getKeyWord());
            else
                throw new GuiOptionPaneException("There were created " +
                        statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                        txtPeriod.getText() + " days.");

        }catch (GuiOptionPaneException guiOptionPaneException){
            JOptionPane.showMessageDialog(super.getPanel(),guiOptionPaneException.getMessage());
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"  Bro, you can't write this.");
        }
    }

    /**
     * this method allows the user to type a number of days and returns the number of jobs that were created in the last "number typed" days
     * @param txtPeriod label where the user will type in a number witch represents the days
     */
    public void check(JTextField txtPeriod){

        try {
            JOptionPane.showMessageDialog(super.getPanel(), "There were created " +
                    statsJobBoard.dateOfCreation(Integer.parseInt(txtPeriod.getText())) + " offers in the last " +
                    txtPeriod.getText() + " days.");

        }catch (Exception exception){
            JOptionPane.showMessageDialog(super.getPanel(),"  Bro, you can't write this.");
        }
    }

    /**
     * this method sets a pie chart and a bar chart by creating a object of the class DrawCharts
     */
    public void setCharts(){
        drawCharts = new DrawCharts (this.statsJobBoard.getJobs(), barChart, pieChart, statsJobBoard);
        drawCharts.drawPieChart();
        drawCharts.drawBarChart();
    }

    /**
     * this method will allow to see the charts
     */
    public void showChart(){
        drawCharts.setPanel();
    }
}
