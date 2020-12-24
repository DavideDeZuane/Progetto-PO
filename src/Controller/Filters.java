package Controller;

import javax.swing.*;

public interface Filters {

    /**
     * @param txtLocation location of the Job the user wants to search for
     * @param txtDescription description of the Job the user wants to search for
     * @param banner allows the user to search for full time jobs or not
     * @return an String array containing all the set filters
     */
    String[] setFilters(JTextField txtLocation, JTextField txtDescription, boolean banner);
}
