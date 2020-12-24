package Exception;

import javax.swing.*;

public class GuiOptionPaneException extends Exception{

    private String msg = null;

    /**
     * constructor
     * @param msg message
     */
    public GuiOptionPaneException(String msg){
        super(msg);
        this.msg = msg;
    }

    /**
     * this method gets the messsage
     * @return message
     */
    public String getMsg() {
        return msg;
    }

    /**
     * this method sets the message
     * @param msg message
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
