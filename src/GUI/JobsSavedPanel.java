package GUI;

import Model.PickedJobs;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class JobsSavedPanel extends JFrame{

    private JPanel jobsSavedPanel;
    private JButton btnStats;
    private JButton btnExit;
    private JButton deleteButton;
    private JButton btnInternetPage;
    private JButton btnDeleteAll;
    private JButton btnHowToApply;
    private JTable tableJobs;

    private Desktop desktop = Desktop.getDesktop();

    private PickedJobs pickedJobs;

    private GuiJobsPanelManagement guiJobsPanelManagement;

    private final int widthPanel = 625;
    private final int heightPanel = 695;

    private final int widthTable = 625;
    private final int heightTable = 600;

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

        guiJobsPanelManagement = new GuiJobsPanelManagement(jobsSavedPanel, "Jobs Saved Panel");
        guiJobsPanelManagement.createTable(this.tableJobs, this.pickedJobs, this.widthTable, this.heightTable);
        guiJobsPanelManagement.setPanel(this.widthPanel, this.heightPanel);


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
                try {

                    if(pickedJobs.getCompany_url(guiJobsPanelManagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()).toString().equals("http://http")){
                        JOptionPane.showMessageDialog(jobsSavedPanel, "The link is not available");
                    }
                    else{
                        desktop.browse(pickedJobs.getCompany_url(guiJobsPanelManagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()).toURI());
                    }

                }catch (IOException ioException) {
                    ioException.printStackTrace();

                } catch (URISyntaxException uriSyntaxException) {
                    JOptionPane.showMessageDialog(jobsSavedPanel, "The link is wrong");
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(jobsSavedPanel,"     Bro, jobs ain't found");
                }
            }
        });

        btnHowToApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Object[] options = { "Copy on clip board", "Exit" };

                    int result = JOptionPane.showOptionDialog(jobsSavedPanel,pickedJobs.getHow_to_apply(guiJobsPanelManagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()) ,"Information",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
                            null, options, options[0]);

                    if (result == JOptionPane.YES_OPTION){
                        StringSelection selection = new StringSelection(pickedJobs.getHow_to_apply(guiJobsPanelManagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()));
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(selection, selection);
                        JOptionPane.showMessageDialog(jobsSavedPanel,"Text successfully copied to the clip board");
                    }

                }catch(Exception exception){
                    JOptionPane.showMessageDialog(jobsSavedPanel,"     Bro, jobs ain't found");
                }
            }
        });

        btnDeleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] options = { "Yes", "No" };

                int result = JOptionPane.showOptionDialog(null, "           Are you sure?", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);

                if (result == JOptionPane.YES_OPTION) {
                    pickedJobs.deleteAll();
                    pickedJobs.getJobs().clear();
                    guiJobsPanelManagement.dispose();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] options = { "Yes", "No" };

                int result = JOptionPane.showOptionDialog(null, "           Are you sure?", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);

                if (result == JOptionPane.YES_OPTION){
                    try {
                        pickedJobs.deleteJob(pickedJobs.getJob(guiJobsPanelManagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()).getId());


                        guiJobsPanelManagement.dispose();
                        //TableModel model = new PlayerTableModel(pickedJobs.setTableJobs(pickedJobs.getJobs().iterator(),pickedJobs.getNumOfJobs(), COLUMNS), columnHeaders);
                        //table.setModel(model);
                        new JobsSavedPanel(pickedJobs);
                        //tableJobs.setModel((TableModel) tableJobs);                      //tableJobs.

                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiJobsPanelManagement.dispose();
            }
        });
    }
}