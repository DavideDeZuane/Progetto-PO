package GUI;

import Controller.ApiController;
import Controller.GuiApiController;
import Model.Job;
import Model.JobBoard;
import Model.PickedJobs;

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
        //getContentPane().setBackground(new Color(0x000000));
        setVisible(true);

        //ImageIcon image = new ImageIcon("src/GUI/femto.png");
        //setIconImage(image.getImage());
        //getContentPane().setBackground(new Color(0xFFFFFF));


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
                    //offers.addAll(apiController.parsing());
                    apiController.setJobBoard(job);
                    job.setJobs(offers);
                    job.setKeyWord(txtDescription.getText());

                    if (apiController.parsing()) {
                        new JobsFoundPanel(job);

                    } else
                        JOptionPane.showMessageDialog(rootPanel, "     Bro, jobs ain't found");


                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(rootPanel, "Invalid filters");
                }
            }
        });

        btnShowJobsSaved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PickedJobs pickedJobs = null;

                try {
                    pickedJobs = new PickedJobs();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                try {
                    if(pickedJobs.setJobsFromFile()){
                        new JobsSavedPanel(pickedJobs);
                    }else{
                        JOptionPane.showMessageDialog(rootPanel,"bro, there ain't saved jobs");
                    }
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(rootPanel,"Error Jobs not found.");
                }
            }
        });
    }


    public String getTxtDescription(){
        return txtDescription.getText();
    }
}