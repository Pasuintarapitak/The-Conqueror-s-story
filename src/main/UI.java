package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Heart;
import object.SuperObject;
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font mono , mono_time;   
    BufferedImage heart_full,heart_half,heart_blank;
    // public boolean messageOn = false;
    // public String message ="";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        mono = new Font("Monospaced",Font.BOLD,60);
        mono_time = new Font("Monospaced",Font.PLAIN,40);
    
        //Create HUD Object
        SuperObject heart = new Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    
    }
    
        // public void showMessage(String text){
        //     message = text;
        //     messageOn = true;
        // }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        // g2.setFont(arial_40);
        // g2.setColor(Color.white);

        //Play State
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawTime();
        }

        if(gp.gameState == gp.endState){
            // drawPlayerLife();
            drawEndScreen();

        }

        if(gp.gameState == gp.overState){
            // drawPlayerLife();
            drawOverScreen();

        }
    }
    public void drawTime(){
        playTime += (double)1/60;
        g2.setFont(mono_time);
        g2.setColor(Color.white);
        g2.drawString("Time:"+dFormat.format(playTime),gp.tileSize*11,65);
    }
    public void drawPlayerLife(){                                                                                   
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        //Draw max life
        while(i < gp.player.maxLife/2){
            // g2.drawImage(heart_blank,x,y,null);
            g2.drawImage(heart_blank, x, y, gp.tileSize-10, gp.tileSize-10, null);
            i++;
            x += gp.tileSize;
        }

        //reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //draw current life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, gp.tileSize-10, gp.tileSize-10, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, gp.tileSize-10, gp.tileSize-10, null);
            }
            i++;
            x += gp.tileSize;
        }

    }
 
    public void drawOverScreen(){
        String text = "Game Over";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.setFont(mono);
        g2.setColor(Color.red);
        g2.drawString(text, x-(gp.tileSize*3), y);
    }
    public void drawEndScreen(){
        String text = "FINISHED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.setFont(mono);
        g2.setColor(Color.YELLOW);
        g2.drawString(text, x-(gp.tileSize*2), y);
        g2.drawString("Time : "+dFormat.format(playTime)+"s",x-(gp.tileSize*3)-8, y+80);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
