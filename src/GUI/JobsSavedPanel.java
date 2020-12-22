package GUI;

import Controller.CheckOffer;
import Model.Job;
import Model.JobBoard;
import Model.PickedJobs;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;

public class JobsSavedPanel extends JFrame{

    private JPanel jobsSavedPanel;
    private JButton btnStats;
    private JButton btnExit;
    private JButton deleteButton;
    private JButton btnInternetPage;
    private JButton btnDeleteAll;
    private JButton btnHowToApply;
    private JTable tableJobs;
    //private JobBoard job = new JobBoard();
    //private HashSet<Job> jobs;
    private String keyWord;

    private final static int COLUMNS = 3;
    //private Object[] columnHeaders = {"Type","Company","Location"};


    private Desktop desktop = Desktop.getDesktop();

    private PickedJobs pickedJobs;

    GuiMenagement guiMenagement;

    public JobsSavedPanel(PickedJobs pickedJobs){

        this.pickedJobs = pickedJobs;

        guiMenagement = new GuiMenagement(jobsSavedPanel, "Jobs Saved Panel");
        guiMenagement.createTable(tableJobs, this.pickedJobs);


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

                    if(pickedJobs.getCompany_url(guiMenagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()).toString().equals("http://http")){
                        JOptionPane.showMessageDialog(jobsSavedPanel, "The link is not available");
                    }
                    else{
                        desktop.browse(pickedJobs.getCompany_url(guiMenagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()).toURI());
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

                    int result = JOptionPane.showOptionDialog(jobsSavedPanel,pickedJobs.getHow_to_apply(guiMenagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()) ,"Information",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
                            null, options, options[0]);

                    if (result == JOptionPane.YES_OPTION){
                        StringSelection selection = new StringSelection(pickedJobs.getHow_to_apply(guiMenagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()));
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
                    guiMenagement.dispose();
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
                        pickedJobs.deleteJob(pickedJobs.getJob(guiMenagement.getTableJobs().getSelectedRow(), pickedJobs.getJobs()).getId());

                        guiMenagement.dispose();
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
                guiMenagement.dispose();
            }
        });
    }
}
