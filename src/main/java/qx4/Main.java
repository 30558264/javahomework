package qx4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    private static Pane pane=new Pane();
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage=primaryStage;

        ImageView background=new ImageView();
        background.setImage(new Image("/picture/background.png"));
        background.setLayoutX(0);
        background.setLayoutY(0);
        background.setFitWidth(Global.getN()*Global.getScale());
        background.setFitHeight(Global.getM()*Global.getScale());
        pane.getChildren().add(background);
        //标签
        Label label0=new Label("游戏说明：");
        label0.setLayoutX(Global.getN()*Global.getScale()+10);
        label0.setLayoutY(0);
        label0.setPrefWidth(150);
        label0.setPrefHeight(30);
        label0.setFont(Font.font("游戏说明：", FontWeight.BOLD,15));
        pane.getChildren().add(label0);
        Label label1=new Label("空格键：开始");
        label1.setLayoutX(Global.getN()*Global.getScale()+25);
        label1.setLayoutY(Global.getScale());
        label1.setPrefWidth(150);
        label1.setPrefHeight(30);
        label1.setFont(Font.font("空格键：开始", FontWeight.BOLD,12));
        pane.getChildren().add(label1);
        Label label2=new Label("R键：重新开始/重置");
        label2.setLayoutX(Global.getN()*Global.getScale()+25);
        label2.setLayoutY(2*Global.getScale());
        label2.setPrefWidth(150);
        label2.setPrefHeight(30);
        label2.setFont(Font.font("R键：重新开始/重置", FontWeight.BOLD,12));
        pane.getChildren().add(label2);
        Label label3=new Label("S键：保存当前战斗过程");
        label3.setLayoutX(Global.getN()*Global.getScale()+25);
        label3.setLayoutY(3*Global.getScale());
        label3.setPrefWidth(150);
        label3.setPrefHeight(30);
        label3.setFont(Font.font("S键：保存当前战斗过程", FontWeight.BOLD,12));
        pane.getChildren().add(label3);
        Label label4=new Label("L键：读取文件进行回放");
        label4.setLayoutX(Global.getN()*Global.getScale()+25);
        label4.setLayoutY(4*Global.getScale());
        label4.setPrefWidth(150);
        label4.setPrefHeight(30);
        label4.setFont(Font.font("L键：读取文件进行回放", FontWeight.BOLD,12));
        pane.getChildren().add(label4);
        Label label5=new Label("葫芦娃阵型：");
        label5.setLayoutX(Global.getN()*Global.getScale()+10);
        label5.setLayoutY(5*Global.getScale());
        label5.setPrefWidth(75);
        label5.setPrefHeight(30);
        label5.setFont(Font.font("葫芦娃阵型：", FontWeight.BOLD,12));
        pane.getChildren().add(label5);
        Label label6=new Label("妖精阵型：");
        label6.setLayoutX(Global.getN()*Global.getScale()+110);
        label6.setLayoutY(5*Global.getScale());
        label6.setPrefWidth(175);
        label6.setPrefHeight(30);
        label6.setFont(Font.font("妖精阵型：", FontWeight.BOLD,12));
        pane.getChildren().add(label6);
        //葫芦娃阵型按钮
        Button button1=new Button("鹤翼阵");
        button1.setLayoutX(Global.getN()*Global.getScale()+10);
        button1.setLayoutY(6*Global.getScale());
        button1.setPrefWidth(60);
        button1.setPrefHeight(30);
        button1.setOnAction(new Mouse(true,0));
        button1.setFocusTraversable(false);
        pane.getChildren().add(button1);
        Button button2=new Button("雁行阵");
        button2.setLayoutX(Global.getN()*Global.getScale()+10);
        button2.setLayoutY(7*Global.getScale());
        button2.setPrefWidth(60);
        button2.setPrefHeight(30);
        button2.setOnAction(new Mouse(true,1));
        button2.setFocusTraversable(false);
        pane.getChildren().add(button2);
        Button button3=new Button("衡轨阵");
        button3.setLayoutX(Global.getN()*Global.getScale()+10);
        button3.setLayoutY(8*Global.getScale());
        button3.setPrefWidth(60);
        button3.setPrefHeight(30);
        button3.setOnAction(new Mouse(true,2));
        button3.setFocusTraversable(false);
        pane.getChildren().add(button3);
        Button button4=new Button("长蛇阵");
        button4.setLayoutX(Global.getN()*Global.getScale()+10);
        button4.setLayoutY(9*Global.getScale());
        button4.setPrefWidth(60);
        button4.setPrefHeight(30);
        button4.setOnAction(new Mouse(true,3));
        button4.setFocusTraversable(false);
        pane.getChildren().add(button4);
        Button button5=new Button("鱼鳞阵");
        button5.setLayoutX(Global.getN()*Global.getScale()+10);
        button5.setLayoutY(10*Global.getScale());
        button5.setPrefWidth(60);
        button5.setPrefHeight(30);
        button5.setOnAction(new Mouse(true,4));
        button5.setFocusTraversable(false);
        pane.getChildren().add(button5);
        Button button6=new Button("方円阵");
        button6.setLayoutX(Global.getN()*Global.getScale()+10);
        button6.setLayoutY(11*Global.getScale());
        button6.setPrefWidth(60);
        button6.setPrefHeight(30);
        button6.setOnAction(new Mouse(true,5));
        button6.setFocusTraversable(false);
        pane.getChildren().add(button6);
        Button button7=new Button("偃月阵");
        button7.setLayoutX(Global.getN()*Global.getScale()+10);
        button7.setLayoutY(12*Global.getScale());
        button7.setPrefWidth(60);
        button7.setPrefHeight(30);
        button7.setOnAction(new Mouse(true,6));
        button7.setFocusTraversable(false);
        pane.getChildren().add(button7);
        Button button8=new Button("锋矢阵");
        button8.setLayoutX(Global.getN()*Global.getScale()+10);
        button8.setLayoutY(13*Global.getScale());
        button8.setPrefWidth(60);
        button8.setPrefHeight(30);
        button8.setOnAction(new Mouse(true,7));
        button8.setFocusTraversable(false);
        pane.getChildren().add(button8);
        //妖精阵型按钮
        Button button9=new Button("鹤翼阵");
        button9.setLayoutX(Global.getN()*Global.getScale()+110);
        button9.setLayoutY(6*Global.getScale());
        button9.setPrefWidth(60);
        button9.setPrefHeight(30);
        button9.setOnAction(new Mouse(false,0));
        button9.setFocusTraversable(false);
        pane.getChildren().add(button9);
        Button button10=new Button("雁行阵");
        button10.setLayoutX(Global.getN()*Global.getScale()+110);
        button10.setLayoutY(7*Global.getScale());
        button10.setPrefWidth(60);
        button10.setPrefHeight(30);
        button10.setOnAction(new Mouse(false,1));
        button10.setFocusTraversable(false);
        pane.getChildren().add(button10);
        Button button11=new Button("衡轨阵");
        button11.setLayoutX(Global.getN()*Global.getScale()+110);
        button11.setLayoutY(8*Global.getScale());
        button11.setPrefWidth(60);
        button11.setPrefHeight(30);
        button11.setOnAction(new Mouse(false,2));
        button11.setFocusTraversable(false);
        pane.getChildren().add(button11);
        Button button12=new Button("长蛇阵");
        button12.setLayoutX(Global.getN()*Global.getScale()+110);
        button12.setLayoutY(9*Global.getScale());
        button12.setPrefWidth(60);
        button12.setPrefHeight(30);
        button12.setOnAction(new Mouse(false,3));
        button12.setFocusTraversable(false);
        pane.getChildren().add(button12);
        Button button13=new Button("鱼鳞阵");
        button13.setLayoutX(Global.getN()*Global.getScale()+110);
        button13.setLayoutY(10*Global.getScale());
        button13.setPrefWidth(60);
        button13.setPrefHeight(30);
        button13.setOnAction(new Mouse(false,4));
        button13.setFocusTraversable(false);
        pane.getChildren().add(button13);
        Button button14=new Button("方円阵");
        button14.setLayoutX(Global.getN()*Global.getScale()+110);
        button14.setLayoutY(11*Global.getScale());
        button14.setPrefWidth(60);
        button14.setPrefHeight(30);
        button14.setOnAction(new Mouse(false,5));
        button14.setFocusTraversable(false);
        pane.getChildren().add(button14);
        Button button15=new Button("偃月阵");
        button15.setLayoutX(Global.getN()*Global.getScale()+110);
        button15.setLayoutY(12*Global.getScale());
        button15.setPrefWidth(60);
        button15.setPrefHeight(30);
        button15.setOnAction(new Mouse(false,6));
        button15.setFocusTraversable(false);
        pane.getChildren().add(button15);
        Button button16=new Button("锋矢阵");
        button16.setLayoutX(Global.getN()*Global.getScale()+110);
        button16.setLayoutY(13*Global.getScale());
        button16.setPrefWidth(60);
        button16.setPrefHeight(30);
        button16.setOnAction(new Mouse(false,7));
        button16.setFocusTraversable(false);
        pane.getChildren().add(button16);

        Scene scene=new Scene(pane,Global.getN()*Global.getScale()+200,Global.getM()*Global.getScale());
        scene.setOnKeyPressed(new Keyboard());
        primaryStage.setTitle("葫芦娃大战蛇蝎精");
        primaryStage.setScene(scene);
        primaryStage.show();

        Global.init();
    }

    public static Pane getPane() {
        return pane;
    }

    public static Stage getStage() {
        return stage;
    }
}
