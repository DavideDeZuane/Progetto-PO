package GUI;

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

public interface GuiStatsPanel {
    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent, JLabel lblKeyWordRepeat);
    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent);
    public void setPanel(int width, int height);
    public void check(JTextField txtPeriod, String keyWord);
    public void check(JTextField txtPeriod);
    public void setCharts();
    public void showChart();
}
