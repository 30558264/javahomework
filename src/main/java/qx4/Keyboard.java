package qx4;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent e)
    {
        if(e.getCode()== KeyCode.SPACE)
        {
            if(!Global.isStart())
            {
                if(!Global.isReplaying())
                {
                    Global.clear();
                    Global.init();
                }
                Global.start();
            }
        }
        else if(e.getCode()==KeyCode.R)
        {
            if(!Global.isStart())
            {
                Global.clear();
                Global.init();
            }
        }
        else if(e.getCode()==KeyCode.S)
        {
            if(!Global.isStart())
                Global.save();
        }
        else if(e.getCode()==KeyCode.L)
        {
            if(!Global.isStart())
            {
                Global.clear();
                Global.read();
            }
        }
    }
}
