package Exception;

import javax.swing.*;

public class GuiOptionPaneException extends Exception{

    private String msg = null;

    public GuiOptionPaneException(String msg){
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
