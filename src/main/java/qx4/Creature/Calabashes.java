package qx4.Creature;

import qx4.Creature.Calabash;
import qx4.Creature.Creatures;
import qx4.Creature.Grandpa;

public class Calabashes extends Creatures {
    public Calabashes()
    {
        creatures.add(new Grandpa());
        for(int i=1;i<=7;i++)
        {
            creatures.add(new Calabash(i));
        }
        camp=true;
    }
}
