package GameView;
/*
 * Note: Some code in this file has been reference from an online
 * tutorial: https://www.youtube.com/watch?v=om59cwR7psI&ab_channel=RyiSnow
 * The guide was referenced in the interest of time rather than discovering these
 * nuances of Java Swing through trial and error because gui is optional for this project.
 *
 */
import FloorGenerator.FloorGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameView extends JPanel implements Runnable{
    //View will be 9 tiles x 13 tiles so the player can always be in the middle
    private final int SCALE = 1;
    private final int BASE_TILE_SIZE = 24;
    private final int TILE_SIZE = BASE_TILE_SIZE * SCALE;
    private final int SCREEN_TILE_WIDTH = 13;
    private final int SCREEN_TILE_HEIGHT = 9;
    private final int SCREEN_WIDTH = SCREEN_TILE_WIDTH * TILE_SIZE*5;
    private final int SCREEN_HEIGHT = SCREEN_TILE_HEIGHT * TILE_SIZE*5;
    private final int FPS = 60;
    private int drawX;
    private int drawY;
    private Controller c;
    private BufferedImage texture2 = ImageIO.read(new File("C:\\Users\\Lowen\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\grass_texture2.png"));
    private BufferedImage texture = ImageIO.read(new File("C:\\Users\\Lowen\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\grassTexture.png"));
    private BufferedImage midWall = ImageIO.read(new File("C:\\Users\\Lowen\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\grass_midWall.png"));
    private BufferedImage northWall = ImageIO.read(new File("C:\\Users\\Lowen\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\grass_northWall.png"));
    private BufferedImage player = ImageIO.read(new File("C:\\Users\\Lowen\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\MagikarpPlayer.png"));
    private BufferedImage staircase = ImageIO.read(new File("C:\\Users\\Lowen\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\staircase.png"));
    private BufferedImage trap = ImageIO.read(new File("C:\\Users\\Lowen\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\trap.png"));
    private BufferedImage item = ImageIO.read(new File("C:\\Users\\Lowen\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\oranBerry.png"));
    private BufferedImage enemy = ImageIO.read(new File("C:\\Users\\Lowen\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\enemy.png"));


    private Thread gameThread;


    public GameView() throws IOException {
        c = new Controller();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        gameThread = new Thread(this);
        gameThread.start();
    }



    @Override
    public void run() {

        double interval = 1000000000/FPS; //update every .01666 seconds
        double nextUpdate = System.nanoTime() + interval;
        while(gameThread != null){

            update();
            repaint();


            double timeUntilUpdate = nextUpdate - System.nanoTime(); //1mil = 1sec
            timeUntilUpdate = timeUntilUpdate/1000000;
            if(timeUntilUpdate < 0) timeUntilUpdate = 0;
            try {
                Thread.sleep((long) timeUntilUpdate );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nextUpdate += interval;
        }
    }

    private void update(){

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawFloor(g2);
    }

    private void drawFloor(Graphics2D g2){
        Random r = new Random();
        char[][] floor = c.getFloor();
        for(int row = 0; row <  floor.length; row++){
            for(int column = 0; column < floor[0].length; column++){
                char tile = floor[row][column];
                if (tile == 'T'){
                    draw(column, row, g2, texture2);
                } else if(tile == 'W'){
                    draw(column, row, g2, northWall);
                } else if (tile == 'p'){
                    draw(column, row, g2, player);
                } else if (tile == 's'){
                    draw(column, row, g2, staircase);
                } else if (tile == 't'){
                    draw(column, row, g2, trap);
                } else if (tile == 'i'){
                    draw(column, row, g2, item);
                } else if (tile == 'e'){
                    draw(column, row, g2, enemy);
                }
            }
        }
    }

    private void draw(int column, int row, Graphics2D g2, BufferedImage image){
        drawX = column;
        drawY = row;
        g2.drawImage(image, drawX*TILE_SIZE,drawY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);

    }
    private class Controller{
        private char[][] floor;
        public Controller(){
            FloorGenerator fg = new FloorGenerator();
            floor = fg.getFloor();
            System.out.println(fg.debugToString());

        }

        public char[][] getFloor(){
            return floor;
        }
    }
}
