package qx4.Creature;

import javafx.scene.image.Image;
import qx4.Global;
import qx4.Position;

import java.util.concurrent.BrokenBarrierException;

public class Grandpa extends Creature {
    public Grandpa()
    {
        setName("老爷爷");
        setPosition(new Position(0,0));
        setImageView(new Image("/picture/0.png"));
        setCamp(true);
        setAlive(true);
        setTotalHP(1400);
        setCurrentHP(1400);
        setAttack(100);
    }
}
