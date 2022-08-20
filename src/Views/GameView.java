package Views;
/*
 * Note: Some code in this file has been reference from an online
 * tutorial: https://www.youtube.com/watch?v=om59cwR7psI&ab_channel=RyiSnow
 * The guide was referenced in the interest of time rather than discovering the
 * nuances of Java Swing through trial and error.
 *
 */
import Controller.InputControls;
import DungeonCharacter.DungeonCharacter;
import DungeonCharacter.Enemy.Enemy;
import DungeonCharacter.Hero.Hero;
import FloorGenerator.Floor;
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
import java.util.Scanner;
import static Controller.GameManager.myJFrame;

public final class GameView extends JPanel implements Runnable, View {
    private static Floor myFloor;
    private Hero myPlayer;
    private Thread myGameViewThread;
    private Message myMessage;

    public GameView(final Hero thePlayer) throws IOException {
        this(thePlayer, new Floor(thePlayer));
    }

    public GameView(final Hero thePlayer, final Floor theFloor) throws IOException {
        this.myMessage = Message.getMyInstance();
        this.myPlayer = thePlayer;
        this.myFloor = theFloor;
        myJFrame.addKeyListener(new KeyListener() {
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
                            myFloor = InputControls.moveUp(myFloor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_S:
                        System.out.println("DOWN");
                        try {
                            myFloor = InputControls.moveDown(myFloor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_A:
                        System.out.println("LEFT");
                        try {
                            myFloor = InputControls.moveLeft(myFloor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_D:
                        System.out.println("RIGHT");
                        try {
                            myFloor = InputControls.moveRight(myFloor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        System.out.println("Regular Attack");
                        try {
                            myFloor = InputControls.useRegularAttack(myFloor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_SHIFT:
                        System.out.println("Special Attack");
                        try {
                            myFloor = InputControls.useSpecialAttack(myFloor);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_1:
                        System.out.println("USE ORAN BERRY");
                        myFloor = InputControls.useOranBerry(myFloor);
                        break;
                    case KeyEvent.VK_2:
                        System.out.println("USE VISION SEED");
                        myFloor = InputControls.useVisionSeed(myFloor);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.out.println("QUIT GAME");
                        InputControls.quitGame();
                        break;
                    case KeyEvent.VK_P:
                        InputControls.saveGame(myFloor);
                        break;
                    case KeyEvent.VK_V:
                        System.out.println("CHEAT: GET VISION SEED");
                        thePlayer.collectVisionSeeds();
                        break;
                    case KeyEvent.VK_B:
                        System.out.println("CHEAT: GET ORAN BERRY");
                        thePlayer.collectOranBerry();
                        break;
                    default:
                        break;
                }
                gameOverCheck();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        myGameViewThread = new Thread(this);
        myGameViewThread.start();
        myJFrame.pack();
    }

    private void createFloor() throws IOException {
        myFloor = new Floor(myPlayer);
    }

    @Override
    public void run() {

        double interval = 1000000000/FPS; //update every .01666 seconds
        double nextUpdate = System.nanoTime() + interval;
        while(myGameViewThread != null){
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

    protected void paintComponent(final Graphics theGraphics){
        super.paintComponent(theGraphics);
        Graphics2D g2 = (Graphics2D) theGraphics;
        try {
            drawFloor(g2);
            drawDungeonMap(g2);
            drawHud(g2);
            drawMessage(g2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gameOverCheck() {
        if(myPlayer.getHP() < 1) {
            Message.setMessage(myPlayer.getName() + " fainted! Game over!");
            Scanner in = new Scanner(System.in);
            System.exit(0);
        }
    }

    private void drawMessage(final Graphics2D theGraphics) throws IOException {
        if (Message.getVisible()) {
            URL url = this.getClass().getResource("/Sprites/Text_Box.png");
            BufferedImage sprite = ImageIO.read(url);
            String message = Message.getMessage();
            String previousMessage = Message.getPreviousMessage();
            int x = this.getWidth()/7;
            int y = this.getHeight()-this.getHeight()/5;
            theGraphics.drawImage(sprite, x, y, sprite.getWidth() * 3, sprite.getHeight() * 2, null);
            theGraphics.setFont(MESSAGE_FONT);
            theGraphics.setColor(Color.white);
            drawStringWithOutline(theGraphics, previousMessage, x+9*SCALE, y + 13*SCALE);
            drawStringWithOutline(theGraphics, message, x+9*SCALE, y + 25*SCALE);
        }
    }

    private void drawHud(final Graphics2D theGraphics) throws IOException {
        int xx = 5*SCALE;
        int yy = SCREEN_HEIGHT/12;
        TileObject[][] floorArray = myFloor.getFloorArray();
        Hero player = (Hero) floorArray[myFloor.getMyPlayerRow()][myFloor.getMyPlayerColumn()];
        theGraphics.setFont(FONT);
        drawStringWithOutline(theGraphics, "" + player.getMyFloorLevel() + "F", xx, yy);
        drawHpBar(theGraphics, 18*SCALE, yy/2, player);
        drawStringWithOutline(theGraphics, "" + player.getHP() + "HP", SCREEN_WIDTH/3, yy);
        drawInventory(theGraphics, 22*SCALE, -3, player);
    }

    private static void drawInventory(final Graphics2D theGraphics, final int theX, final int theY, final Hero thePlayer) throws IOException {
        //DRAW ORAN BERRYS
        int numberOfOranBerries = thePlayer.getBerryCount();
        int numberOfVisionSeeds = thePlayer.getSeedCount();
        int totalInventoryCount = numberOfOranBerries + numberOfVisionSeeds;
        URL url = GameView.class.getResource("/Sprites/TileObjects/Items/Oran_Berry.png");
        BufferedImage oranBerrySprite = ImageIO.read(url);
        for(int i = 0; i < numberOfOranBerries; i++)
            theGraphics.drawImage(oranBerrySprite, theX+(10*i*SCALE), theY, TILE_SIZE/2, TILE_SIZE/2, null);

        //DRAW VISION SEEDS
        url = GameView.class.getResource("/Sprites/TileObjects/Items/Vision_Seed.png");
        BufferedImage visionSeedSprite = ImageIO.read(url);
        for(int i = numberOfOranBerries; i < totalInventoryCount; i++)
            theGraphics.drawImage(visionSeedSprite, theX+(10*i*SCALE), theY, TILE_SIZE/2, TILE_SIZE/2, null);

    }
    private static void drawHpBar(final Graphics theGraphics, final int theX, final int theY, final Hero thePlayer){
        theGraphics.setColor(Color.BLACK);
        theGraphics.fillRect(theX-2, theY-2, SCREEN_WIDTH/4+4, SCREEN_HEIGHT/20+4);
        theGraphics.setColor(Color.LIGHT_GRAY);
        theGraphics.fillRect(theX, theY, SCREEN_WIDTH/4, SCREEN_HEIGHT/20);
        theGraphics.setColor(Color.CYAN);
        //FillRect only takes in integers so must calculate the width using double division
        double hpBarWidthDouble = 1.0* thePlayer.getHP()/thePlayer.getMaxHP()*SCREEN_WIDTH/4.0;
        int hpBarWidth = (int) Math.round(hpBarWidthDouble);
        theGraphics.fillRect(theX, theY, hpBarWidth, SCREEN_HEIGHT/20);
    }

    //Clamp is used to make sure the camera stays in bounds
    private static int clamp(final int theValue, final int theMin, final int theMax){
        int value = theValue;
        if (theMin > theValue) return theMin;
        if (theValue > theMax) return theMax;
        return value;
    }

    private static void drawFloor(final Graphics2D theGraphics) throws IOException {
        if (myFloor != null) {
            for (int row = 0; row < SCREEN_TILE_HEIGHT; row++) {
                for (int column = 0; column < SCREEN_TILE_WIDTH; column++) {
                    int cameraHeight = clamp(myFloor.getMyPlayerRow() + row - SCREEN_TILE_HEIGHT/2, 0, myFloor.getFloorArray().length-1);
                    int cameraWidth = clamp(myFloor.getMyPlayerColumn() + column - SCREEN_TILE_WIDTH/2, 0, myFloor.getFloorArray()[0].length-1);

                    //This prevents out of bounds errors by just spawning walls in out of bound locations
                    if(cameraHeight > myFloor.getFloorArray().length-1 || cameraWidth > myFloor.getFloorArray()[0].length-1){
                        draw(row, column,  theGraphics, new Wall().getMySprite());
                    } else {
                        if (myFloor.getFloorArray()[cameraHeight][cameraWidth] instanceof DungeonCharacter ||
                                myFloor.getFloorArray()[cameraHeight][cameraWidth] instanceof Item){
                            draw(row, column,  theGraphics, new Texture().getMySprite());
                        };
                        draw(row, column,  theGraphics, myFloor.getFloorArray()[cameraHeight][cameraWidth].getMySprite());
                    }

                }
            }
        }
    }

    private static void drawDungeonMap(final Graphics2D theGraphics){
        int size = 2*SCALE+1;
        int mapSize = 10;
        for (int row = 0; row < myFloor.getFloorArray().length; row++) {
            for (int column = 0; column < myFloor.getFloorArray()[0].length; column++) {
                TileObject tile = myFloor.getFloorArray()[row][column];
                if(tile.getIsVisibleOnDungeonMap() == true) {
                    if (tile instanceof Texture || tile instanceof SpikeTip) {
                        theGraphics.setColor(new Color(255, 255, 255, 150));
                        theGraphics.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    } else if (tile instanceof Hero) {
                        theGraphics.setColor(Color.blue);
                        theGraphics.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    } else if (tile instanceof Staircase) {
                        theGraphics.setColor(Color.yellow);
                        theGraphics.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    } else if (tile instanceof Item) {
                        theGraphics.setColor(Color.CYAN);
                        theGraphics.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    } else if (tile instanceof Enemy) {
                        theGraphics.setColor(Color.RED);
                        theGraphics.fillRect(column * size + SCREEN_WIDTH / mapSize, row * size + SCREEN_HEIGHT / mapSize, size, size);
                    }
                }
            }
        }
    }

    private static void draw(final int theRow, final int theColumn, final Graphics2D theGraphics,
                             final BufferedImage theImage) throws IOException {
        theGraphics.drawImage(theImage, theColumn*TILE_SIZE,theRow*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
    }

}
