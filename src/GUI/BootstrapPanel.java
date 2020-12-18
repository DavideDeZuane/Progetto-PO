package GUI;

import Controller.ApiController;
import Controller.GuiApiController;
import Controller.Parameters;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

    URL url = null;

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

                apiController = new GuiApiController();

                try {
                    url = null;
                    url = ApiController.query(apiController.setFilters(txtLocation,txtDescription,fullTime.isSelected()));
                    new JobsFoundPanel(url, txtDescription.getText());

                } catch (MalformedURLException exception) {

                    JOptionPane.showMessageDialog(rootPanel,"Invalid filters");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
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