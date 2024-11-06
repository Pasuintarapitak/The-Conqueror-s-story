package enitity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class End extends Entity{
    GamePanel gp;
    
    public End(GamePanel gp){
        this.gp = gp;
        solidArea = new Rectangle();
        solidArea.x = 8; // ตำแหน่ง x ของ solid area
        solidArea.y = 16; // ตำแหน่ง y ของ solid area
        solidArea.width = 32; // ความกว้างของ solid area
        solidArea.height = 32;
        name = "end";
        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/character/objects/end.png"));
            // image2 = ImageIO.read(getClass().getResourceAsStream("/character/monster/slime2.png"));

        } catch (IOException e) {
      
            e.printStackTrace(); 
        }
    }

  
}
