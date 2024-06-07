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
        gp.bat[0].worldX = 10 * gp.tileSize;
        gp.bat[0].worldY = 11 * gp.tileSize;

        gp.bat[1] = new MON_Bat(gp);
        gp.bat[1].worldX = 7 * gp.tileSize;
        gp.bat[1].worldY = 7 * gp.tileSize;

        gp.bat[2] = new MON_Bat(gp);
        gp.bat[2].worldX = 8 * gp.tileSize;
        gp.bat[2].worldY = 7 * gp.tileSize;
    }
    public void setMonster(){
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = 16 * gp.tileSize;
        gp.monster[0].worldY = 8 * gp.tileSize;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = 26 * gp.tileSize;
        gp.monster[1].worldY = 9 * gp.tileSize;
    }
}
