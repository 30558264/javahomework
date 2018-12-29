package qx4.Creature;

import javafx.scene.image.Image;
import qx4.Position;

public class Calabash extends Creature {
    public Calabash(int i)
    {
        switch(i)
        {
            case 1:setName("老大");break;
            case 2:setName("老二");break;
            case 3:setName("老三");break;
            case 4:setName("老四");break;
            case 5:setName("老五");break;
            case 6:setName("老六");break;
            case 7:setName("老七");break;
            default:System.out.print("没有此葫芦娃！");break;
        }
        setPosition(new Position(0,0));
        setImageView(new Image("/picture/"+i+".png"));
        setCamp(true);
        setAlive(true);
        setTotalHP(1200);
        setCurrentHP(1200);
        setAttack(200);
    }
}
