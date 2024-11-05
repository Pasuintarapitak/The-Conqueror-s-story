package object;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
// import java.util.Random;

import javax.imageio.ImageIO;

import enitity.Entity;
import main.GamePanel;




public class OBJ_slime extends Entity{

    
    public OBJ_slime(){
        solidArea = new Rectangle();
        solidArea.x = 8; // ตำแหน่ง x ของ solid area
        solidArea.y = 16; // ตำแหน่ง y ของ solid area
        solidArea.width = 32; // ความกว้างของ solid area
        solidArea.height = 32;
        name = "slime";
        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/character/monster/slime.png"));
            // image2 = ImageIO.read(getClass().getResourceAsStream("/character/monster/slime2.png"));
            

        } catch (IOException e) {
            e.printStackTrace(); 
        }

        
    }
    public void draw(Graphics2D g2 , GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize  > gp.player.worldX - gp.player.screenX &&
        worldX  - gp.tileSize< gp.player.worldX + gp.player.screenX &&
        worldY  + gp.tileSize> gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize< gp.player.worldY + gp.player.screenY){
            g2.drawImage(image, screenX, screenY , gp.tileSize , gp.tileSize , null);
        }
    }

   

}



