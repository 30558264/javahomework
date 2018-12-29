package qx4.Creature;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import qx4.Global;
import qx4.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public abstract class Creature implements Runnable {
    protected String name;
    protected Position position;
    protected ImageView imageView=new ImageView();
    protected boolean camp;
    protected boolean alive;
    protected int totalHP;
    protected int currentHP;
    protected int attack;
    protected ImageView red=new ImageView();
    protected ImageView green=new ImageView();
    protected CyclicBarrier cyclicBarrier;
    protected List<Position> route=new ArrayList<Position>();
    protected List<Boolean> state=new ArrayList<Boolean>();
    protected List<Integer> HP=new ArrayList<Integer>();

    public void setName(String name)
    {
        this.name=name;
    }
    public void setPosition(Position position)
    {
        this.position=position;
    }
    public void setImageView(Image image)
    {
        imageView.setImage(image);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        imageView.setFitWidth(Global.getScale());
        imageView.setFitHeight(Global.getScale()-5);
        imageView.setVisible(true);
    }
    public void setImageView(Position position)
    {
        imageView.setLayoutX(position.getX()*Global.getScale());
        imageView.setLayoutY(position.getY()*Global.getScale()+5);
        setRed(position);
        setGreen(position);
    }
    public void setCamp(boolean camp)
    {
        this.camp=camp;
    }
    public void setAlive(boolean alive)
    {
        this.alive=alive;
        if(!alive)
        {
            switch (this.name)
            {
                case "老爷爷":Global.runLater(()->imageView.setImage(new Image("/picture/dead0.png")));break;
                case "老大":Global.runLater(()->imageView.setImage(new Image("/picture/dead1.png")));break;
                case "老二":Global.runLater(()->imageView.setImage(new Image("/picture/dead2.png")));break;
                case "老三":Global.runLater(()->imageView.setImage(new Image("/picture/dead3.png")));break;
                case "老四":Global.runLater(()->imageView.setImage(new Image("/picture/dead4.png")));break;
                case "老五":Global.runLater(()->imageView.setImage(new Image("/picture/dead5.png")));break;
                case "老六":Global.runLater(()->imageView.setImage(new Image("/picture/dead6.png")));break;
                case "老七":Global.runLater(()->imageView.setImage(new Image("/picture/dead7.png")));break;
                case "蝎子精":Global.runLater(()->imageView.setImage(new Image("/picture/dead8.png")));break;
                case "蛇精":Global.runLater(()->imageView.setImage(new Image("/picture/dead8.png")));break;
                case "小喽啰1":Global.runLater(()->imageView.setImage(new Image("/picture/dead8.png")));break;
                case "小喽啰2":Global.runLater(()->imageView.setImage(new Image("/picture/dead8.png")));break;
                case "小喽啰3":Global.runLater(()->imageView.setImage(new Image("/picture/dead8.png")));break;
                case "小喽啰4":Global.runLater(()->imageView.setImage(new Image("/picture/dead8.png")));break;
                case "小喽啰5":Global.runLater(()->imageView.setImage(new Image("/picture/dead8.png")));break;
                case "小喽啰6":Global.runLater(()->imageView.setImage(new Image("/picture/dead8.png")));break;
            }
            Global.runLater(()->red.setVisible(false));
            Global.runLater(()->green.setVisible(false));
        }
    }
    public void setTotalHP(int totalHP){this.totalHP=totalHP;}
    public void setCurrentHP(int currentHP){this.currentHP=currentHP;}
    public void setAttack(int attack){this.attack=attack;}
    public void setRed(Position position)
    {
        red.setImage(new Image("/picture/red.png"));
        red.setLayoutX(position.getX()*Global.getScale());
        red.setLayoutY(position.getY()*Global.getScale());
        red.setFitWidth(Global.getScale());
        red.setFitHeight(5);
        red.setVisible(true);
    }
    public void setGreen(Position position)
    {
        green.setImage(new Image("/picture/green.png"));
        green.setLayoutX(position.getX()*Global.getScale());
        green.setLayoutY(position.getY()*Global.getScale());
        green.setFitWidth(Global.getScale());
        green.setFitHeight(5);
        green.setVisible(true);
    }
    public void updateGreen()
    {
        green.setFitWidth((double)Global.getScale()*(double)currentHP/(double)totalHP);
    }
    public void setCyclicBarrier(CyclicBarrier cyclicBarrier)
    {
        this.cyclicBarrier=cyclicBarrier;
    }
    public void setRoute(Position position)
    {
        this.route.add(position);
    }
    public void setState(boolean alive)
    {
        this.state.add(alive);
    }
    public void setHP(int HP){this.HP.add(HP);}

    public String getName()
    {
        return name;
    }
    public Position getPosition()
    {
        return position;
    }
    public ImageView getImageView()
    {
        return imageView;
    }
    public boolean isCamp()
    {
        return camp;
    }
    public boolean isAlive()
    {
        return alive;
    }
    public int getCurrentHP(){return currentHP;}
    public int getAttack(){return attack;}
    public ImageView getRed(){return red;}
    public ImageView getGreen(){return green;}
    public List<Position> getRoute()
    {
        return route;
    }
    public List<Boolean> getState()
    {
        return state;
    }
    public List<Integer> getHP(){return HP;}

    public synchronized void moveTo(Position position)
    {
        double dx=position.getX()-this.position.getX();
        double dy=position.getY()-this.position.getY();
        double x=imageView.getLayoutX();
        double y=imageView.getLayoutY();
        for(int i=0;i<=Global.getScale();i++)
        {
            final int temp=i;
            if(dx!=0)
            {
                Global.runLater(()->{imageView.setLayoutX(x+temp*dx);red.setLayoutX(x+temp*dx);green.setLayoutX(x+temp*dx);});
            }
            if(dy!=0)
            {
                Global.runLater(()->{imageView.setLayoutY(y+temp*dy);red.setLayoutY(y-5+temp*dy);green.setLayoutY(y-5+temp*dy);});
            }
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        setPosition(position);
    }

    @Override
    public void run()
    {
        while(!Global.isEnd())
        {
            if(this.isAlive())
            {
                Position position=Global.getBattlefield().getPosition(this);
                moveTo(position);
            }
            try
            {
                cyclicBarrier.await();
            }
            catch (InterruptedException | BrokenBarrierException e)
            {
                e.printStackTrace();
            }
        }

    }
}