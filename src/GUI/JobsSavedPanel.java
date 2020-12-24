package GUI;

import Model.PickedJobs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobsSavedPanel extends JFrame{

    private JPanel jobsSavedPanel;
    private JButton btnStats;
    private JButton btnExit;
    private JButton btnDelete;
    private JButton btnInternetPage;
    private JButton btnDeleteAll;
    private JButton btnHowToApply;
    private JTable tableJobs;

    private PickedJobs pickedJobs;

    private GuiJobsPanelManagement guiJobsPanelMenagement;

    private final int widthPanel = 625;
    private final int heightPanel = 695;

    private final int widthTable = 625;
    private final int heightTable = 600;

    //private Object[] columnHeaders = {"Type","Company","Location","Title", "Status"};

    public JobsSavedPanel(PickedJobs pickedJobs){

        this.pickedJobs = pickedJobs;

        guiJobsPanelMenagement = new GuiJobsPanelManagement(jobsSavedPanel, "Jobs Saved Panel");
        guiJobsPanelMenagement.createTable(this.tableJobs, this.pickedJobs, this.widthTable, this.heightTable);
        guiJobsPanelMenagement.setPanel(this.widthPanel, this.heightPanel);


        btnStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    new StatsJobsSavedPanel(pickedJobs.getJobs());
                }catch (Exception exception){
                    //JOptionPane.showMessageDialog(jobsSavedPanel,"     Bro, jobs ain't saved");
                    //exception.fillInStackTrace();
                    exception.printStackTrace();
                }
            }
        });

        btnInternetPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelMenagement.showInternetPage(pickedJobs, guiJobsPanelMenagement.getTableJobs().getSelectedRow());
            }
        });

        btnHowToApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelMenagement.showHowToApply(pickedJobs, guiJobsPanelMenagement.getTableJobs().getSelectedRow());
            }
        });

        btnDeleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelMenagement.deleteAllJobs(pickedJobs);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelMenagement.deleteJobs(pickedJobs, guiJobsPanelMenagement.getTableJobs().getSelectedRow());
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelMenagement.dispose();
            }
        });
    }
}