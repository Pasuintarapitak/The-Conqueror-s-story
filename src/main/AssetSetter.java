package main;

import enitity.Entity;
import object.OBJ_end;
import object.OBJ_health;
// import monster.MON_slime;
// import object.OBJ_Key;
import object.OBJ_slime;
import object.SuperObject;

public class AssetSetter {
    
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject(){
   
            int[] posX = {56, 14, 17,61,69}; // เพิ่มตำแหน่ง x
            int[] posY = {12, 30, 62,92,21}; // เพิ่มตำแหน่ง y
        
            for (int i = 0; i < posX.length; i++) {
                if (i < gp.health.length) { 
                    gp.health[i] = new OBJ_health();
                    gp.health[i].worldX = posX[i] * gp.tileSize;
                    gp.health[i].worldY = posY[i] * gp.tileSize;
                }
            }

            gp.end[0] = new OBJ_end(gp);
            gp.end[0].worldX = 90 * gp.tileSize;
            gp.end[0].worldY = 96 * gp.tileSize;
        
      
    }
    
    public void setMonsters(){
        
            int[] posX = {19, 30, 31, 56, 51, 51, 23, 24, 22, 37, 48, 57, 41,  64, 53, };
            int[] posY = {18, 12, 12, 7, 20, 21, 25, 35, 53, 69, 69, 55, 82, 74, 91, };
        
            for (int i = 0; i < posX.length; i++) {
                gp.monster[i] = new OBJ_slime();
                gp.monster[i].worldX = posX[i] * gp.tileSize;
                gp.monster[i].worldY = posY[i] * gp.tileSize;
            }
        
        
   
    }
       
}
