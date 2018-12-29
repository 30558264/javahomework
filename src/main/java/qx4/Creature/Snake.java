package qx4.Creature;

import javafx.scene.image.Image;
import qx4.Position;

public class Snake extends Creature {
    public Snake()
    {
        setName("蛇精");
        setPosition(new Position(0,0));
        setImageView(new Image("/picture/9.png"));
        setCamp(false);
        setAlive(true);
        setTotalHP(1600);
        setCurrentHP(1600);
        setAttack(150);
    }
}
