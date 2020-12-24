package Controller;

import javax.swing.*;
import java.util.EnumSet;

public class GuiController extends ApiController implements Filters{

    /**
     * constructor
     */
    public GuiController(){
        super();
    }

    /**
     * this method allows the user to set filters
     * @param txtLocation1 location of the Job the user wants to search for
     * @param txtDescription description of the Job the user wants to search for
     * @param banner allows the user to search for full time jobs or not
     * @return an array of String containing all the set filters
     */
    @Override
    public String[] setFilters(JTextField txtLocation1, JTextField txtDescription, boolean banner) {

        flags = EnumSet.noneOf(Parameters.class);
        String filters[] = new String[100];



        if(banner){
            flags.add(Parameters.TYPE);
        }

        if(txtDescription.getText().equals("") && !txtLocation1.getText().equals("")){
            filters[0] = txtLocation1.getText();
            flags.add(Parameters.LOCATION);
        }

        else if(!txtDescription.getText().equals("") && txtLocation1.getText().equals("")){
            filters[0] = txtDescription.getText();
            flags.add(Parameters.DESCRIPTION);
        }

        else if(!txtDescription.getText().equals("") && !txtLocation1.getText().equals("")) {
            filters[0] = txtDescription.getText();
            filters[1] = txtLocation1.getText();
            flags.add(Parameters.LOCATION);
            flags.add(Parameters.DESCRIPTION);
        }

        return filters;
    }

}