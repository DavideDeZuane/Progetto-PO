package GUI;

import Controller.ApiController;
import Controller.PARAMETERES;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.EnumSet;

public class BootstrapPanel extends JFrame{

    private JPanel rootPanel;
    private JTextField txtLocation;
    private JTextField txtDescription;
    private JButton btnSearch;
    private JButton btnShowJobsSaved;
    private JCheckBox FullTime;
    URL url = null;
    private String filters[] = new String[100];

    private ApiController apiController = null;


    public BootstrapPanel() {


        add(rootPanel);
        setTitle("IT Found Jobs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setVisible(true);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                inserisciCampi();
                apiController = new ApiController();

/*
                if(!txtDescription.equals("")){
                    EnumSet<PARAMETERES> flags = EnumSet.of(PARAMETERES.LOCATION, PARAMETERES.DESCRIPTION);
                }*/
                EnumSet<PARAMETERES> flags = EnumSet.of(PARAMETERES.LOCATION, PARAMETERES.DESCRIPTION);

                try {

                    url = ApiController.query(filters, flags);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                new JobsFoundPanel();
            }
        });

        btnShowJobsSaved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobsSavedPanel();
            }
        });
    }

    public void inserisciCampi(){
        filters[0] = txtDescription.getText();
        filters[1] = txtLocation.getText();
    }
}
