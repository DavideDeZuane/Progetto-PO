package GUI;

import javax.swing.*;

public interface GuiStatsPanel {
    /**
     * @param lblJobTot label where with the total number of jobs
     * @param lblFullTimePercent label with the percentage of full time jobs
     * @param lblKeyWordRepeat label with the number of times that the keyword is repeated
     */
    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent, JLabel lblKeyWordRepeat);

    /**
     * this is a overloading of the method setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent, JLabel lblKeyWordRepeat)
     * @param lblJobTot label where with the total number of jobs
     * @param lblFullTimePercent label with the percentage of full time jobs
     */
    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent);

    /**
     * @param width width of the panel
     * @param height height of the panel
     */
    public void setPanel(int width, int height);

    /**
     * @param txtPeriod text box where the user will type in the number of days
     * @param keyWord keyword
     */
    public void check(JTextField txtPeriod, String keyWord);

    /**
     * @param txtPeriod text box where the user will type in the number of days
     */
    public void check(JTextField txtPeriod);

    /**
     * this method sets the charts
     */
    public void setCharts();

    /**
     * this method shows the charts
     */
    public void showChart();
}
