package GUI;

import Controller.ApiController;
import Controller.Parameters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.EnumSet;
import java.util.Vector;

import static Controller.Parameters.parametri;

public class BootStrapPanel extends JFrame{

    private JPanel rootPanel;
    private JTextField txtLocation;
    private JTextField txtDescription;
    private JButton btnSearch;
    private JButton btnShowJobsSaved;
    private JCheckBox fullTime;

    public EnumSet<Parameters> flags = EnumSet.noneOf(Parameters.class);

    private boolean fProva = true;

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
                setEumFilters();

                try {
                    url = null;
                    url = ApiController.query(filters, flags);
                    new JobsFoundPanel(url,getTxtDescription());

                } catch (MalformedURLException exception) {

                    JOptionPane.showMessageDialog(rootPanel,"Invalid filters");
                    //exception.printStackTrace();
                }
            }
        });

        btnShowJobsSaved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(fullTime.isSelected());
                new JobsSavedPanel(getTxtDescription());
            }
        });
    }

    public String getTxtDescription(){
        return txtDescription.getText();
    }

    public void setEumFilters(){
        flags = EnumSet.noneOf(Parameters.class);

        if(fullTime.isSelected()){
            flags.add(Parameters.TYPE);
        }

        if(txtDescription.getText().equals("") && !txtLocation.getText().equals("")){
            filters[0] = txtLocation.getText();
            flags.add(Parameters.LOCATION);
        }

        else if(!txtDescription.getText().equals("") && txtLocation.getText().equals("")){
            filters[0] = txtDescription.getText();
            flags.add(Parameters.DESCRIPTION);
        }

        else if(!txtDescription.getText().equals("") && !txtLocation.getText().equals("")) {
            filters[0] = txtDescription.getText();
            filters[1] = txtLocation.getText();
            flags.add(Parameters.LOCATION);
            flags.add(Parameters.DESCRIPTION);
        }
    }
}