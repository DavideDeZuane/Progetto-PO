package GUI;

import Controller.CheckOffer;
import Model.Job;
import Model.JobBoard;
import Model.PickedJobs;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.Iterator;

public abstract class GuiMenagement extends JFrame {


    private JPanel panel;
    private String namePanel;

    public GuiMenagement(JPanel panel, String namePanel){
        this.namePanel = namePanel;
        this.panel = panel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public String getNamePanel() {
        return namePanel;
    }

    public void setNamePanel(String namePanel) {
        this.namePanel = namePanel;
    }

    public abstract void setPanel(int width, int height);

}
