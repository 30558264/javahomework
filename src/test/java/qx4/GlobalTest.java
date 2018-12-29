package qx4;

import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Test;
import qx4.Creature.Calabashes;
import qx4.Creature.Monsters;
import qx4.Formation.*;
import qx4.Global;

public class GlobalTest {
    @Test
    public void initTest()
    {
        new JFXPanel();
        Calabashes calabashes=new Calabashes();
        Global.setNum(1);
        Monsters monsters=new Monsters();
        Formation[] formations={new FormationHY(),new FormationYX(),new FormationHG(),new FormationCS(),new FormationYL(),new FormationFY(),new FormationYY(),new FormationFS()};
        formations[Global.getCalabashesFormation()].formation(calabashes);
        formations[Global.getMonstersFormation()].formation(monsters);
        Global.init();
        for(int i=0;i<Global.getCalabashes().getCreatures().size();i++)
        {
            Assert.assertEquals(calabashes.getCreatures().get(i).getName(),Global.getCalabashes().getCreatures().get(i).getName());
            Assert.assertEquals(calabashes.getCreatures().get(i).getPosition().getX(),Global.getCalabashes().getCreatures().get(i).getPosition().getX());
            Assert.assertEquals(calabashes.getCreatures().get(i).getPosition().getY(),Global.getCalabashes().getCreatures().get(i).getPosition().getY());
        }

        for(int i=0;i<Global.getMonsters().getCreatures().size();i++)
        {
            Assert.assertEquals(monsters.getCreatures().get(i).getName(),Global.getMonsters().getCreatures().get(i).getName());
            Assert.assertEquals(monsters.getCreatures().get(i).getPosition().getX(),Global.getMonsters().getCreatures().get(i).getPosition().getX());
            Assert.assertEquals(monsters.getCreatures().get(i).getPosition().getY(),Global.getMonsters().getCreatures().get(i).getPosition().getY());
        }
    }

    @Test
    public void clearTest()
    {
        new JFXPanel();
        Global.init();
        Global.clear();
        Assert.assertEquals(24,Main.getPane().getChildren().size());
    }
}
