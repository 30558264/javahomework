package qx4.Creature;

import javafx.scene.image.Image;
import qx4.Position;

public class Scorpion extends Creature {
    public Scorpion()
    {
        setName("蝎子精");
        setPosition(new Position(0,0));
        setImageView(new Image("/picture/8.png"));
        setCamp(false);
        setAlive(true);
        setTotalHP(2500);
        setCurrentHP(2500);
        setAttack(300);
    }
}
