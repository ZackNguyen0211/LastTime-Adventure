package monster;

import java.util.Random;

import Entity.Entity;
import Main.GamePanel;

public class MON_GreenSlime extends Entity {
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        type = 1;
        name = "Green Slime";
        direction = "down";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getGreenSlimeImage();
    }

    public void getGreenSlimeImage(){
        up1 = setup("/Picture/green_slime/green_slime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/Picture/green_slime/green_slime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup ("/Picture/green_slime/green_slime_down_1", gp.tileSize, gp.tileSize);;
        down2 = setup("/Picture/green_slime/green_slime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/Picture/green_slime/green_slime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/Picture/green_slime/green_slime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/Picture/green_slime/green_slime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/Picture/green_slime/green_slime_down_2", gp.tileSize, gp.tileSize);
    }
    @Override
    public void setAction(){
        actionLockCounter ++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number from 1 to 100
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
