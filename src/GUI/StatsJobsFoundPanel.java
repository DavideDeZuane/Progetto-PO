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
    private JFreeChart chart;
    private DefaultPieDataset dataset;
    private XYSeries series;
    private XYSeriesCollection seriesCollection;

    public StatsJobsFoundPanel(HashSet<Job> jobs, String keyWord){

        statsJobBoard = new StatsJobBoard();
        statsJobBoard.setJobs(jobs);
        statsJobBoard.setKeyWord(keyWord);

        /*HistogramDataset datataset1 = new HistogramDataset();
        datataset1.setType(HistogramType.FREQUENCY);
        datataset1.addSeries("Jobs Created", statsJobBoard.dateOfCreationBis(), 30);
        JFreeChart histogram = ChartFactory.createHistogram("Date of Creation", "Days", "Jobs", datataset1, PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame2 = new ChartFrame("Charts", histogram);
        frame2.pack();
        XYPlot plot1 = (XYPlot) histogram.getPlot();
        Plot plot2 =  histogram.getPlot();*/

        series = new XYSeries("Number of Jobs");
        for(int i=0; i<30; i++)
            series.add(i, statsJobBoard.dateOfCreationBis()[i]);
        seriesCollection = new XYSeriesCollection();
        seriesCollection.addSeries(series);

        JFreeChart barchart = ChartFactory.createXYBarChart("Date of creation", "Days", false, "Jobs", seriesCollection, PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame1 = new ChartFrame("Charts", barchart);
        //frame1.pack();

        XYPlot plot1= (XYPlot) barchart.getXYPlot();
        TickUnitSource ticks = NumberAxis.createIntegerTickUnits();
        plot1.getDomainAxis().setRange(0,30);
        plot1.getDomainAxis().setStandardTickUnits(ticks);


       // TickUnitSource unitSource = new TickUnits();

       // plot1.getDomainAxis().setStandardTickUnits(unitSource);
       // plot1.getRangeAxis().setStandardTickUnits(unitSource);
        //ValueAxis numberAxis = plot1.getRangeAxis();


        //ValueAxis domainAxis = plot1.getDomainAxis();
        //domainAxis.setStandardTickUnits();

        //plot2.getDomainAxis().setAutoRange(true);

        //ValueAxis rangeAxis = plot1.getRangeAxis(30);
        //plot1.configureRangeAxes();
       // plot1.setRangeAxis(30,plot1.getRangeAxis(1));
        //plot1.setDomainAxis(30,plot1.getRangeAxis(0));


        dataset = new DefaultPieDataset();
        this.dataset.setValue("Full Time", statsJobBoard.calculatePercentage());
        this.dataset.setValue("Part time", 100.0-statsJobBoard.calculatePercentage());
        chart = ChartFactory.createPieChart("Pie Chart", dataset, true, true, true);
        ChartFrame frame = new ChartFrame("Charts", chart);
        frame.pack();
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setIgnoreNullValues(true);

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
                fr.getContentPane().add(new ChartPanel(chart), BorderLayout.EAST);
                fr.getContentPane().add(new ChartPanel(barchart), BorderLayout.WEST);
                fr.pack();
                fr.setVisible(true);
                //frame.setVisible(true);
                //frame2.setVisible(true);
                //histogramTest.setVisible(true);
            }
        });
    }

}
