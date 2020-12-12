package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BootstrapPanel extends JFrame{

    private JPanel rootPanel;
    private JTextField txtType;
    private JTextField txtLocation;
    private JTextField txtCompany;
    private JTextField txtDescription;
    private JButton btnSearch;
    private JButton btnShowJobsSaved;

    public BootstrapPanel() {


        add(rootPanel);
        setTitle("IT Found Jobs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setVisible(true);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
}
