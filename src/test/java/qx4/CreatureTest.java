package qx4;

import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Test;
import qx4.Creature.Calabash;

public class CreatureTest {
    @Test
    public void setAliveTest()
    {
        new JFXPanel();
        Calabash calabash=new Calabash(1);
        calabash.setPosition(new Position(2,2));
        calabash.setImageView(new Position(2,2));
        calabash.moveTo(new Position(3,4));
        Assert.assertEquals(3,calabash.getPosition().getX());
        Assert.assertEquals(4,calabash.getPosition().getY());
        Assert.assertEquals(3,(int)(calabash.getImageView().getLayoutX()/Global.getScale()));
        Assert.assertEquals(4,(int)(calabash.getImageView().getLayoutY()/Global.getScale()));
    }
}
