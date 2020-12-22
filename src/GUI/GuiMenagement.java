package GUI;

import Model.Job;
import Model.JobBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class GuiMenagement extends JFrame {

    private JTable tableJobs;
    private JPanel panel;
    private String namePanel;

    private final static int COLUMNS = 4;

    private Object[] columnHeaders = {"Type","Company","Location","Title"};

    public GuiMenagement(JPanel panel, String namePanel){
        this.namePanel = namePanel;
        this.panel = panel;
    }


    public void createTable(JTable tableJobs, JobBoard job){
        this.tableJobs = tableJobs;
        this.tableJobs = new JTable(this.setTable(job.getJobs().iterator(), job.getNumOfJobs(), COLUMNS), columnHeaders);
        this.tableJobs.setPreferredScrollableViewportSize(new Dimension(500,50));
        this.tableJobs.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(this.tableJobs);
        add(scrollPane);
        setSize(700, 600);
        setVisible(true);

        this.setPanel(this.panel, this.namePanel);
    }

    public JTable getTableJobs() {
        return tableJobs;
    }

    public void setTableJobs(JTable tableJobs) {
        this.tableJobs = tableJobs;
    }

    public void setPanel(JPanel panel, String namePanel){
        add(panel);
        setTitle(namePanel);
        setSize(700, 725);
        setResizable(false);
        setVisible(true);
    }

    public Object[][] setTable(Iterator iterator, int raws, int columns){
        Job tmp = null;
        int count = 0;

        Object[][] rowData = new String[raws][columns];

        for(int i = 0; i < raws; i++) {
            tmp = (Job) iterator.next();
            rowData[i][count++] = tmp.getType();
            rowData[i][count++] = tmp.getCompany();
            rowData[i][count++] = tmp.getLocation();
            rowData[i][count++] = tmp.getTitle();
            count = 0;
        }
        return rowData;
    }

}
