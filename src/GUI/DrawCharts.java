package GUI;

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
import java.util.HashSet;


public class DrawCharts{

    private JFreeChart chart;
    private DefaultPieDataset dataset;
    private XYSeries series;
    private XYSeriesCollection seriesCollection;
    private StatsJobBoard statsJobBoard = new StatsJobBoard();
    private JFreeChart barChart;
    private JFreeChart pieChart;
    private HashSet<Job> jobs;

    /**
     * constructor
     * @param jobs HashSet of Job
     * @param barChart bar chart
     * @param pieChart pie chart
     * @param statsJobBoard Object of the class StatsJobBoard
     */
    public DrawCharts(HashSet<Job> jobs, JFreeChart barChart, JFreeChart pieChart, StatsJobBoard statsJobBoard){
        this.statsJobBoard = statsJobBoard;
        this.barChart = barChart;
        this.pieChart = pieChart;
        this.jobs = jobs;
}

    /**
     * this method gets a bar chart
     * @return a bar chart
     */
    public JFreeChart getBarChart() {
        return barChart;
    }

    /**
     * this method gets a pie chart
     * @return a pie chart
     */
    public JFreeChart getPieChart() {
        return pieChart;
    }

    /**
     * this method gets the HashSet of Job
     * @return the HashSet of Job jobs
     */
    public HashSet<Job> getJobs() {
        return jobs;
    }

    /**
     * this method creates a bar chart from a certain dataset
     */
    public void drawBarChart()
{
    series = new XYSeries("Number of Jobs");
    for(int i=0; i<30; i++)
        series.add(i, statsJobBoard.dateOfCreationBis()[i]);
    seriesCollection = new XYSeriesCollection();
    seriesCollection.addSeries(series);

    barChart = ChartFactory.createXYBarChart("Date of creation", "Days", false, "Jobs", seriesCollection, PlotOrientation.VERTICAL, true, true, false);
    ChartFrame frame1 = new ChartFrame("Charts", barChart);

    XYPlot plot1= (XYPlot) barChart.getXYPlot();
    TickUnitSource ticks = NumberAxis.createIntegerTickUnits();
    plot1.getDomainAxis().setRange(0,30);
    plot1.getDomainAxis().setStandardTickUnits(ticks);
    //plot1.getRangeAxis().setRange(0, 12); //da migliorare
}

    /**
     * this method creates a pie chart from a certain dataset
     */
    public void drawPieChart()
{
    dataset = new DefaultPieDataset();
    this.dataset.setValue("Full Time", statsJobBoard.calculatePercentage());
    this.dataset.setValue("Part time", 100.0-statsJobBoard.calculatePercentage());
    pieChart = ChartFactory.createPieChart("Pie Chart", dataset, true, true, true);
    ChartFrame frame = new ChartFrame("Charts", pieChart);
    PiePlot plot = (PiePlot) pieChart.getPlot();
    plot.setIgnoreNullValues(true);}

    /**
     * this method creates a panel that contains the pie chart and the bar chart
     */
    public void setPanel(){
        JFrame fr = new JFrame("Chart");
        fr.getContentPane().add(new ChartPanel(this.getPieChart()), BorderLayout.WEST);
        fr.getContentPane().add(new ChartPanel(this.getBarChart()), BorderLayout.EAST);
        fr.pack();
        fr.setVisible(true);
    }
}
