package Main;

import java.awt.*;
import java.awt.image.BufferedImage;

import Object.OBJ_Heart;
import Object.SuperObject;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B, maruMonica, purisaB;
    BufferedImage heart_full, heart_half, heart_blank;
   
    public boolean messageOn = false;
    public String message ="";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    int subState = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        // Create hub object
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }
    public void showMessage(String text){
        message = text ;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
        g2.setColor(Color.white);

        //title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //play state
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }

        //pause state
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }

        //Option State
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }
    }
    public void drawPlayerLife(){

        // gp.player.life = 5; use to check

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // Draw max life
        while (i< gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //Reset
         x = gp.tileSize/2;
         y = gp.tileSize/2;
         i = 0;

         //Draw current life
         while (i< gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i< gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            i++;
            x += gp.tileSize;
            }
         }
    }
    public void drawTitleScreen(){
        g2.setColor(new Color (0, 0, 0) ) ;
        g2.fillRect (0, 0, gp. screenWidth, gp. screenHeight) ;

        //title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        String text = "Last Time Adventure";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        //shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+5, y+5);
        //Main color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //char image
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);

        //Menu
        g2.setFont (g2.getFont().deriveFont(Font.BOLD,40F ));
        text ="NEW GAME";
        x = getXforCenteredText (text) ;
        y += gp.tileSize*4;
        g2.drawString (text, x, y) ;
        if(commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text = "QUIT";
        x = getXforCenteredText (text) ;
        y+= gp.tileSize;
        g2.drawString (text, x, y);
        if(commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
       
        int y = gp.screenHeight/2;
        g2.drawString(text, x , y );
    }
    public void drawOptionsScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        //Sub window
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch (subState){
            case 0: options_top(frameX, frameY); break;
            case 1:  break;
            case 2:  break;
        }
    }
    public void options_top(int frameX, int frameY){
        int textX;
        int textY;

        //Title
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text,textX,textY);

        //Full Screen On/Off
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen",textX,textY);
        if(commandNum == 0){
            g2.drawString(">",textX - 25,textY);
        }
        //Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1){
            g2.drawString(">",textX - 25,textY);
        }
        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum == 2){
            g2.drawString(">",textX - 25,textY);
        }
        //Control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 3){
            g2.drawString(">",textX - 25,textY);
        }
        //End Game
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 4){
            g2.drawString(">",textX - 25,textY);
        }
        //Resume
        textY += gp.tileSize*2;
        g2.drawString("Resume Game", textX, textY);
        if(commandNum == 5){
            g2.drawString(">",textX - 25,textY);
        }
    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
       int x = gp.screenWidth/2 - length/2;
       return x;
    }
}
