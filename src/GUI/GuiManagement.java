package GUI;

import javax.swing.*;

public abstract class GuiManagement extends JFrame {


    private JPanel panel;
    private String namePanel;

    /**
     * constructor
     * @param panel is a panel
     * @param namePanel is the name of the panel
     */
    public GuiManagement(JPanel panel, String namePanel){
        this.namePanel = namePanel;
        this.panel = panel;
    }

    /**
     * this method gets a JPanel panel
     * @return a panel object of JPanel type
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * this model sets a JPanel panel
     * @param panel object of JPanel type
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * this method gets the name of the panel
     * @return the name of the panel
     */
    public String getNamePanel() {
        return namePanel;
    }

    /**
     * this method sets the name of the panel
     * @param namePanel name of the panel
     */
    public void setNamePanel(String namePanel) {
        this.namePanel = namePanel;
    }

    /**
     * @param width weight of the panel
     * @param height height of the panel
     */
    public abstract void setPanel(int width, int height);

}
