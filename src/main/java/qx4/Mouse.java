package qx4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import qx4.Creature.Creatures;

public class Mouse implements EventHandler<ActionEvent> {
    private Creatures creatures=new Creatures();
    private int n;
    public Mouse(boolean camp,int n)
    {
        this.creatures.setCamp(camp);
        this.n=n;
    }
    @Override
    public void handle(ActionEvent e)
    {
        if(creatures.isCamp())
        {
            Global.setCalabashesFormation(n%8);
        }
        else
        {
            Global.setMonstersFormation(n%8);
        }
    }
}
