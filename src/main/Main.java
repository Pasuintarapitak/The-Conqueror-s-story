package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        //Set ตัวหน้าจอ GUI
        JFrame window = new JFrame(); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ให้มีปุ่มกดปิด
        window.setResizable(false); //ไม่ใส่แก้ไขขนาดหน้าจอได้
        window.setTitle("The Conqueror's story");
        
        GamePanel gamePanel = new GamePanel();  
        window.add(gamePanel);
        window.pack(); 

        gamePanel.setupGame();
        gamePanel.startGameThread();

        window.setLocationRelativeTo(null); //อยู่กลางจอ
        window.setVisible(true);
        
        
    }
    
}