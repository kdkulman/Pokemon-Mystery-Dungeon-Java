package FloorGenerator;

import DungeonCharacter.Hero.Hero;
import DungeonCharacter.Hero.Jirachi;
import TileObjects.Items.OranBerry;
import TileObjects.Texture;
import TileObjects.TileObject;
import TileObjects.Wall;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FloorGeneratorTest {

    private TileObject[][] myFloor;
    private FloorGenerator myFloorGenerator;

    private final int FLOOR_WIDTH = 3; //56 IS DEFAULT
    private final int FLOOR_HEIGHT = 3; //32 IS DEFAULT
    private Hero myHero;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        myFloorGenerator = new FloorGenerator(new Jirachi());
        TileObject texture = new Texture();
        TileObject wall = new Wall();
        myFloor = new TileObject[][]{
                {wall, wall,    wall},
                {wall, texture, wall},
                {wall, wall,    wall},
        };
        myFloorGenerator.setMyFloor(myFloor);
        myHero = new Jirachi();
    }
    @Test
    void placePlayerTest_0() throws IOException {
        myFloorGenerator.placePlayerTest((Hero) myHero, 3, 3);
        assertTrue(myFloorGenerator.getMyPlayerRow() != 0);
        assertTrue(myFloorGenerator.getMyPlayerColumn() != 0);
    }

    @Test
    void placePlayerTest_1() throws IOException {
        myFloorGenerator.placePlayerTest((Hero) myHero, 3, 3);
        assertTrue(myFloorGenerator.getMyPlayerRow() == 1);
        assertTrue(myFloorGenerator.getMyPlayerColumn() == 1);
    }

    @Test
    void placePlayerTest_2() throws IOException {
        myFloorGenerator.placePlayerTest((Hero) myHero, 3, 3);
        assertTrue(myFloorGenerator.getMyPlayerRow() != 2);
        assertTrue(myFloorGenerator.getMyPlayerColumn() != 2);
    }

    @Test
    void placeTileObjectTest_0() throws IOException {
        TileObject oranBerry = new OranBerry();
        myFloorGenerator.placeTileObjectTest(oranBerry, 3, 3);
        myFloor = myFloorGenerator.getMyFloor();
        assertTrue(oranBerry != myFloor[0][0]);
    }

    @Test
    void placeTileObjectTest_1() throws IOException {
        TileObject oranBerry = new OranBerry();
        myFloorGenerator.placeTileObjectTest(oranBerry, 3, 3);
        myFloor = myFloorGenerator.getMyFloor();
        assertTrue(oranBerry == myFloor[1][1]);
    }

    @Test
    void placeTileObjectTest_2() throws IOException {
        TileObject oranBerry = new OranBerry();
        myFloorGenerator.placeTileObjectTest(oranBerry, 3, 3);
        myFloor = myFloorGenerator.getMyFloor();
        assertTrue(oranBerry != myFloor[2][2]);
    }
}