package qx4.Formation;

import qx4.Creature.Creatures;
import qx4.Global;
import qx4.Position;

public class FormationYL implements Formation {
    @Override
    public void formation(Creatures creatures)
    {
        if(creatures.isCamp())
        {
            creatures.getCreatures().get(0).setPosition(new Position(3,(Global.getM()-1)/2));
            creatures.getCreatures().get(1).setPosition(new Position(4,(Global.getM()-1)/2-2));
            creatures.getCreatures().get(2).setPosition(new Position(4,(Global.getM()-1)/2-1));
            creatures.getCreatures().get(3).setPosition(new Position(4,(Global.getM()-1)/2));
            creatures.getCreatures().get(4).setPosition(new Position(4,(Global.getM()-1)/2+1));
            creatures.getCreatures().get(5).setPosition(new Position(4,(Global.getM()-1)/2+2));
            creatures.getCreatures().get(6).setPosition(new Position(5,(Global.getM()-1)/2-1));
            creatures.getCreatures().get(7).setPosition(new Position(6,(Global.getM()-1)/2));
            creatures.getCreatures().get(0).setImageView(new Position(3,(Global.getM()-1)/2));
            creatures.getCreatures().get(1).setImageView(new Position(4,(Global.getM()-1)/2-2));
            creatures.getCreatures().get(2).setImageView(new Position(4,(Global.getM()-1)/2-1));
            creatures.getCreatures().get(3).setImageView(new Position(4,(Global.getM()-1)/2));
            creatures.getCreatures().get(4).setImageView(new Position(4,(Global.getM()-1)/2+1));
            creatures.getCreatures().get(5).setImageView(new Position(4,(Global.getM()-1)/2+2));
            creatures.getCreatures().get(6).setImageView(new Position(5,(Global.getM()-1)/2-1));
            creatures.getCreatures().get(7).setImageView(new Position(6,(Global.getM()-1)/2));
        }
        else
        {
            creatures.getCreatures().get(0).setPosition(new Position(Global.getN()-4,(Global.getM()-1)/2));
            creatures.getCreatures().get(1).setPosition(new Position(Global.getN()-5,(Global.getM()-1)/2-2));
            creatures.getCreatures().get(2).setPosition(new Position(Global.getN()-5,(Global.getM()-1)/2-1));
            creatures.getCreatures().get(3).setPosition(new Position(Global.getN()-5,(Global.getM()-1)/2));
            creatures.getCreatures().get(4).setPosition(new Position(Global.getN()-5,(Global.getM()-1)/2+1));
            creatures.getCreatures().get(5).setPosition(new Position(Global.getN()-5,(Global.getM()-1)/2+2));
            creatures.getCreatures().get(6).setPosition(new Position(Global.getN()-6,(Global.getM()-1)/2-1));
            creatures.getCreatures().get(7).setPosition(new Position(Global.getN()-7,(Global.getM()-1)/2));
            creatures.getCreatures().get(0).setImageView(new Position(Global.getN()-4,(Global.getM()-1)/2));
            creatures.getCreatures().get(1).setImageView(new Position(Global.getN()-5,(Global.getM()-1)/2-2));
            creatures.getCreatures().get(2).setImageView(new Position(Global.getN()-5,(Global.getM()-1)/2-1));
            creatures.getCreatures().get(3).setImageView(new Position(Global.getN()-5,(Global.getM()-1)/2));
            creatures.getCreatures().get(4).setImageView(new Position(Global.getN()-5,(Global.getM()-1)/2+1));
            creatures.getCreatures().get(5).setImageView(new Position(Global.getN()-5,(Global.getM()-1)/2+2));
            creatures.getCreatures().get(6).setImageView(new Position(Global.getN()-6,(Global.getM()-1)/2-1));
            creatures.getCreatures().get(7).setImageView(new Position(Global.getN()-7,(Global.getM()-1)/2));
        }
    }
}
