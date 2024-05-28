package object;
import java.io. IOException;
import javax.imageio.ImageIO;
import Main.GamePanel ;


public class OBJ_Heart extends SuperObject  {
    GamePanel gp;

    public OBJ_Heart (GamePanel gp){
        this.gp =gp ;
        
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Picture/object/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Picture/object/heart_blank.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Picture/object/heart_half.png"));
            // uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            // uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            // uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    
}
