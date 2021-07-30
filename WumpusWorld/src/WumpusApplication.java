

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class WumpusApplication extends Application {



    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;
    private int status;
    private WumpusPlayer player;
    private WumpusMap map;
    private GraphicsContext gc;
    Image floor= new Image ("Floor.gif");
    Image arrow= new Image ("arrow.gif");
    Image black= new Image ("black.GIF");
    Image breeze= new Image ("breeze.gif");
    Image deadWumpus= new Image ("deadWumpus.png");
    Image gold= new Image ("gold.gif");
    Image ladder= new Image ("ladder.gif");
    Image pit= new Image ("pit.gif");
    Image playerDown= new Image ("playerDown.png");
    Image playerLeft= new Image ("playerLeft.png");
    Image playerRight= new Image ("playerRight.png");
    Image playerUp= new Image ("playerUp.png");
    Image stench= new Image ("stench.gif");
    Image wumpus= new Image ("wumpus.png");
    Image playerOrientation = playerDown;
    boolean fogOn = true;
    boolean killWumpus = false;
    int wampr = 0;
    int wampc = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        map = new WumpusMap();
         System.out.print(map);


        primaryStage.setTitle("Wumpus World");

        Group group = new Group();

        Canvas canvas = new Canvas(600, 800);

        canvas.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                String keypress = event.getCharacter();

                //restart
                if((status == WON || status == DEAD ) && keypress.equals("n")){
                        reset();
                }

                if(keypress.equals("s") && status == PLAYING){
                    if(player.getRowPosition() <= 9 ){
                        player.setDirection(2);
                        player.setColPosition(player.getColPosition()+1);
                        player.setRowPosition(player.getRowPosition());
                        map.getSquare(player.getRowPosition(),player.getColPosition()).setVisited(true);
                        playerOrientation = playerDown;
                    }

                }
                if(keypress.equals("w") && status == PLAYING){
                    if(player.getRowPosition() >=0 ){
                        player.setDirection(0);
                        player.setColPosition(player.getColPosition()-1);
                        player.setRowPosition(player.getRowPosition());
                        map.getSquare(player.getRowPosition(),player.getColPosition()).setVisited(true);
                        playerOrientation = playerUp;
                    }
                }
                if(keypress.equals("a") && status == PLAYING){
                    if(player.getRowPosition() > 0 ){
                        player.setDirection(2);
                        player.setColPosition(player.getColPosition());
                        player.setRowPosition(player.getRowPosition()-1);
                        map.getSquare(player.getRowPosition(),player.getColPosition()).setVisited(true);
                        playerOrientation = playerLeft;
                    }
                }
                if(keypress.equals("d") && status == PLAYING){
                    if(player.getRowPosition() < 9 ){
                        player.setDirection(2);
                        player.setColPosition(player.getColPosition());
                        player.setRowPosition(player.getRowPosition()+1);
                        map.getSquare(player.getRowPosition(),player.getColPosition()).setVisited(true);
                        playerOrientation = playerRight;
                    }
                }

                //shooting arrows

                if(player.getArrow() && keypress.equals("i")){
                    for(int a = player.getRowPosition(); a > 0; a--){
                        player.setArrow(false);
                        if(map.getSquare(player.getRowPosition(),a).getWumpus()){
                            map.getSquare(player.getRowPosition(),a).setWumpus(false);
                            map.getSquare(player.getRowPosition(),a).setDeadWumpus(true);
                            killWumpus = true;
                            playerOrientation = playerUp;
                        }
                        System.out.println(map.getSquare(player.getRowPosition(),a));
                        //System.out.print(map.getSquare(player.getRowPosition(),a).getWumpus());
                    }
                }
                if(player.getArrow() && keypress.equals("j")){
                    for(int a = player.getColPosition(); a > 0; a--){
                        player.setArrow(false);
                        if(map.getSquare(a,player.getColPosition()).getWumpus()){
                            map.getSquare(a,player.getColPosition()).setWumpus(false);
                            map.getSquare(a,player.getColPosition()).setDeadWumpus(true);
                            killWumpus = true;
                            playerOrientation = playerLeft;
                        }
                       System.out.println(map.getSquare(a, player.getColPosition()));
                        //System.out.print(map.getSquare(a,player.getColPosition()).getWumpus());
                    }
                }
                if(player.getArrow() && keypress.equals("k")){
                    player.setArrow(false);
                    for(int a = player.getRowPosition(); a < 10; a++){
                        if(map.getSquare(player.getRowPosition(),a).getWumpus()){
                            map.getSquare(player.getRowPosition(),a).setWumpus(false);
                            map.getSquare(player.getRowPosition(),a).setDeadWumpus(true);
                            killWumpus = true;
                            playerOrientation = playerDown;
                        }
                        System.out.println(map.getSquare(player.getRowPosition(),a));
                       // System.out.print(map.getSquare(player.getRowPosition(),a).getWumpus());
                    }
                }
                if(player.getArrow() && keypress.equals("l")){
                    player.setArrow(false);
                    for(int a = player.getColPosition(); a < 10; a++){
                        if(map.getSquare(a,player.getColPosition()).getWumpus()){
                            map.getSquare(a,player.getColPosition()).setWumpus(false);
                            map.getSquare(a,player.getColPosition()).setDeadWumpus(true);
                            killWumpus = true;
                            playerOrientation = playerRight;
                        }
                        System.out.println(map.getSquare(a, player.getColPosition()));
                       // System.out.print(map.getSquare(a,player.getColPosition()).getWumpus());
                    }
                }

                //win
                if(keypress.equals("c") && map.getSquare(player.getRowPosition(),player.getColPosition()).getLadder() && player.getGold()){
                    status = WON;
                }

//                pick up gold
                if(keypress.equals("p") && map.getSquare(player.getRowPosition(),player.getColPosition()).getGold() && status == PLAYING){
                        player.setGold(true);
                }

                // fog o war toggle
                   if (fogOn && keypress.equals("*") && status == PLAYING) {
                       fogOn = false;
                   } else if(!fogOn && keypress.equals("*") && status == PLAYING) {
                       fogOn = true;
                   }





                //System.out.println(player.getRowPosition());
                //System.out.println(player.getColPosition());
                paint(canvas.getGraphicsContext2D());

            }
        });

        group.getChildren().add(canvas);

        Scene scene = new Scene(group);

        primaryStage.setScene(scene);

        reset();
        paint(canvas.getGraphicsContext2D());

        canvas.requestFocus();
        primaryStage.show();
    }
    public void reset(){


        status = PLAYING;
        fogOn = true;
        map = new WumpusMap();
        player = new WumpusPlayer();
        playerOrientation = playerDown;
        player.setRowPosition(map.getLadderRow());
        player.setColPosition(map.getLadderCol());

    }
    public void paint(GraphicsContext gc){

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,600,800);
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0,600,600);
        // text
        gc.setFill(Color.RED);
        gc.setFont(Font.font(20));
        gc.fillText("Inventory",75,620);

        gc.setFill(Color.GREEN);
        gc.setFont(Font.font(20));
        gc.fillText("Messages",425,620);


        for(int x = 0; x < 10; x++){
            for(int y = 0 ; y < 10; y++){
                gc.drawImage(floor,(x*50)+50,(y*50)+50,50,50);

                if(map.getSquare(x,y).getPit()){
                    gc.drawImage(pit,(x*50)+50,(y*50)+50,50,50);
                } else if(map.getSquare(x,y).getGold() && !player.getGold()){
                    gc.drawImage(gold,(x*50)+50,(y*50)+50,50,50);
                } else if(map.getSquare(x,y).getLadder()){
                    gc.drawImage(ladder,(x*50)+50,(y*50)+50,50,50);
                } else if(map.getSquare(x,y).getWumpus()){
                    gc.drawImage(wumpus,(x*50)+50,(y*50)+50,50,50);
                    wampr = x;
                    wampc = y;
                } else if(map.getSquare(x,y).getDeadWumpus()){
                    gc.drawImage(deadWumpus,(x*50)+50,(y*50)+50,50,50);
                }

                if(map.getSquare(x,y).getBreeze() && !map.getSquare(x,y).getPit() ){
                    gc.drawImage(breeze,(x*50)+50,(y*50)+50,50,50);}
                if(map.getSquare(x,y).getStench() && !map.getSquare(x,y).getPit() ){
                    gc.drawImage(stench,(x*50)+50,(y*50)+50,50,50);
                }

                //player

                    gc.drawImage(playerOrientation,(player.getRowPosition()*50)+50,(player.getColPosition()*50)+50,50,50);

//              fog o war
                if(fogOn && !map.getSquare(x,y).getLadder() && !map.getSquare(x,y).getVisited() )
                   gc.drawImage(black,(x*50)+50,(y*50)+50,50,50);

//                  Inventory
                if(player.getArrow()) {
                    gc.drawImage(arrow,30,650,100,100);
                }
                if(player.getGold()){
                    gc.drawImage(gold,130,650,100,100);
                }

            }
        }
