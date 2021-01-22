package GUI.Stats.Frontend;

import GUI.Stats.Backend.GuiStatsPanelManagement;
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

    /**
     * this constructor is implemented to make the user take a look at the stats made from the jobs saved panel
     * - the user can press on the show chart to see charts
     * - the user can type in a key word and see how many times that word occurred in the jobs found description
     * - the user can type in a number and see how many jobs were created number days ago
     * @param jobs HashSet that contains all the jobs found
     */
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
