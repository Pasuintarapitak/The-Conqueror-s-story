package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

// import javax.swing.JFrame;
import javax.swing.JPanel;

import enitity.Entity;
// import enitity.Entity;
import enitity.Player;
import object.OBJ_end;
import object.OBJ_health;
import object.OBJ_slime;
import object.SuperObject;
import tile.TitleManager;

// For set game resolution display
public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; 
    public final int screenWidth = tileSize * maxScreenCol; // 48*16 = 768px
    public final int screenHeight = tileSize * maxScreenRow; // 48*12 = 576px

    //World settings
    public final int maxWorldCol = 100; 
    public final int maxWorldRow = 100;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;
    
    //System
    TitleManager tileM = new TitleManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    //Entity and object                  
    public Player player = new Player(this,keyH);
    // public SuperObject obj[] = new SuperObject[10]; 
    // public SuperObject monster[] = new SuperObject[20];
    public Entity health[] = new OBJ_health[20]; 
    public Entity end[] = new OBJ_end[2];
    public Entity monster[] = new OBJ_slime[20];

    public UI ui = new UI(this);
    


    //GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int endState = 2;
    

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);  
        this.addKeyListener(keyH); // Receive when press button
        this.setFocusable(true);  // GamePanel can be focused to receive key input
    }

  

    public void setupGame(){
        aSetter.setMonsters();
        aSetter.setObject();
        gameState = playState;
    }

    public void startGameThread(){
        gameThread  = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        // long currentTime = System.nanoTime();
        // long currentTime = System.currentTimeMillis();
        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null){
            

           
            //1. UPDATE : update information such as character positions 
            update();

            //2. DRAW : draw the screen with the updated information
            repaint(); // calls paintComponent
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;//เพราะว่า Thread.sleep รับเป็น miliseconds
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if(gameState == playState){
            player.update();
            player.checkDamage();
            player.checkHealth();
            player.checkEnd();


        }
        if(gameState == endState){
            //nothing
        }
    }
    /*Graphics มีหลาย function ในการวาด object บนหน้าจอ */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //Title
        tileM.draw(g2);
        //Object
        for (int i = 0; i < health.length; i++) {
            if(health[i] != null){   
                health[i].draw(g2,this);
            }
        }
        if(end[0] != null){   
            end[0].draw(g2, this);
        }
        
        //monster

        for(int i = 0; i < monster.length; i++){
            if(monster[i] != null){
                monster[i].draw(g2, this); // ใช้ this แทน monster[i]
            }
        }


        //Player
        player.draw(g2);
        //UI
        ui.draw(g2);
        g2.dispose();
    }
 


}       