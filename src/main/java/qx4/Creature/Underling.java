package qx4.Creature;

import javafx.scene.image.Image;
import qx4.Global;
import qx4.Position;

public class Underling extends Creature {
    public Underling()
    {
        setName("小喽啰"+Global.getNum());
        setPosition(new Position(0,0));
        setImageView(new Image("/picture/10.png"));
        setCamp(false);
        setAlive(true);
        setTotalHP(1200);
        setCurrentHP(1200);
        setAttack(120);
        Global.setNum(Global.getNum()+1);
    }
}
