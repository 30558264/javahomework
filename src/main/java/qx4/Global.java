package qx4;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import qx4.Creature.Calabashes;
import qx4.Creature.Monsters;
import qx4.Formation.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Global {
    private static int N=20;
    private static int M=16;
    private static int scale=40;
    private static int num;
    private static boolean start=false;
    private static boolean end=false;
    private static Calabashes calabashes;
    private static Monsters monsters;
    private static Battlefield battlefield;
    private static CyclicBarrier cyclicBarrier;
    private static int calabashesFormation=3;
    private static int monstersFormation=3;
    private static List<Formation> formation=new ArrayList<Formation>();
    private static Thread[] threads;
    private static int round=0;
    private static boolean replaying=false;

    public static int getN() {
        return N;
    }
    public static int getM() {
        return M;
    }
    public static int getScale() {
        return scale;
    }
    public static void setNum(int n){num=n;}
    public static int getNum(){return  num;}
    public static boolean isStart(){return start;}
    public static boolean isEnd() {
        int num1=0,num2=0;
        for(int i=0;i<calabashes.getCreatures().size();i++)
        {
            if(calabashes.getCreatures().get(i).isAlive())
            {
                num1++;
            }
        }
        for(int i=0;i<monsters.getCreatures().size();i++)
        {
            if(monsters.getCreatures().get(i).isAlive())
            {
                num2++;
            }
        }
        if(num1==0&&num2!=0)
        {
            start=false;
            end=true;
            replaying=false;
            runLater(()->{
                ImageView imageView=new ImageView();
                imageView.setImage(new Image("/picture/defeat.png"));
                imageView.setFitWidth(Global.getN()*Global.getScale());
                imageView.setFitHeight(Global.getM()*Global.getScale());
                imageView.setLayoutX(0);
                imageView.setLayoutY(0);
                Main.getPane().getChildren().add(imageView);
            });
        }
        else if(num1!=0&&num2==0)
        {
            start=false;
            end=true;
            replaying=false;
            runLater(()->{
                ImageView imageView=new ImageView();
                imageView.setImage(new Image("/picture/victory.png"));
                imageView.setFitWidth(Global.getN()*Global.getScale());
                imageView.setFitHeight(Global.getM()*Global.getScale());
                imageView.setLayoutX(0);
                imageView.setLayoutY(0);
                Main.getPane().getChildren().add(imageView);
            });
        }
        else if(num1==0&&num2==0)
        {
            start=false;
            end=true;
            replaying=false;
            runLater(()->{
                ImageView imageView=new ImageView();
                imageView.setImage(new Image("/picture/tie.png"));
                imageView.setFitWidth(Global.getN()*Global.getScale());
                imageView.setFitHeight(Global.getM()*Global.getScale());
                imageView.setLayoutX(0);
                imageView.setLayoutY(0);
                Main.getPane().getChildren().add(imageView);
            });
        }
        else if(num>2000)
        {
            if(num1>num2)
            {
                start=false;
                end=true;
                replaying=false;
                runLater(()->{
                    ImageView imageView=new ImageView();
                    imageView.setImage(new Image("/picture/victory.png"));
                    imageView.setFitWidth(Global.getN()*Global.getScale());
                    imageView.setFitHeight(Global.getM()*Global.getScale());
                    imageView.setLayoutX(0);
                    imageView.setLayoutY(0);
                    Main.getPane().getChildren().add(imageView);
                });
            }
            else if(num1<num2)
            {
                start=false;
                end=true;
                replaying=false;
                runLater(()->{
                    ImageView imageView=new ImageView();
                    imageView.setImage(new Image("/picture/defeat.png"));
                    imageView.setFitWidth(Global.getN()*Global.getScale());
                    imageView.setFitHeight(Global.getM()*Global.getScale());
                    imageView.setLayoutX(0);
                    imageView.setLayoutY(0);
                    Main.getPane().getChildren().add(imageView);
                });
            }
            else
            {
                start=false;
                end=true;
                replaying=false;
                runLater(()->{
                    ImageView imageView=new ImageView();
                    imageView.setImage(new Image("/picture/tie.png"));
                    imageView.setFitWidth(Global.getN()*Global.getScale());
                    imageView.setFitHeight(Global.getM()*Global.getScale());
                    imageView.setLayoutX(0);
                    imageView.setLayoutY(0);
                    Main.getPane().getChildren().add(imageView);
                });
            }
        }
        return end;
    }
    public static Calabashes getCalabashes(){return calabashes;}
    public static Monsters getMonsters(){return monsters;}
    public static Battlefield getBattlefield(){return battlefield;}
    public static int getCalabashesFormation(){return calabashesFormation;}
    public static int getMonstersFormation(){return monstersFormation;}
    public static List<Formation> getFormation(){return formation;}
    public static synchronized void runLater(Runnable runnable)
    {
        Platform.runLater(runnable);
    }
    public static boolean isReplaying(){return replaying;}

    public static void init()
    {
        Global.setNum(1);
        calabashes=new Calabashes();
        monsters=new Monsters();
        Global.setNum(0);
        formation.add(new FormationHY());
        formation.add(new FormationYX());
        formation.add(new FormationHG());
        formation.add(new FormationCS());
        formation.add(new FormationYL());
        formation.add(new FormationFY());
        formation.add(new FormationYY());
        formation.add(new FormationFS());
        battlefield=new Battlefield();
        cyclicBarrier=new CyclicBarrier(calabashes.getCreatures().size() + monsters.getCreatures().size(), new Runnable() {
            @Override
            public void run() {
                Global.getBattlefield().update();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        for(int i=0;i<calabashes.getCreatures().size();i++)
        {
            calabashes.getCreatures().get(i).setCyclicBarrier(cyclicBarrier);
        }
        for(int i=0;i<monsters.getCreatures().size();i++)
        {
            monsters.getCreatures().get(i).setCyclicBarrier(cyclicBarrier);
        }
    }

    public static void setCalabashesFormation(int i)
    {
        calabashesFormation=i;
        formation.get(calabashesFormation).formation(calabashes);
    }

    public static void setMonstersFormation(int i)
    {
        monstersFormation=i;
        formation.get(monstersFormation).formation(monsters);
    }

    public static void clear()
    {
        Pane pane=Main.getPane();
        int n=pane.getChildren().size()-24;
        for(int i=0;i<n;i++)
        {
            pane.getChildren().remove(24);
        }
        end=false;
    }

    public static void start()
    {
        start=true;
        end=false;
        threads=new Thread[calabashes.getCreatures().size()+monsters.getCreatures().size()];
        for(int i=0;i<calabashes.getCreatures().size()+monsters.getCreatures().size();i++)
        {
            if(i<calabashes.getCreatures().size())
            {
                threads[i]=new Thread(calabashes.getCreatures().get(i));
            }
            else
            {
                threads[i]=new Thread((monsters.getCreatures().get(i-calabashes.getCreatures().size())));
            }
        }
        for(Thread thread:threads)
        {
            thread.start();
        }
    }

    public static void save()
    {
        if(end)
        {
            FileChooser fileChooser=new FileChooser();
            File file=fileChooser.showOpenDialog(Main.getStage());
            if(file!=null)
            {
                try
                {
                    OutputStream outputStream=new FileOutputStream(file);
                    PrintWriter printWriter=new PrintWriter(outputStream);
                    printWriter.println(calabashesFormation+" "+monstersFormation);
                    int size=calabashes.getCreatures().get(0).getRoute().size();
                    printWriter.println(size);
                    for(int i=0;i<calabashes.getCreatures().size();i++)
                    {
                        for(int j=0;j<size;j++)
                        {
                            printWriter.println(calabashes.getCreatures().get(i).getRoute().get(j).getX()+" "+calabashes.getCreatures().get(i).getRoute().get(j).getY()+" "+calabashes.getCreatures().get(i).getState().get(j)+" "+calabashes.getCreatures().get(i).getHP().get(j));
                        }
                    }
                    for(int i=0;i<monsters.getCreatures().size();i++)
                    {
                        for(int j=0;j<size;j++)
                        {
                            printWriter.println(monsters.getCreatures().get(i).getRoute().get(j).getX()+" "+monsters.getCreatures().get(i).getRoute().get(j).getY()+" "+monsters.getCreatures().get(i).getState().get(j)+" "+monsters.getCreatures().get(i).getHP().get(j));
                        }
                    }
                    printWriter.close();
                    outputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "保存失败！");
                clear();
                init();
            }
        }
    }
    public static void read()
    {
        Global.setNum(1);
        calabashes=new Calabashes();
        monsters=new Monsters();
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(Main.getStage());
        if(file!=null)
        {
            try
            {
                BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                //阵型
                String line=bufferedReader.readLine();
                String[] strings=line.split(" ");
                calabashesFormation=Integer.parseInt(strings[0]);
                monstersFormation=Integer.parseInt(strings[1]);
                //回合数
                line=bufferedReader.readLine();
                round=Integer.parseInt(line);
                //路径
                for(int i=0;i<calabashes.getCreatures().size();i++)
                {
                    for(int j=0;j<round;j++)
                    {
                        line=bufferedReader.readLine();
                        strings=line.split(" ");
                        int x=Integer.parseInt(strings[0]);
                        int y=Integer.parseInt(strings[1]);
                        boolean state=Boolean.parseBoolean(strings[2]);
                        int HP=Integer.parseInt(strings[3]);
                        calabashes.getCreatures().get(i).setRoute(new Position(x,y));
                        calabashes.getCreatures().get(i).setState(state);
                        calabashes.getCreatures().get(i).setHP(HP);
                    }
                }
                for(int i=0;i<monsters.getCreatures().size();i++)
                {
                    for(int j=0;j<round;j++)
                    {
                        line=bufferedReader.readLine();
                        strings=line.split(" ");
                        int x=Integer.parseInt(strings[0]);
                        int y=Integer.parseInt(strings[1]);
                        boolean state=Boolean.parseBoolean(strings[2]);
                        int HP=Integer.parseInt(strings[3]);
                        monsters.getCreatures().get(i).setRoute(new Position(x,y));
                        monsters.getCreatures().get(i).setState(state);
                        monsters.getCreatures().get(i).setHP(HP);
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            replaying=true;
            Global.setNum(0);
            formation.add(new FormationHY());
            formation.add(new FormationYX());
            formation.add(new FormationHG());
            formation.add(new FormationCS());
            formation.add(new FormationYL());
            formation.add(new FormationFY());
            formation.add(new FormationYY());
            formation.add(new FormationFS());
            battlefield=new Battlefield();
            cyclicBarrier=new CyclicBarrier(calabashes.getCreatures().size() + monsters.getCreatures().size(), new Runnable() {
                @Override
                public void run() {
                    Global.getBattlefield().update();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            for(int i=0;i<calabashes.getCreatures().size();i++)
            {
                calabashes.getCreatures().get(i).setCyclicBarrier(cyclicBarrier);
            }
            for(int i=0;i<monsters.getCreatures().size();i++)
            {
                monsters.getCreatures().get(i).setCyclicBarrier(cyclicBarrier);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "没有选择文件，无法读取！");
            clear();
            init();
        }
    }
}


