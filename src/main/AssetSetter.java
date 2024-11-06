package main;

import enitity.End;
import enitity.Heal;
import enitity.Slime;
import enitity.slimeKing;

public class AssetSetter {
    
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject(){
            //health
            int[] posX = {56, 14, 17,61,69}; // เพิ่มตำแหน่ง x
            int[] posY = {12, 30, 62,92,21}; // เพิ่มตำแหน่ง y
        
            for (int i = 0; i < posX.length; i++) {
                if (i < gp.health.length) { 
                    gp.health[i] = new Heal();
                    gp.health[i].worldX = posX[i] * gp.tileSize;
                    gp.health[i].worldY = posY[i] * gp.tileSize;
                }
            }

            //END
            gp.end[0] = new End(gp);
            gp.end[0].worldX = 90 * gp.tileSize;
            gp.end[0].worldY = 96 * gp.tileSize;

            // gp.end[1] = new OBJ_end(gp);
            // gp.end[1].worldX = 2 * gp.tileSize;
            // gp.end[1].worldY = 2 * gp.tileSize;
        
      
    }
    
    public void setMonsters(){
        
            //slime
            int[] posX = {19, 30, 31, 56, 51, 51, 23, 24, 22, 37, 48, 57, 41,  64, 53, };
            int[] posY = {18, 12, 12, 7, 20, 21, 25, 35, 53, 69, 69, 55, 82, 74, 91, };
        
            for (int i = 0; i < posX.length; i++) {
                gp.monster[i] = new Slime();
                gp.monster[i].worldX = posX[i] * gp.tileSize;
                gp.monster[i].worldY = posY[i] * gp.tileSize;
            }
            
            //slime king

            gp.slimeKing[0] = new slimeKing();
            gp.slimeKing[0].worldX = 68 * gp.tileSize;
            gp.slimeKing[0].worldY = 80 * gp.tileSize;
            // gp.slimeKing[0].worldX = 10 * gp.tileSize;
            // gp.slimeKing[0].worldY = 10 * gp.tileSize;
          

   
    }
       
}
