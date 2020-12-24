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

    private final int widthPanel = 600;
    private final int heightPanel = 200;

    private GuiJobsPanelMenagement guiJobsPanelMenagement;


    public BootStrapPanel() {

        guiJobsPanelMenagement = new GuiJobsPanelMenagement(rootPanel, "IT Found Jobs");
        guiJobsPanelMenagement.setPanel(this.widthPanel, this.heightPanel);

        /*
        add(rootPanel);
        setTitle("IT Found Jobs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        //getContentPane().setBackground(new Color(0x000000));
        setVisible(true);
*/

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

                PickedJobs pickedJobs = null;
                FileController fileController = null;
                try {
                    pickedJobs = new PickedJobs();
                    fileController = new FileController("PickedJobs.txt");
                    fileController.readJobsFromFile(pickedJobs.getJobs());
                }catch (IOException exception) {
                    exception.printStackTrace();
                }

                guiJobsPanelMenagement.search(job, pickedJobs, txtLocation1, txtLocation2, txtDescription, fullTime);
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
                    JOptionPane.showMessageDialog(rootPanel,"bro, there ain't saved jobs");
                    //exception.printStackTrace();
                }

                if(!pickedJobs.getJobs().isEmpty()){
                    new JobsSavedPanel(pickedJobs);
                }else{
                    JOptionPane.showMessageDialog(rootPanel,"bro, there ain't saved jobs");
                }
            }
        });
    }
}