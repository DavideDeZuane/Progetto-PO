package GUI;

import Controller.ApiController;
import Controller.PARAMETERES;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.EnumSet;
import java.util.Vector;

public class BootStrapPanel extends JFrame{

    private JPanel rootPanel;
    private JTextField txtLocation;
    private JTextField txtDescription;
    private JButton btnSearch;
    private JButton btnShowJobsSaved;
    private JCheckBox FullTime;
    URL url = null;
    private String filters[] = new String[100];

    private ApiController apiController = null;


    public BootStrapPanel() {

        add(rootPanel);
        setTitle("IT Found Jobs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setVisible(true);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                apiController = new ApiController();
                EnumSet<PARAMETERES> flags = setEumFilters();

                try {

                    url = ApiController.query(filters, flags);
                    new JobsFoundPanel(url); //passo l'url come parametro alla classe in modo da utilizzarlo in Jobs Found Panel

                } catch (MalformedURLException exception) {

                    JOptionPane.showMessageDialog(rootPanel, "bro non hai scritto niente");
                    //exception.printStackTrace();
                }


            }
        });

        btnShowJobsSaved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobsSavedPanel();
            }
        });
    }

    public EnumSet setEumFilters(){

        if(txtDescription.getText().equals("") && !txtLocation.getText().equals("")){
            filters[0] = txtLocation.getText();
            return EnumSet.of(PARAMETERES.LOCATION);
        }

        else if(!txtDescription.getText().equals("") && txtLocation.getText().equals("")){
            filters[0] = txtDescription.getText();
            return EnumSet.of(PARAMETERES.DESCRIPTION);
        }

        else if(!txtDescription.getText().equals("") && !txtLocation.getText().equals("")){
            filters[0] = txtDescription.getText();
            filters[1] = txtLocation.getText();
            return EnumSet.of(PARAMETERES.LOCATION, PARAMETERES.DESCRIPTION);
        }

        else{
            return null;
        }
    }
}
