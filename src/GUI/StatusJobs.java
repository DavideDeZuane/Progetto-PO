package GUI;

import Model.PickedJobs;

import javax.swing.*;

//this class is useful to print out the multithread result
public class StatusJobs extends JFrame{

    private GuiJobsPanelManagement guiJobsPanelManagement;
    private JPanel statusJobs;
    private JTable statusJobsTable;

    private final int widthTable = 625;
    private final int heightTable = 600;

    private Object[] columnHeaders = {"Type","Company","Location","Title", "Status"};

    public StatusJobs(PickedJobs pickedJobs){
        guiJobsPanelManagement = new GuiJobsPanelManagement(statusJobs, "Status Jobs");
        guiJobsPanelManagement.createTable(statusJobsTable,widthTable,heightTable,columnHeaders);
        guiJobsPanelManagement.setPanel(widthTable, heightTable);
        //statusJobs,widthTable,heightTable,columnHeaders
    }

}
