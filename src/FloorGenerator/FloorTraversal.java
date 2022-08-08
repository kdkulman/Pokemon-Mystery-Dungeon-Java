package FloorGenerator;

import DungeonCharacter.Enemy.Enemy;
import DungeonCharacter.Hero.Hero;
import TileObjects.Staircase;
import TileObjects.Texture;
import TileObjects.TileObject;
import TileObjects.Wall;

public class FloorTraversal {
    private final TileObject[][] floor;
    private boolean isTraversable;
    private final int playerRow;
    private final int playerColumn;

    private final int FLOOR_HEIGHT;
    private final int FLOOR_WIDTH;

    public FloorTraversal(TileObject[][] floor, int playerRow, int playerColumn) {
        this.floor = floor;
        FLOOR_HEIGHT = floor.length;
        FLOOR_WIDTH = floor[0].length;
        this.playerColumn = playerColumn;
        this.playerRow = playerRow;
        this.setTraversable();
    }

    private boolean isFloorTraversable(int currentRow, int currentColumn, int[][] memory) {
        if(currentRow < 0  || currentRow == FLOOR_HEIGHT || currentColumn < 0 || currentColumn == FLOOR_WIDTH) {
            return false;
        }
        if(this.floor[currentRow][currentColumn] instanceof Texture) { // Staircase
            return true;
        }
        if(memory[currentRow][currentColumn] != -1) {
            return (memory[currentRow][currentColumn] > 0);
        }
        if(floor[currentRow][currentColumn].getSolid()){
            return false;
        }
        boolean result =    (isFloorTraversable(currentRow + 1, currentColumn, memory) ||
                isFloorTraversable(currentRow - 1, currentColumn, memory) ||
                isFloorTraversable(currentRow, currentColumn + 1, memory) ||
                isFloorTraversable(currentRow, currentColumn - 1, memory));
        memory[currentRow][currentColumn] = result ? 1 : 0;
        return result;
    }

    public void setTraversable() {
        int[][] memory = new int [FLOOR_HEIGHT][FLOOR_WIDTH];
        for(int i = 0; i < FLOOR_HEIGHT; i++) {
            for(int j = 0; j < FLOOR_WIDTH; j++) {
                memory[i][j] = -1;
            }
        }
        this.isTraversable = isFloorTraversable(this.playerRow, this.playerColumn, memory);
    }

    public boolean getTraversable() {
        return this.isTraversable;
    }
}