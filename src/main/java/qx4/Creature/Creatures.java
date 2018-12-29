package qx4.Creature;

import qx4.Creature.Creature;

import java.util.ArrayList;
import java.util.List;

public class Creatures {
    protected List<Creature> creatures=new ArrayList<Creature>();
    protected boolean camp;
    public List<Creature> getCreatures()
    {
        return creatures;
    }
    public boolean isCamp()
    {
        return camp;
    }
    public void setCamp(boolean camp){this.camp=camp;}
}
