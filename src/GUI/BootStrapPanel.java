package GUI;

import Controller.ApiController;
import Controller.FileController;
import Controller.GuiController;
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
    private GuiController guiController = null;



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

                apiController = new ApiController();
                guiController = new GuiController();



                //todo configurazione file
                /*
                try {
                    System.out.println(ApiController.readConfigurationFile());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }*/

                try {
                    apiController.setUrl(ApiController.query(guiController.setFilters(txtLocation, txtDescription, fullTime.isSelected())));
                    //offers.addAll(apiController.parsing());
                    job.setJobs(offers);
                    job.setKeyWord(txtDescription.getText());
                    apiController.save(job.getJobs());

                    if (!job.getJobs().isEmpty()) {
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
                FileController fileController = null;
                try {
                    pickedJobs = new PickedJobs("PickedJobs.txt");
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