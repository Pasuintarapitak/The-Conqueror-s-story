package enitity;

import java.awt.Graphics2D;
// import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
    GamePanel gp;
    public int worldX , worldY;
    public int speed;

    public BufferedImage up1 , up2 , down1 , down2 , left1 , left2 , right1 , right2 , atk , atkB;
    public String direction = "down";
    public String atk_direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCounter = 0;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;

    public BufferedImage image , image2 , image3;
    public String name;
    public boolean collision = false;


    //Character status
    public int maxLife;
    public int life;
    public Entity(){

    }
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){}

 

    public void getImage(){}
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

}
