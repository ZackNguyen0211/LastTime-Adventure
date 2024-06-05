package monster;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;

import Entity.Entity;
import Main.GamePanel;

public class MON_GreenSlime extends Entity {
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
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

        getMonsterImage();
    }

    public void getMonsterImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Picture/monster/greenslime_down_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Picture/monster/greenslime_down_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Picture/monster/greenslime_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Picture/monster/greenslime_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Picture/monster/greenslime_down_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Picture/monster/greenslime_down_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Picture/monster/greenslime_down_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Picture/monster/greenslime_down_2.png")));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
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
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
