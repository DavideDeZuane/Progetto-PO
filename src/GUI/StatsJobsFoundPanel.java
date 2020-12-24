package GUI;

import Model.Job;
import Model.StatsJobBoard;

import javax.swing.*;
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

    private String myKeyWord;

    private GuiStatsPanelManagement guiStatsPanelMenagement;

    private final int widthPanel = 600;
    private final int heightPanel = 165;

    public StatsJobsFoundPanel(HashSet<Job> jobs, String keyWord){

        this.myKeyWord = keyWord;

        statsJobBoard = new StatsJobBoard();
        guiStatsPanelMenagement = new GuiStatsPanelManagement(statsJobsFoundPanel, "Stats jobs found", jobs, keyWord, statsJobBoard);
        guiStatsPanelMenagement.setCharts();
        guiStatsPanelMenagement.setTextLabelStats(lblJobTot, lblFullTimePercent, lblKeyWordRepeat);
        guiStatsPanelMenagement.setPanel(this.widthPanel, this.heightPanel);


        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                guiStatsPanelMenagement.check(txtPeriod, myKeyWord);
            }
        });

        showChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiStatsPanelMenagement.showChart();
            }
        });
    }

}