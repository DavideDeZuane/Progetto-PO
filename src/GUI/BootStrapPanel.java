package GUI;

import Controller.FileController;
import Model.PickedJobs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BootStrapPanel extends JFrame{

    private JPanel rootPanel;
    private JTextField txtLocation1;
    private JTextField txtDescription;
    private JButton btnSearch;
    private JButton btnShowJobsSaved;
    private JCheckBox fullTime;
    private JTextField txtLocation2;
    private JButton btnShowStatus;

    private PickedJobs job;

    private final int widthPanel = 600;
    private final int heightPanel = 200;

    private GuiJobsPanelManagement guiJobsPanelManagement;


    public BootStrapPanel() {

        guiJobsPanelManagement = new GuiJobsPanelManagement(rootPanel, "IT Found Jobs");
        guiJobsPanelManagement.setPanel(this.widthPanel, this.heightPanel);

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

                guiJobsPanelManagement.search(job, pickedJobs, txtLocation1, txtLocation2, txtDescription, fullTime);
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

        btnShowStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //guiJobsPanelManagement.createTable();

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
                    new StatusJobs(pickedJobs);
                }else{
                    JOptionPane.showMessageDialog(rootPanel,"bro, there ain't saved jobs");
                }

                //new StatusJobs();
            }
        });
    }
}