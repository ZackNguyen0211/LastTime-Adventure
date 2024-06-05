package Main;

import Entity.Entity;
import Entity.Player;
import Tiles.TileManager;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //World settings
    public final int maxWorldCol = 32;
    public final int maxWorldRow = 24;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    // system
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionCheck cChecker = new CollisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);

    //Entity and Object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity bat[] = new Entity[20];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    public UI ui = new UI(this);

    //Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int optionsState = 5;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setMonster();
        aSetter.setBat();
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        //"Delta" method for GameLoop
        //60 FPS
        double drawInterval = (double) 1000000000 /FPS; //0.01666s
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update(){
        if(gameState == playState){
            player.update();
            //bat
            for(int i = 0; i < bat.length; i++){
                if(bat[i] != null){
                    bat[i].update();
                }
            }
            //monster
            for(int i = 0; i< monster.length; i++){
                if(monster[i] != null){
                    monster[i].update();
                }
            }
        }
        if(gameState == pauseState){
            // nothing
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        // title screen
        if(gameState == titleState){
            ui.draw(g2);
        }
        // others
        else{
            // tile
            tileM.draw(g2);
            // object
            for(int i = 0; i< obj.length; i++){
                if(obj[i] !=null){
                    obj[i].draw(g2, this);
                }
            }
            //bat
            for(int i = 0; i< bat.length; i++){
                if(bat[i] !=null){
                    bat[i].draw(g2);
                }
            }
            //monster
            for(int i = 0; i< monster.length; i++){
                if(monster[i] !=null){
                    monster[i].draw(g2);
                }
            }
            // Collections.sort(entityList, new Comparator<Entity>() {
            //     @Override
            //     public int compare(Entity e1, Entity e2) {
            //         int result = Integer.compare(e1.worldY, e2.worldY);
            //         return result;
            //     }
                
            // });
            // for(int i = 0; i< entityList.size(); i++){
            //     entityList.get(i).draw(g2);
            // }
            // for(int i = 0; i< entityList.size(); i++){
            //     entityList.remove(i);
            // }
            // player
            player.draw(g2);
            // UI
            ui.draw(g2);
            g2.dispose();

        }
       
    }
}
