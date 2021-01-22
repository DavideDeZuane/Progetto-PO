package GUI.Jobs.Frontend;

import Controller.FileController;
import GUI.Jobs.Backend.GuiJobsPanelManagement;
import GUI.Stats.Frontend.StatsJobsSavedPanel;
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

    private String X = "0";
    private String Y = "0";


    //private Object[] columnHeaders = {"Type","Company","Location","Title", "Status"};

    /**
     * constructor that created a table and a panel in witch there are some buttons:
     * - the stats button allows the user to see the stats of the jobs the user saved
     * - the internet page button shows the user the internet page of the job the user clicked on
     * - the how to apply button allows the user to take a look at the how to apply field of the job the user selected
     * - the delete all button allows the user to delete all the jobs the user saved
     * - the delete button allows the user to delete the job the user clicked on
     * - the exit button allows the user to exit the panel
     * @param pickedJobs object of the class PickedJobs
     */
    public JobsSavedPanel(PickedJobs pickedJobs){

        this.pickedJobs = pickedJobs;

        guiJobsPanelMenagement = new GuiJobsPanelManagement(jobsSavedPanel, "Jobs Saved Panel");
        guiJobsPanelMenagement.createTable(this.tableJobs, this.pickedJobs, this.widthTable, this.heightTable);

        FileController.readLocationForm();

        guiJobsPanelMenagement.setPanel(this.widthPanel, this.heightPanel, Integer.parseInt(FileController.getX()), Integer.parseInt(FileController.getY()));


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