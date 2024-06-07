package Entity;
import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    KeyHandler keyH;
    public final int screenX ;
    public final int screenY ;
    int standCounter = 0;
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);

        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 18;
        worldY = gp.tileSize * 7;
        speed = 4;
        direction = "down";

        //Player status
        maxLife = 6;
        life = maxLife;
    }
    public void getPlayerImage(){
        up1 = setup("/Picture/player/up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Picture/player/up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/Picture/player/down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Picture/player/down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Picture/player/left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Picture/player/left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Picture/player/right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Picture/player/right_2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage() {
        attackUp1 = setup("/Picture/player/attackUp_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/Picture/player/attackUp_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/Picture/player/attackDown_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/Picture/player/attackDown_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/Picture/player/attackLeft_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/Picture/player/attackLeft_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/Picture/player/attackRight_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/Picture/player/attackRight_2", gp.tileSize*2, gp.tileSize);
    }
    public void update() {
        if(isAttacking) {
            attacking();
        }
        else if(keyH.upPressed|| keyH.downPressed|| keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){
            if(keyH.upPressed){
                direction = "up";
            } else if (keyH.downPressed){
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }
            checkIfAttacking();
            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check bat collision
            int batIndex = gp.cChecker.checkEntity(this, gp.bat);
            interactBat(batIndex);
            //check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interactGreenSlime(monsterIndex);

            // If collision is false, player can move
            if(!collisionOn && !keyH.enterPressed){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if(spriteCounter > 12){
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

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
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
            int solidAreaHeight = solidArea.height;

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
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            isAttacking = false;
        }
    }
    public void damageMonster(int i){
        if (i != 999) {
            System.out.println("Hit");
        }
        else {
            System.out.println("Miss");
        }
    }
    private void checkIfAttacking() {
        if (gp.keyH.enterPressed) {
            isAttacking = true;
        }
    }
    public void interactBat(int batIndex){
        if(batIndex != 999){
            if(!invincible){
                life -= 1;
                invincible = true;
            }
        }
    }
    public void interactGreenSlime(int greenSlimeIndex){
        if(greenSlimeIndex != 999){
            if(!invincible){
                life -= 1;
                invincible = true;
            }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (!isAttacking) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if (isAttacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if(!isAttacking) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if(isAttacking) {
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if (!isAttacking) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                if (isAttacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if (!isAttacking) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if (isAttacking) {
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break;
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);
    }
}
