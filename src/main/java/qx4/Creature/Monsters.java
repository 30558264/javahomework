package qx4.Creature;

import qx4.Creature.Creatures;
import qx4.Creature.Scorpion;
import qx4.Creature.Snake;
import qx4.Creature.Underling;

public class Monsters extends Creatures {
    public Monsters()
    {
        creatures.add(new Snake());
        creatures.add(new Scorpion());
        for(int i=0;i<6;i++)
        {
            creatures.add(new Underling());
        }
        camp=false;
    }
}
