package GameView;

import Controller.InputControls;
import FloorGenerator.FloorGenerator;
/*
 * Note: Some code in this file has been reference from an online
 * tutorial: https://www.youtube.com/watch?v=om59cwR7psI&ab_channel=RyiSnow
 * The guide was referenced in the interest of time rather than discovering the
 * nuances of Java Swing through trial and error.
 *
 */
import TileObjects.Texture;
import TileObjects.TileObject;
import TileObjects.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameView extends JPanel implements Runnable{
    private final int SCALE = 3;
    private final int BASE_TILE_SIZE = 24;
    private final int TILE_SIZE = BASE_TILE_SIZE * SCALE;
    private final int SCREEN_TILE_WIDTH = 13;    //View will be 9 tiles x 13 tiles
    private final int SCREEN_TILE_HEIGHT = 9;
    private final int SCREEN_WIDTH = SCREEN_TILE_WIDTH * TILE_SIZE;
    private final int SCREEN_HEIGHT = SCREEN_TILE_HEIGHT * TILE_SIZE;
    private final int FPS = 60;
    private int drawX;
    private int drawY;
    private Thread gameThread;
    private TileObject[][] floor;
    private InputControls controller;

    //Player Coordinates
    private int playerRow;
    private int playerColumn;

    //CAMERA SETTINGS
    private int viewWidth;
    private int viewHeight;


    public GameView() throws IOException {
        JFrame jFrame = new JFrame();
        jFrame.add(this);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setTitle("Pokemon Mystery Dungeon Java");
        jFrame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        jFrame.setBackground(Color.BLACK);
        jFrame.isDoubleBuffered();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        createFloor(); //Create Model
        jFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("BUTTON HAS BEEN PRESSED :))");
                int keyCode = e.getKeyCode();
                switch (keyCode){
                    case KeyEvent.VK_W:
                        InputControls.moveUp();
                        break;
                    case KeyEvent.VK_S:
                        InputControls.moveDown();
                        break;
                    case KeyEvent.VK_A:
                        InputControls.moveLeft();
                        break;
                    case KeyEvent.VK_D:
                        InputControls.moveRight();
                        break;
                    case KeyEvent.VK_SPACE:
                        InputControls.useRegularAttack();
                        break;
                    case KeyEvent.VK_SHIFT:
                        InputControls.useSpecialAttack();
                        break;
                    case KeyEvent.VK_1:
                        InputControls.useOranBerry();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        InputControls.quitGame();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        jFrame.pack();
        gameThread = new Thread(this);
        gameThread.start();
    }


    private void createInputControls() throws IOException{
        controller = new InputControls(this);
    }


    private void createFloor() throws IOException {
        FloorGenerator floor = new FloorGenerator();
        playerRow = floor.getPlayerRow();
        System.out.println("Player row in GameView:" + playerRow);
        playerColumn = floor.getPlayerColumn();
        this.floor = floor.getFloor();
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
        try {
            drawFloor(g2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Clamp is used to make sure the camera stays in bounds
    private int clamp(final int value, final int min, final int max){
        int theValue = value;
//        if(value - 5 < min) theValue += 5;
//        if(value + 5 > max) theValue -= 5;
        if (min > value) return min;
        if (value > max) return max;
        return theValue;
    }

    private void drawFloor(final Graphics2D g2) throws IOException {
        if (floor != null) {
            for (int row = 0; row < SCREEN_TILE_HEIGHT; row++) {
                for (int column = 0; column < SCREEN_TILE_WIDTH; column++) {
                    int cameraHeight = clamp(playerRow + row - SCREEN_TILE_HEIGHT/2, 0, floor.length-1);
                    int cameraWidth = clamp(playerColumn + column - SCREEN_TILE_WIDTH/2, 0, floor[0].length-1);

                    if(cameraHeight > floor.length-1 || cameraWidth > floor[0].length-1){
                        draw(row, column,  g2, new Wall().getSprite());
                    } else {
                        draw(row, column,  g2, floor[cameraHeight][cameraWidth].getSprite());
                    }
                    int c = cameraHeight;
                    int r = cameraWidth;
                    //System.out.println("Floor[" + c + "][" + r + "].getsprite()");
                }
            }
        }
    }

    private void draw(final int row, final int column, final Graphics2D g2, final BufferedImage image){
//        drawX = column;
//        drawY = row;
        g2.drawImage(image, column*TILE_SIZE,row*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);

    }



    public void setFloor(final TileObject[][] floor){
        this.floor = floor;
    }

    public void setPlayerRow(final int playerRow){
        this.playerRow = playerRow;
    }

    public void setPlayerColumn(final int playerColumn){
        this.playerColumn = playerColumn;
    }

    public int getPlayerRow(){
        return playerRow;
    }

    public int getPlayerColumn() {
        return playerColumn;
    }



    public TileObject[][] getFloor() {
        return floor;
    }


}
