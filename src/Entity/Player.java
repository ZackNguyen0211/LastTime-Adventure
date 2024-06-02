package Entity;
import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX ;
    public final int screenY ;
    int standCounter = 0;
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        solidArea = new Rectangle(8, 16, 32, 32);
        attackArea.width = 36;
        attackArea.height = 36;
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 6;
        worldY = gp.tileSize * 5;
        speed = 4;
        direction = "down";

         //Player status 
         maxLife = 6;
         life = maxLife;
    }
    public void getPlayerImage(){
            up1 = setup("/player/up_1.png", gp.tileSize, gp.tileSize);
            up2 = setup("/player/up_2.png", gp.tileSize, gp.tileSize);
            down1 = setup("/player/down_1.png", gp.tileSize, gp.tileSize);
            down2 = setup("/player/down_2.png", gp.tileSize, gp.tileSize);
            left1 = setup("/player/left_1.png", gp.tileSize, gp.tileSize);
            left2 = setup("/player/left_2.png", gp.tileSize, gp.tileSize);
            right1 = setup("/player/right_1.png", gp.tileSize, gp.tileSize);
            right2 = setup("/player/right_2.png", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/attackUp_1.png", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/attackUp_2.png", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/player/attackDown_1.png", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/player/attackDown_2.png", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/player/attackLeft_1.png", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/attackLeft_2.png", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/player/attackRight_1.png", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/attackRight_2.png", gp.tileSize*2, gp.tileSize);
    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e){
            e.printStackTrace();;
        }
        return image;
    }
    public void update(){
        if(attacking == true) {
            attacking();
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction = "up";
            } else if (keyH.downPressed){
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }
            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // If collision is false, player can move
            if(!collisionOn){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 20){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
            standCounter++;
            if(standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
    }
    public void damageMonster(int i){
        if (i != 999) {

        }
        else {

        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (attacking = false) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if (attacking = true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if(attacking = false) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if(attacking = true) {
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if (attacking = false) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                if (attacking = true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if (attacking = false) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if (attacking = true) {
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break;
        }
        g2.drawImage(image, tempScreenX, tempScreenY, gp.tileSize, gp.tileSize, null);
    }
    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            //Save current WorldX, WorldY area.
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAraHeight = solidArea.height;

            //Adjust player's worldX/Y for attackArea
            switch (direction) {
                case "up" : worldY -= attackArea.height; break;
                case "down" : worldY += attackArea.height; break;
                case "left" : worldX -= attackArea.width; break;
                case "right" : worldX += attackArea.width; break;
            }
            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //Check monster collision with the updated worldX, worldY and solidArea
            //int monsterIndex  = gp.cChecker.checkEntity(this, gp.monster);
            //damageMonster(monsterIndex);

            //After checking collision, resort the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAraHeight;
        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
}
