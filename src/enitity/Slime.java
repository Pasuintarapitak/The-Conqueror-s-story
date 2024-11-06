package enitity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
// import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;




public class Slime extends Entity{

    
    public Slime(){
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
 

   

}



