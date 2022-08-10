package GameView;

import Controller.InputControls;
import DungeonCharacter.DungeonCharacter;
import DungeonCharacter.Enemy.Enemy;
import DungeonCharacter.Hero.Hero;
import FloorGenerator.Floor;

/*
 * Note: Some code in this file has been reference from an online
 * tutorial: https://www.youtube.com/watch?v=om59cwR7psI&ab_channel=RyiSnow
 * The guide was referenced in the interest of time rather than discovering the
 * nuances of Java Swing through trial and error.
 *
 */
import TileObjects.*;
import TileObjects.Items.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public final class GameView extends JPanel implements Runnable{
    private final int SCALE = 3;
    private final int BASE_TILE_SIZE = 24;
    private final int TILE_SIZE = BASE_TILE_SIZE * SCALE;
    private final int SCREEN_TILE_WIDTH = 13;    //View will be 9 tiles x 13 tiles
    private final int SCREEN_TILE_HEIGHT = 9;
    private final int SCREEN_WIDTH = SCREEN_TILE_WIDTH * TILE_SIZE;
    private final int SCREEN_HEIGHT = SCREEN_TILE_HEIGHT * TILE_SIZE;
    private final int FPS = 30;
    private int drawX;
    private int drawY;
    private Thread gameThread;

    //CAMERA SETTINGS
    private int viewWidth;
    private int viewHeight;
    private int viewWidthAnimation = 0;
    private int viewHeightAnimation = 0;

    //Model
    private Floor floor;
    private Hero player;

    public GameView(JFrame jFrame, Hero player) throws IOException {
        this.player = player;
        jFrame.setLocationRelativeTo(null);
        this.setDoubleBuffered(true);
        jFrame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        jFrame.add(this);
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
                        try {
                            floor = InputControls.moveUp(floor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_S:
                        System.out.println("DOWN");
                        try {
                            floor = InputControls.moveDown(floor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_A:
                        System.out.println("LEFT");
                        try {
                            floor = InputControls.moveLeft(floor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_D:
                        System.out.println("RIGHT");
                        try {
                            floor = InputControls.moveRight(floor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        //viewWidthAnimation = TILE_SIZE;
                        break;
                    case KeyEvent.VK_SPACE:
                        System.out.println(InputControls.useRegularAttack(floor));
                        break;
                    case KeyEvent.VK_SHIFT:
                        System.out.println("SPECIAL ATTACK");
                        InputControls.useSpecialAttack();
                        break;
                    case KeyEvent.VK_1:
                        System.out.println("USE ORAN BERRY");
                        floor = InputControls.useOranBerry(floor);
                        break;
                    case KeyEvent.VK_2:
                        System.out.println("USE VISION SEED");
                        floor = InputControls.useVisionSeed(floor);
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

    //This needs to be refactored to a different class
    //Should not be in view class
    private void createFloor() throws IOException {
        floor = new Floor(player);

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
        drawDungeonMap(g2);
        try {
            drawHud(g2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void drawHud(final Graphics2D g) throws IOException {
        int xx = 5*SCALE;
        int yy = SCREEN_HEIGHT/12;
        TileObject[][] floorArray = floor.getFloorArray();
        Hero player = (Hero) floorArray[floor.getPlayerRow()][floor.getPlayerColumn()];

        //Floor Display
        drawStringWithOutline(g, "" + player.getMyFloorLevel() + "F", xx, yy);
        drawHpBar(g, 18*SCALE, yy/2, player);
        drawStringWithOutline(g, "" + player.getHP(), SCREEN_WIDTH/3, yy);
        drawInventory(g, 22*SCALE, -3, player);
    }

    private void drawInventory(final Graphics2D g, final int x, final int y, final Hero player) throws IOException {
        //DRAW ORAN BERRYS
        int numberOfOranBerries = player.getBerryCount();
        int numberOfVisionSeeds = player.getSeedCount();
        int totalInventoryCount = numberOfOranBerries + numberOfVisionSeeds;
        URL url = this.getClass().getResource("/Sprites/TileObjects/Items/Oran_Berry.png");
        BufferedImage oranBerrySprite = ImageIO.read(url);
        for(int i = 0; i < numberOfOranBerries; i++)
            g.drawImage(oranBerrySprite, x+(10*i*SCALE), y, TILE_SIZE/2, TILE_SIZE/2, null);

        //DRAW VISION SEEDS
        url = this.getClass().getResource("/Sprites/TileObjects/Items/Vision_Seed.png");
        BufferedImage visionSeedSprite = ImageIO.read(url);
        for(int i = numberOfOranBerries; i < totalInventoryCount; i++)
            g.drawImage(visionSeedSprite, x+(10*i*SCALE), y, TILE_SIZE/2, TILE_SIZE/2, null);

    }
    private void drawHpBar(final Graphics g, final int x, final int y, final Hero player){
        g.setColor(Color.WHITE);
        g.fillRect(x-1, y-1, SCREEN_WIDTH/4+2, SCREEN_HEIGHT/20+2);
        g.setColor(Color.BLACK);
        g.fillRect(x, y, SCREEN_WIDTH/4, SCREEN_HEIGHT/20);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, (SCREEN_WIDTH/4)*(player.getHP()/player.getMaxHP()), SCREEN_HEIGHT/20);


    }

    private void drawStringWithOutline(final Graphics g, final String string, final int x, final int y){
        g.setFont(new Font("Serif", Font.PLAIN, 10*SCALE));
        g.setColor(Color.WHITE);
        g.drawString(string, x, y);
        g.setColor(Color.BLACK);
//        g.drawString(string,x-1, y-1);
//        g.drawString(string,x-1, y+1);
//        g.drawString(string,x+1, y-1);
//        g.drawString(string,x+1, y+1);
    }

    //Clamp is used to make sure the camera stays in bounds
    private int clamp(final int value, final int min, final int max){
        int theValue = value;
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

                    //This prevents out of bounds errors by just spawning walls in out of bound locations
                    if(cameraHeight > floor.getFloorArray().length-1 || cameraWidth > floor.getFloorArray()[0].length-1){
                        draw(row, column,  g2, new Wall().getSprite());
                    } else {
                        if (floor.getFloorArray()[cameraHeight][cameraWidth] instanceof DungeonCharacter ||
                                floor.getFloorArray()[cameraHeight][cameraWidth] instanceof Item){
                            draw(row, column,  g2, new Texture().getSprite());
                        };
                        draw(row, column,  g2, floor.getFloorArray()[cameraHeight][cameraWidth].getSprite());
                    }

                }
            }
        }
    }

    private void drawDungeonMap(final Graphics2D g2){
        int size = 2*SCALE+1;
        int mapSize = 10;
        for (int row = 0; row < floor.getFloorArray().length; row++) {
            for (int column = 0; column < floor.getFloorArray()[0].length; column++) {
                TileObject tile = floor.getFloorArray()[row][column];
                if(tile.getIsVisibleOnDungeonMap() == true) {
                    if (tile instanceof Texture || tile instanceof SpikeTip) {
                        g2.setColor(new Color(255, 255, 255, 150));
                        g2.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    } else if (tile instanceof Hero) {
                        g2.setColor(Color.blue);
                        g2.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    } else if (tile instanceof Staircase) {
                        g2.setColor(Color.yellow);
                        g2.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    } else if (tile instanceof Item) {
                        g2.setColor(Color.CYAN);
                        g2.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    } else if (tile instanceof Enemy) {
                        g2.setColor(Color.RED);
                        g2.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    }
                }
            }
        }
    }

    private void draw(final int row, final int column, final Graphics2D g2, final BufferedImage image) throws IOException {
        g2.drawImage(image, column*TILE_SIZE,row*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
    }

}
