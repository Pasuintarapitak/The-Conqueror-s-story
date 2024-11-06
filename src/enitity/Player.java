    package enitity;

    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.IOException;

    import javax.imageio.ImageIO;

    import main.GamePanel;
    import main.KeyHandler;
    // import monster.MON_slime;

    public class Player extends Entity{
        GamePanel gp;
        KeyHandler keyH;
        public final int screenX;
        public final int screenY;
        public final int damage = 1;
        
        public Player(GamePanel gp , KeyHandler keyH){
            this.gp = gp;
            this.keyH = keyH;
        
            screenX = gp.screenWidth / 2 - (gp.tileSize/2);
            screenY = gp.screenHeight / 2 - (gp.tileSize/2);

            solidArea = new Rectangle();
            solidArea.x = 8;
            solidArea.y = 16;
            solidArea.width = 32;
            solidArea.height = 32;
        
            setDefaultValues();
            getPlayerImage();
        }
        public void setDefaultValues(){
            worldX = gp.tileSize * 1;
            worldY = gp.tileSize * 1;
            speed = 4;
            direction = "down";

            //Player status
            maxLife = 10;
            life = maxLife;
        }
        public void getPlayerImage(){
            try {
                up1 = ImageIO.read(getClass().getResourceAsStream("/character/player/p_up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/character/player/p_up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/character/player/p_down1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/character/player/p_down2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/character/player/p_left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/character/player/p_left2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/character/player/p_right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/character/player/p_right2.png"));


            } catch (IOException e) {
                e.printStackTrace(); 
            }
        }
        public void update(){
            if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
                if(keyH.upPressed){
                    direction = "up";
                }
                else if(keyH.downPressed){
                    direction = "down";
                }
                else if(keyH.leftPressed){
                    direction = "left";
                }
                else if(keyH.rightPressed){
                    direction = "right";
                }
        
                // Check tile collision
                collisionOn = false;
                gp.cChecker.checkTile(this);
                // if collision is false , player can move
                if(collisionOn == false){
                    switch (direction) {
                        case "up":
                            worldY-=speed;
                            break;
                        case "down":
                            worldY+=speed; 
                            break;
                        case "left":
                            worldX-=speed;                                 
                            break;
                        case "right":
                            worldX+=speed;                                
                            break;
                
                    }
                }

                spriteCounter++;
                if(spriteCounter > 10){
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2){
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
        
        }
        public void draw(Graphics2D g2){
            // g2.setColor(Color.WHITE);
            // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

            BufferedImage image = null;
            switch (direction) {
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    else{
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    else{
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    else{
                        image = left2;
                    }
                    
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    else{
                        image = right2;
                    }
                
                    break;
    
                    
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize ,gp.tileSize ,null); 
        }
        public void checkEnd() {
            if (gp.end[0] != null) {
              
                if (this.worldX < gp.end[0].worldX + gp.end[0].solidArea.width &&
                    this.worldX + this.solidArea.width > gp.end[0].worldX &&
                    this.worldY < gp.end[0].worldY + gp.end[0].solidArea.height &&
                    this.worldY + this.solidArea.height > gp.end[0].worldY) {
                    
                    gp.gameState = gp.endState;
                    System.out.println("Reached the end!");
                }

            //     if (this.worldX < gp.end[1].worldX + gp.end[1].solidArea.width &&
            //     this.worldX + this.solidArea.width > gp.end[1].worldX &&
            //     this.worldY < gp.end[1].worldY + gp.end[1].solidArea.height &&
            //     this.worldY + this.solidArea.height > gp.end[1].worldY) {
                
            //     gp.gameState = gp.endState;
            //     System.out.println("Reached the end!");
            // }
            }
        }
        public void checkHP(){
            if(this.life < 1){
                gp.gameState = gp.overState;
                System.out.println("Game Over");
            }
        }
        
        public void checkDamage() {
            for (int i = 0; i < gp.monster.length; i++) {
                if (gp.monster[i] != null) {
                    // ตรวจสอบว่าตำแหน่งของผู้เล่นตรงกับตำแหน่งของมอนสเตอร์
                    if (this.worldX < gp.monster[i].worldX + gp.monster[i].solidArea.width &&
                        this.worldX + this.solidArea.width > gp.monster[i].worldX &&
                        this.worldY < gp.monster[i].worldY + gp.monster[i].solidArea.height &&
                        this.worldY + this.solidArea.height > gp.monster[i].worldY) {
                        life--; // ลดชีวิตของผู้เล่น
                        gp.monster[i] = null;
                        System.out.println("decrease HP");
                        // คุณสามารถจัดการกับการชนกันที่นี่ เช่น การรีเซ็ตตำแหน่งของมอนสเตอร์
                    }

                    if (gp.slimeKing[0] != null) {
                        // ตรวจสอบว่าตำแหน่งของผู้เล่นตรงกับตำแหน่งของมอนสเตอร์
                        if (this.worldX < gp.slimeKing[0].worldX + gp.slimeKing[0].solidArea.width &&
                            this.worldX + this.solidArea.width > gp.slimeKing[0].worldX &&
                            this.worldY < gp.slimeKing[0].worldY + gp.slimeKing[0].solidArea.height &&
                            this.worldY + this.solidArea.height > gp.slimeKing[0].worldY) {
                            life-=4; // ลดชีวิตของผู้เล่น
                            gp.slimeKing[0] = null;
                            System.out.println("decrease HP");
                            // คุณสามารถจัดการกับการชนกันที่นี่ เช่น การรีเซ็ตตำแหน่งของมอนสเตอร์
                        }
                    }
            }
           
     
        }
    }
        public void checkHealth(){
            for (int i = 0; i < gp.health.length; i++) {
                if (gp.health[i] != null) {
                    
                    if (this.worldX < gp.health[i].worldX + gp.health[i].solidArea.width &&
                        this.worldX + this.solidArea.width >  gp.health[i].worldX &&
                        this.worldY <  gp.health[i].worldY +  gp.health[i].solidArea.height &&
                        this.worldY + this.solidArea.height >  gp.health[i].worldY) {
                        life++; // ลดชีวิตของผู้เล่น
                        gp.health[i] = null;
                        System.out.println("increase HP");
                        
                    }
                }
            }
        }

    }
