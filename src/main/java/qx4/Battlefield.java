package qx4;

import javafx.scene.layout.Pane;
import qx4.Creature.Calabashes;
import qx4.Creature.Creature;
import qx4.Creature.Monsters;

public class Battlefield {
    private Creature[][] creature;
    private Calabashes calabashes;
    private Monsters monsters;
    private Position[] calabashesDirection;
    private Position[] monstersDirection;
    private boolean[] moved;
    private boolean[] visited;
    public Battlefield()
    {
        creature=new Creature[Global.getN()][Global.getM()];
        calabashes=Global.getCalabashes();
        monsters=Global.getMonsters();
        Pane pane=Main.getPane();
        for(int i=0;i<calabashes.getCreatures().size();i++)
        {
            pane.getChildren().add(calabashes.getCreatures().get(i).getImageView());
            pane.getChildren().add(calabashes.getCreatures().get(i).getRed());
            pane.getChildren().add(calabashes.getCreatures().get(i).getGreen());
        }
        for(int i=0;i<monsters.getCreatures().size();i++)
        {
            pane.getChildren().add(monsters.getCreatures().get(i).getImageView());
            pane.getChildren().add(monsters.getCreatures().get(i).getRed());
            pane.getChildren().add(monsters.getCreatures().get(i).getGreen());
        }
        calabashesDirection=new Position[calabashes.getCreatures().size()];
        monstersDirection=new Position[monsters.getCreatures().size()];
        moved=new boolean[calabashes.getCreatures().size()];
        visited=new boolean[monsters.getCreatures().size()];
        Global.getFormation().get(Global.getCalabashesFormation()).formation(calabashes);
        Global.getFormation().get(Global.getMonstersFormation()).formation(monsters);
        update();
    }
    public Position getPosition(Creature creature)
    {
        Position position=new Position();
        for(int i=0;i<calabashes.getCreatures().size();i++)
        {
            if(creature.getName()==calabashes.getCreatures().get(i).getName())
            {
                position=calabashesDirection[i];
                break;
            }
        }
        for(int i=0;i<monsters.getCreatures().size();i++)
        {
            if(creature.getName()==monsters.getCreatures().get(i).getName())
            {
                position=monstersDirection[i];
                break;
            }
        }
        return position;
    }
    public void clear()
    {
        for(int i=0;i<Global.getN();i++)
        {
            for(int j=0;j<Global.getM();j++)
            {
                creature[i][j]=null;
            }
        }
    }
    public void set()
    {
        for(int i=0;i<calabashes.getCreatures().size();i++)
        {
            if(calabashes.getCreatures().get(i).isAlive())
            {
                creature[calabashes.getCreatures().get(i).getPosition().getX()][calabashes.getCreatures().get(i).getPosition().getY()]=calabashes.getCreatures().get(i);
            }
            calabashesDirection[i]=calabashes.getCreatures().get(i).getPosition();
            moved[i]=false;
        }
        for(int i=0;i<monsters.getCreatures().size();i++)
        {
            if(monsters.getCreatures().get(i).isAlive())
            {
                creature[monsters.getCreatures().get(i).getPosition().getX()][monsters.getCreatures().get(i).getPosition().getY()]=monsters.getCreatures().get(i);
            }
            monstersDirection[i]=monsters.getCreatures().get(i).getPosition();
            visited[i]=false;
        }
    }
    public void update()
    {
        clear();
        set();
        if(Global.isReplaying())
        {
            for(int i=0;i<calabashes.getCreatures().size();i++)
            {
                calabashes.getCreatures().get(i).setCurrentHP(calabashes.getCreatures().get(i).getHP().get(Global.getNum()));
                calabashes.getCreatures().get(i).setAlive(calabashes.getCreatures().get(i).getState().get(Global.getNum()));
                calabashesDirection[i]=calabashes.getCreatures().get(i).getRoute().get(Global.getNum());
                calabashes.getCreatures().get(i).updateGreen();
            }
            for(int i=0;i<monsters.getCreatures().size();i++)
            {
                monsters.getCreatures().get(i).setCurrentHP(monsters.getCreatures().get(i).getHP().get(Global.getNum()));
                monsters.getCreatures().get(i).setAlive(monsters.getCreatures().get(i).getState().get(Global.getNum()));
                monstersDirection[i]=monsters.getCreatures().get(i).getRoute().get(Global.getNum());
                monsters.getCreatures().get(i).updateGreen();
            }
        }
        else
        {
            updateCalabashesDirection();
            updateMonstersDirection();
            updateRoute();
        }
        Global.setNum(Global.getNum()+1);
    }
    public void updateCalabashesDirection()
    {
        for(int i=0;i<calabashes.getCreatures().size();i++)
        {
            if(calabashes.getCreatures().get(i).isAlive())
            {
                int x=calabashes.getCreatures().get(i).getPosition().getX();
                int y=calabashes.getCreatures().get(i).getPosition().getY();
                if(x-1>=0&&creature[x-1][y]!=null&&!creature[x-1][y].isCamp())
                {
                    for(int j=0;j<monsters.getCreatures().size();j++)
                    {
                        if(monsters.getCreatures().get(j).getName()==creature[x-1][y].getName()&&!visited[j])
                        {
                            int calabashesHP=calabashes.getCreatures().get(i).getCurrentHP()-monsters.getCreatures().get(j).getAttack();
                            int monstersHP=monsters.getCreatures().get(j).getCurrentHP()-calabashes.getCreatures().get(i).getAttack();
                            if(calabashesHP>0)
                            {
                                calabashes.getCreatures().get(i).setCurrentHP(calabashesHP);
                                if(x+1<=Global.getN()-1&&creature[x+1][y]==null)
                                {
                                    calabashesDirection[i]=new Position(x+1,y);
                                    creature[x+1][y]=creature[x][y];
                                    creature[x][y]=null;
                                }
                                moved[i]=true;
                            }
                            else
                            {
                                calabashes.getCreatures().get(i).setCurrentHP(0);
                                calabashes.getCreatures().get(i).setAlive(false);
                                creature[x][y]=null;
                            }
                            calabashes.getCreatures().get(i).updateGreen();
                            if(monstersHP>0)
                            {
                                monsters.getCreatures().get(j).setCurrentHP(monstersHP);
                                if(x-2>=0&&creature[x-2][y]==null)
                                {
                                    monstersDirection[j]=new Position(x-2,y);
                                    creature[x-2][y]=creature[x-1][y];
                                    creature[x-1][y]=null;
                                }
                                visited[j]=true;
                            }
                            else
                            {
                                monsters.getCreatures().get(j).setCurrentHP(0);
                                monsters.getCreatures().get(j).setAlive(false);
                                creature[x-1][y]=null;
                            }
                            monsters.getCreatures().get(j).updateGreen();
                            break;
                        }
                    }
                }
                else if(x+1<=Global.getN()-1&&creature[x+1][y]!=null&&!creature[x+1][y].isCamp())
                {
                    for(int j=0;j<monsters.getCreatures().size();j++)
                    {
                        if(monsters.getCreatures().get(j).getName()==creature[x+1][y].getName()&&!visited[j])
                        {
                            int calabashesHP=calabashes.getCreatures().get(i).getCurrentHP()-monsters.getCreatures().get(j).getAttack();
                            int monstersHP=monsters.getCreatures().get(j).getCurrentHP()-calabashes.getCreatures().get(i).getAttack();
                            if(calabashesHP>0)
                            {
                                calabashes.getCreatures().get(i).setCurrentHP(calabashesHP);
                                if(x-1>=0&&creature[x-1][y]==null)
                                {
                                    calabashesDirection[i]=new Position(x-1,y);
                                    creature[x-1][y]=creature[x][y];
                                    creature[x][y]=null;
                                }
                                moved[i]=true;
                            }
                            else
                            {
                                calabashes.getCreatures().get(i).setCurrentHP(0);
                                calabashes.getCreatures().get(i).setAlive(false);
                                creature[x][y]=null;
                            }
                            calabashes.getCreatures().get(i).updateGreen();
                            if(monstersHP>0)
                            {
                                monsters.getCreatures().get(j).setCurrentHP(monstersHP);
                                if(x+2<=Global.getN()-1&&creature[x+2][y]==null)
                                {
                                    monstersDirection[j]=new Position(x+2,y);
                                    creature[x+2][y]=creature[x+1][y];
                                    creature[x+1][y]=null;
                                }
                                visited[j]=true;
                            }
                            else
                            {
                                monsters.getCreatures().get(j).setCurrentHP(0);
                                monsters.getCreatures().get(j).setAlive(false);
                                creature[x+1][y]=null;
                            }
                            monsters.getCreatures().get(j).updateGreen();
                            break;
                        }
                    }
                }
                else if(y-1>=0&&creature[x][y-1]!=null&&!creature[x][y-1].isCamp())
                {
                    for(int j=0;j<monsters.getCreatures().size();j++)
                    {
                        if(monsters.getCreatures().get(j).getName()==creature[x][y-1].getName()&&!visited[j])
                        {
                            int calabashesHP=calabashes.getCreatures().get(i).getCurrentHP()-monsters.getCreatures().get(j).getAttack();
                            int monstersHP=monsters.getCreatures().get(j).getCurrentHP()-calabashes.getCreatures().get(i).getAttack();
                            if(calabashesHP>0)
                            {
                                calabashes.getCreatures().get(i).setCurrentHP(calabashesHP);
                                if(y+1<=Global.getM()-1&&creature[x][y+1]==null)
                                {
                                    calabashesDirection[i]=new Position(x,y+1);
                                    creature[x][y+1]=creature[x][y];
                                    creature[x][y]=null;
                                }
                                moved[i]=true;
                            }
                            else
                            {
                                calabashes.getCreatures().get(i).setCurrentHP(0);
                                calabashes.getCreatures().get(i).setAlive(false);
                                creature[x][y]=null;
                            }
                            calabashes.getCreatures().get(i).updateGreen();
                            if(monstersHP>0)
                            {
                                monsters.getCreatures().get(j).setCurrentHP(monstersHP);
                                if(y-2>=0&&creature[x][y-2]==null)
                                {
                                    monstersDirection[j]=new Position(x,y-2);
                                    creature[x][y-2]=creature[x][y-1];
                                    creature[x][y-1]=null;
                                }
                                visited[j]=true;
                            }
                            else
                            {
                                monsters.getCreatures().get(j).setCurrentHP(0);
                                monsters.getCreatures().get(j).setAlive(false);
                                creature[x][y-1]=null;
                            }
                            monsters.getCreatures().get(j).updateGreen();
                            break;
                        }
                    }
                }
                else if(y+1<=Global.getM()-1&&creature[x][y+1]!=null&&!creature[x][y+1].isCamp())
                {
                    for(int j=0;j<monsters.getCreatures().size();j++)
                    {
                        if(monsters.getCreatures().get(j).getName()==creature[x][y+1].getName()&&!visited[j])
                        {
                            int calabashesHP=calabashes.getCreatures().get(i).getCurrentHP()-monsters.getCreatures().get(j).getAttack();
                            int monstersHP=monsters.getCreatures().get(j).getCurrentHP()-calabashes.getCreatures().get(i).getAttack();
                            if(calabashesHP>0)
                            {
                                calabashes.getCreatures().get(i).setCurrentHP(calabashesHP);
                                if(y-1>=0&&creature[x][y-1]==null)
                                {
                                    calabashesDirection[i]=new Position(x,y-1);
                                    creature[x][y-1]=creature[x][y];
                                    creature[x][y]=null;
                                }
                                moved[i]=true;
                            }
                            else
                            {
                                calabashes.getCreatures().get(i).setCurrentHP(0);
                                calabashes.getCreatures().get(i).setAlive(false);
                                creature[x][y]=null;
                            }
                            calabashes.getCreatures().get(i).updateGreen();
                            if(monstersHP>0)
                            {
                                monsters.getCreatures().get(j).setCurrentHP(monstersHP);
                                if(y+2<=Global.getM()-1&&creature[x][y+2]==null)
                                {
                                    monstersDirection[j]=new Position(x,y+2);
                                    creature[x][y+2]=creature[x][y+1];
                                    creature[x][y+1]=null;
                                }
                                visited[j]=true;
                            }
                            else
                            {
                                monsters.getCreatures().get(j).setCurrentHP(0);
                                monsters.getCreatures().get(j).setAlive(false);
                                creature[x][y+1]=null;
                            }
                            monsters.getCreatures().get(j).updateGreen();
                            break;
                        }
                    }
                }
                else
                {
                    boolean ismoved=false;
                    while(!ismoved)
                    {
                        int random=(int)(Math.random()*100)%4;
                        if(random==0&&x-1>=0&&creature[x-1][y]==null)
                        {
                            creature[x-1][y]=creature[x][y];
                            creature[x][y]=null;
                            calabashesDirection[i]=new Position(x-1,y);
                            moved[i]=true;
                            ismoved=true;
                        }
                        else if(random==1&&x+1<=Global.getN()-1&&creature[x+1][y]==null)
                        {
                            creature[x+1][y]=creature[x][y];
                            creature[x][y]=null;
                            calabashesDirection[i]=new Position(x+1,y);
                            moved[i]=true;
                            ismoved=true;
                        }
                        else if(random==2&&y-1>=0&&creature[x][y-1]==null)
                        {
                            creature[x][y-1]=creature[x][y];
                            creature[x][y]=null;
                            calabashesDirection[i]=new Position(x,y-1);
                            moved[i]=true;
                            ismoved=true;
                        }
                        else if(random==3&&y+1<=Global.getM()-1&&creature[x][y+1]==null)
                        {
                            creature[x][y+1]=creature[x][y];
                            creature[x][y]=null;
                            calabashesDirection[i]=new Position(x,y+1);
                            moved[i]=true;
                            ismoved=true;
                        }
                    }
                }
            }
        }
    }
    public void updateMonstersDirection()
    {
        for(int i=0;i<monsters.getCreatures().size();i++)
        {
            if(monsters.getCreatures().get(i).isAlive()&&!visited[i])
            {
                int x=monsters.getCreatures().get(i).getPosition().getX();
                int y=monsters.getCreatures().get(i).getPosition().getY();
                boolean ismoved=false;
                while(!ismoved)
                {
                    int random=(int)(Math.random()*100)%4;
                    if(random==0&&x-1>=0&&creature[x-1][y]==null)
                    {
                        creature[x-1][y]=creature[x][y];
                        creature[x][y]=null;
                        monstersDirection[i]=new Position(x-1,y);
                        ismoved=true;
                    }
                    else if(random==1&&x+1<=Global.getN()-1&&creature[x+1][y]==null)
                    {
                        creature[x+1][y]=creature[x][y];
                        creature[x][y]=null;
                        monstersDirection[i]=new Position(x+1,y);
                        ismoved=true;
                    }
                    else if(random==2&&y-1>=0&&creature[x][y-1]==null)
                    {
                        creature[x][y-1]=creature[x][y];
                        creature[x][y]=null;
                        monstersDirection[i]=new Position(x,y-1);
                        ismoved=true;
                    }
                    else if(random==3&&y+1<=Global.getM()-1&&creature[x][y+1]==null)
                    {
                        creature[x][y+1]=creature[x][y];
                        creature[x][y]=null;
                        monstersDirection[i]=new Position(x,y+1);
                        ismoved=true;
                    }
                }
            }
        }
    }
    public void updateRoute()
    {
        for(int i=0;i<calabashes.getCreatures().size();i++)
        {
            calabashes.getCreatures().get(i).setRoute(calabashesDirection[i]);
            calabashes.getCreatures().get(i).setState(calabashes.getCreatures().get(i).isAlive());
            calabashes.getCreatures().get(i).setHP(calabashes.getCreatures().get(i).getCurrentHP());
        }
        for(int i=0;i<monsters.getCreatures().size();i++)
        {
            monsters.getCreatures().get(i).setRoute(monstersDirection[i]);
            monsters.getCreatures().get(i).setState(monsters.getCreatures().get(i).isAlive());
            monsters.getCreatures().get(i).setHP(monsters.getCreatures().get(i).getCurrentHP());
        }
    }
}
