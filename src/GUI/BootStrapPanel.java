package GUI;

import Controller.ApiController;
import Controller.GuiApiController;
import Model.Job;
import Model.JobBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;

public class BootStrapPanel extends JFrame{

    private JPanel rootPanel;
    private JTextField txtLocation;
    private JTextField txtDescription;
    private JButton btnSearch;
    private JButton btnShowJobsSaved;
    private JCheckBox fullTime;

    private JobBoard job = new JobBoard();

    private ApiController apiController = null;

    private HashSet<Job> offers = new HashSet<>();
    public BootStrapPanel() {

        add(rootPanel);
        setTitle("IT Found Jobs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setVisible(true);



        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                apiController = new GuiApiController();

                //todo configurazione file
                /*
                try {
                    System.out.println(ApiController.readConfigurationFile());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }*/

                try {
                    apiController.setUrl(ApiController.query(apiController.setFilters(txtLocation, txtDescription, fullTime.isSelected())));
                    offers.addAll(apiController.parsing());
                    job.setJobs(offers);
                    job.setKeyWord(txtDescription.getText());

                    if (apiController.parsing().isEmpty()) {
                        JOptionPane.showMessageDialog(rootPanel, "     Bro, jobs ain't found");
                    } else
                        new JobsFoundPanel(job);


                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(rootPanel, "Invalid filters");
                }
            }
        });

        btnShowJobsSaved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobsSavedPanel(txtDescription.getText());
            }
        });
    }

    public String getTxtDescription(){
        return txtDescription.getText();
    }
}