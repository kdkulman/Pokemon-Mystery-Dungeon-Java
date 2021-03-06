package GameView;

import Controller.InputControls;
import FloorGenerator.FloorGenerator;
import FloorGenerator.Floor;

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

    //CAMERA SETTINGS
    private int viewWidth;
    private int viewHeight;

    //Model
    private Floor floor;

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
                int keyCode = e.getKeyCode();
                switch (keyCode){
                    case KeyEvent.VK_W:
                        System.out.println("UP");
                        floor = InputControls.moveUp(floor);
                        break;
                    case KeyEvent.VK_S:
                        System.out.println("DOWN");
                        floor = InputControls.moveDown(floor);
                        break;
                    case KeyEvent.VK_A:
                        System.out.println("LEFT");
                        floor = InputControls.moveLeft(floor);
                        break;
                    case KeyEvent.VK_D:
                        System.out.println("RIGHT");
                        floor = InputControls.moveRight(floor);
                        break;
                    case KeyEvent.VK_SPACE:
                        System.out.println("REGULAR ATTACK");
                        InputControls.useRegularAttack();
                        break;
                    case KeyEvent.VK_SHIFT:
                        System.out.println("SPECIAL ATTACK");
                        InputControls.useSpecialAttack();
                        break;
                    case KeyEvent.VK_1:
                        System.out.println("USE ORAN BERRY");
                        InputControls.useOranBerry();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.out.println("QUIT GAME");
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

    private void createFloor() throws IOException {
        floor = new Floor(new FloorGenerator());
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
                    int cameraHeight = clamp(floor.getPlayerRow() + row - SCREEN_TILE_HEIGHT/2, 0, floor.getFloorArray().length-1);
                    int cameraWidth = clamp(floor.getPlayerColumn() + column - SCREEN_TILE_WIDTH/2, 0, floor.getFloorArray()[0].length-1);

                    if(cameraHeight > floor.getFloorArray().length-1 || cameraWidth > floor.getFloorArray()[0].length-1){
                        draw(row, column,  g2, new Wall().getSprite());
                    } else {
                        draw(row, column,  g2, floor.getFloorArray()[cameraHeight][cameraWidth].getSprite());
                    }
                    int c = cameraHeight;
                    int r = cameraWidth;
                    //System.out.println("Floor[" + c + "][" + r + "].getsprite()");
                }
            }
        }
    }

    private void draw(final int row, final int column, final Graphics2D g2, final BufferedImage image){
        g2.drawImage(image, column*TILE_SIZE,row*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);

    }
}
