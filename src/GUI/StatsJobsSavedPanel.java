package GUI;

import Model.Job;
import Model.StatsJobBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class StatsJobsSavedPanel extends JFrame {

    private JLabel lblFullTimePercent;
    private JLabel lblJobTot;
    private JTextField txtPeriod;
    private JButton check;
    private JButton showChartButton;
    private JPanel statsJobsSavedPanel;
    private final StatsJobBoard statsJobBoard;

    private GuiStatsPanelManagement guiStatsPanelMenagement;

    public StatsJobsSavedPanel(HashSet<Job> jobs){
        statsJobBoard = new StatsJobBoard();
        statsJobBoard.setJobs(jobs);

        guiStatsPanelMenagement = new GuiStatsPanelManagement(statsJobsSavedPanel, "Stats jobs found", jobs, statsJobBoard);
        guiStatsPanelMenagement.setCharts();
        guiStatsPanelMenagement.setTextLabelStats(lblJobTot, lblFullTimePercent);
        guiStatsPanelMenagement.setPanel(600, 145);

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                guiStatsPanelMenagement.check(txtPeriod);
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
