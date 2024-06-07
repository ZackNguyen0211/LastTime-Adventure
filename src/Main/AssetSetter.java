package Main;

import monster.MON_Bat;
import monster.MON_GreenSlime;
import Object.OBJ_Heart;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp ;
    }
    
    public void setObject(){
         
    }
    public void setBat(){
        gp.bat[0] = new MON_Bat(gp);
        gp.bat[0].worldX = 31 * gp.tileSize;
        gp.bat[0].worldY = 9 * gp.tileSize;

        gp.bat[1] = new MON_Bat(gp);
        gp.bat[1].worldX = 14 * gp.tileSize;
        gp.bat[1].worldY = 23 * gp.tileSize;

        gp.bat[2] = new MON_Bat(gp);
        gp.bat[2].worldX = 31 * gp.tileSize;
        gp.bat[2].worldY = 23 * gp.tileSize;
    }
    public void setMonster(){
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = 14 * gp.tileSize;
        gp.monster[0].worldY = 9 * gp.tileSize;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = 31 * gp.tileSize;
        gp.monster[1].worldY = 16 * gp.tileSize;
    }
}
