package GUI;

import Controller.ApiController;
import Controller.FileController;
import Controller.GuiController;
import Model.Job;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;

public class BootStrapPanel extends JFrame{

    private JPanel rootPanel;
    private JTextField txtLocation1;
    private JTextField txtDescription;
    private JButton btnSearch;
    private JButton btnShowJobsSaved;
    private JCheckBox fullTime;
    private JTextField txtLocation2;

    private PickedJobs job;

    private ApiController apiController = null;
    private GuiController guiController = null;



    private HashSet<Job> offers;

    //private HashSet<Job> openOffers = new HashSet<>();


    public BootStrapPanel() {
        add(rootPanel);
        setTitle("IT Found Jobs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        //getContentPane().setBackground(new Color(0x000000));
        setVisible(true);


        try {
            job = new PickedJobs();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        //ImageIcon image = new ImageIcon("src/GUI/femto.png");
        //setIconImage(image.getImage());
        //getContentPane().setBackground(new Color(0xFFFFFF));


        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                apiController = new ApiController();
                guiController = new GuiController();

                //offers = new HashSet<>();

                job.getJobs().clear();
                //todo configurazione file
                /*
                try {
                    System.out.println(ApiController.readConfigurationFile());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }*/

                boolean flag = false;

                try {

                    job.setKeyWord(txtDescription.getText());

                    if(!txtLocation1.getText().equals(txtLocation2.getText())) {
                        if (!txtLocation1.getText().equals("")) {
                            apiController.setUrl(ApiController.query(guiController.setFilters(txtLocation1, txtDescription, fullTime.isSelected())));
                            apiController.save(job.getJobs());
                            flag = true;
                        }

                        if (!txtLocation2.getText().equals("")) {
                            apiController.setUrl(ApiController.query(guiController.setFilters(txtLocation2, txtDescription, fullTime.isSelected())));
                            apiController.save(job.getJobs());
                            flag = true;
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(rootPanel, "You can't insert the same location in the fields");
                    }

                    if(flag == true){
                        if (!job.getJobs().isEmpty()) {
                            new JobsFoundPanel(job);

                        } else
                            JOptionPane.showMessageDialog(rootPanel, "     Bro, jobs ain't found");
                    }
                    else{
                        JOptionPane.showMessageDialog(rootPanel, " You must insert the location");
                    }



                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(rootPanel, "Invalid filters");
                }
            }
        });

        btnShowJobsSaved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PickedJobs pickedJobs = null;
                FileController fileController = null;
                try {
                    pickedJobs = new PickedJobs();
                    fileController = new FileController("PickedJobs.txt");
                    fileController.readJobsFromFile(pickedJobs.getJobs());
                }catch (IOException exception) {
                    exception.printStackTrace();
                }

                if(!pickedJobs.getJobs().isEmpty()){
                    new JobsSavedPanel(pickedJobs);
                }else{
                    JOptionPane.showMessageDialog(rootPanel,"bro, there ain't saved jobs");
                }
            }
        });
    }


    public String getTxtDescription(){
        return txtDescription.getText();
    }
}