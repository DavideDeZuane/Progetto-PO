package GUI;

import javax.swing.*;

public interface GuiStatsPanel {
    /**
     * @param lblJobTot JLabel that////////////////////////////////////////////7
     * @param lblFullTimePercent
     * @param lblKeyWordRepeat
     */
    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent, JLabel lblKeyWordRepeat);

    /**
     * @param lblJobTot
     * @param lblFullTimePercent
     */
    public void setTextLabelStats(JLabel lblJobTot, JLabel lblFullTimePercent);

    /**
     * @param width width of the panel
     * @param height height of the panel
     */
    public void setPanel(int width, int height);

    /**
     * @param txtPeriod
     * @param keyWord
     */
    public void check(JTextField txtPeriod, String keyWord);

    /**
     *
     * @param txtPeriod
     */
    public void check(JTextField txtPeriod);


    public void setCharts();


    public void showChart();
}