//       Messages
        gc.setFont(Font.font(10));
        if(map.getSquare(player.getRowPosition(),player.getColPosition()).getLadder()){
            gc.setFill(Color.LIGHTBLUE);
            gc.fillText("You Bump Into a Ladder",415,650);
        }
        if(map.getSquare(player.getRowPosition(),player.getColPosition()).getPit()){
            gc.setFill(Color.LIGHTBLUE);
            gc.fillText("You Fell Down a Pit to Your Death, Press N to Restart",330,650);
            status = DEAD;
        }
        if(map.getSquare(player.getRowPosition(),player.getColPosition()).getBreeze()){
            gc.setFill(Color.LIGHTBLUE);
            gc.fillText("You Feel a Breeze",415,670);
        }
        if(map.getSquare(player.getRowPosition(),player.getColPosition()).getStench() || map.getSquare(player.getRowPosition(),player.getColPosition()).getDeadWumpus()){
            gc.setFill(Color.LIGHTBLUE);
            gc.fillText("You Smell a Stench",415,690);
        }
        if(map.getSquare(player.getRowPosition(),player.getColPosition()).getWumpus()){
            gc.setFill(Color.LIGHTBLUE);
            gc.fillText("You are Eaten by the Wumpus \n Press N to Restart",415,690);
            status = DEAD;
        }
        if(killWumpus){
            gc.setFill(Color.LIGHTBLUE);
            gc.fillText("You Hear a Scream",415,720);
            killWumpus = false;
        }
        //if(liveWumpus && ){

        //}
        if(map.getSquare(player.getRowPosition(),player.getColPosition()).getGold()){
            gc.setFill(Color.LIGHTBLUE);
            gc.fillText("You See a Glimmer",415,710);
        }
        if(status == WON){
            gc.setFill(Color.LIGHTBLUE);
            gc.fillText("You Have Won the Game \n Press N to Replay",390,710);
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font(30));
            gc.fillText("You Win",250,350);
        }

    }

}
