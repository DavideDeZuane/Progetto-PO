package GUI;

import Controller.CheckOffer;
import Model.Job;
import Model.JobBoard;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;

public class GuiMenagement extends JFrame {

    private JTable tableJobs;
    private JPanel panel;
    private String namePanel;

    private TableColumn tableColumn;

    private TableColumn tColumn;

    private final static int COLUMNS = 4;

    private Object[] columnHeaders = {"Type","Company","Location","Title"};

    private CheckOffer checkOffer = new CheckOffer();

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
        setSize(625, 600);
        setVisible(true);


        //TableColumn col = this.tableJobs.getColumnModel().getColumn(0);
        //col.setCellRenderer(new MyTableCellRenderer());

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
        setSize(625, 725);
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

    public void guiVerifyOffer(int row, Job job){
        checkOffer.verify(job);

        if(!checkOffer.verify(job)){

            //tableJobs.setForeground(Color.RED);
            //tableJobs.setForeground(Color.RED);


        }
    }

    public void removeRowPanel(int index){
        /*
        DefaultTableModel model = (DefaultTableModel) tableJobs.getModel();
        model.removeRow(index);*/
    }


    static class MyTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //MyTableModel model = (MyTableModel) table.getModel();
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if(row == 0){
                c.setBackground(Color.RED);
            }

            return c;
        }
    }


}
