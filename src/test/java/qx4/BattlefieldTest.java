package qx4;

import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Test;

public class BattlefieldTest {
    @Test
    public void setTest()
    {
        new JFXPanel();
        Global.init();
        Global.getBattlefield().set();
        for(int i=0;i<Global.getCalabashes().getCreatures().size();i++)
        {
            Assert.assertEquals(Global.getCalabashes().getCreatures().get(i).getPosition().getX(),Global.getBattlefield().getPosition(Global.getCalabashes().getCreatures().get(i)).getX());
            Assert.assertEquals(Global.getCalabashes().getCreatures().get(i).getPosition().getY(),Global.getBattlefield().getPosition(Global.getCalabashes().getCreatures().get(i)).getY());
        }
        for(int i=0;i<Global.getMonsters().getCreatures().size();i++)
        {
            Assert.assertEquals(Global.getMonsters().getCreatures().get(i).getPosition().getX(),Global.getBattlefield().getPosition(Global.getMonsters().getCreatures().get(i)).getX());
            Assert.assertEquals(Global.getMonsters().getCreatures().get(i).getPosition().getY(),Global.getBattlefield().getPosition(Global.getMonsters().getCreatures().get(i)).getY());
        }
    }
    @Test
    public void updateRouteTest()
    {
        new JFXPanel();
        Global.init();
        int n=Global.getCalabashes().getCreatures().get(0).getRoute().size();
        Global.getBattlefield().update();
        for(int i=0;i<Global.getCalabashes().getCreatures().size();i++)
        {
            Assert.assertEquals(n+1,Global.getCalabashes().getCreatures().get(i).getRoute().size());
            Assert.assertEquals(n+1,Global.getCalabashes().getCreatures().get(i).getState().size());
            Assert.assertEquals(n+1,Global.getCalabashes().getCreatures().get(i).getHP().size());
        }
        for(int i=0;i<Global.getMonsters().getCreatures().size();i++)
        {
            Assert.assertEquals(n+1,Global.getMonsters().getCreatures().get(i).getRoute().size());
            Assert.assertEquals(n+1,Global.getMonsters().getCreatures().get(i).getState().size());
            Assert.assertEquals(n+1,Global.getMonsters().getCreatures().get(i).getHP().size());
        }
    }
}
