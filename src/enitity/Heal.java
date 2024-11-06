package enitity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

// import enitity.Entity;

public class Heal extends Entity{
       public Heal(){
        solidArea = new Rectangle();
        solidArea.x = 8; 
        solidArea.y = 16; 
        solidArea.width = 32; 
        solidArea.height = 32;
        name = "health";
        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/character/objects/heart_full.png"));
            // image2 = ImageIO.read(getClass().getResourceAsStream("/character/monster/slime2.png"));

        } catch (IOException e) {
      
            e.printStackTrace(); 
        }

        
    }

}
