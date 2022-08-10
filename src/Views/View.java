package Views;

import javax.swing.*;
import java.awt.*;

public interface View {
    int SCALE = 3; //Changes
    int BASE_TILE_SIZE = 24;
    int TILE_SIZE = BASE_TILE_SIZE * SCALE;
    int SCREEN_TILE_WIDTH = 13;    //View will be 9 tiles x 13 tiles
    int SCREEN_TILE_HEIGHT = 9;
    int SCREEN_WIDTH = SCREEN_TILE_WIDTH * TILE_SIZE;
    int SCREEN_HEIGHT = SCREEN_TILE_HEIGHT * TILE_SIZE;
    int FPS = 30;
    Font font = new Font("Dialog", Font.PLAIN, 10*SCALE);

}
