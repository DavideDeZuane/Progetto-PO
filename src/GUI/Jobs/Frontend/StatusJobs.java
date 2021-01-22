package GUI.Jobs.Frontend;

import GUI.Jobs.Backend.GuiJobsPanelManagement;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class is useful to print out the multithread result
public class StatusJobs extends JFrame{

    private GuiJobsPanelManagement guiJobsPanelManagement;
    private JPanel statusJobs;
    private JButton btnRefresh;
    private JTable statusJobsTable;

    private final int widthTable = 625;
    private final int heightTable = 600;

    private final int widthPanel = 625;
    private final int heightPanel = 625;

    private Object[] columnHeaders = {"Type","Company","Location","Title", "Status"};

    public StatusJobs(PickedJobs pickedJobs){
        guiJobsPanelManagement = new GuiJobsPanelManagement(statusJobs, "Status Jobs");
        guiJobsPanelManagement.createTable(statusJobsTable,pickedJobs,widthTable,heightTable,columnHeaders);
        guiJobsPanelManagement.setPanel(widthPanel, heightPanel);
        //statusJobs,widthTable,heightTable,columnHeaders
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelManagement.dispose();
                guiJobsPanelManagement = new GuiJobsPanelManagement(statusJobs, "Status Jobs");
                guiJobsPanelManagement.createTable(statusJobsTable,pickedJobs,widthTable,heightTable,columnHeaders);
                guiJobsPanelManagement.setPanel(widthPanel, heightPanel);
            }
        });
    }

}
