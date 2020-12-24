package Controller;

import Model.Job;

import javax.swing.*;
import java.util.EnumSet;
import java.util.HashSet;

public class GuiController extends ApiController implements Filters{


    public GuiController(){
        super();
    }

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