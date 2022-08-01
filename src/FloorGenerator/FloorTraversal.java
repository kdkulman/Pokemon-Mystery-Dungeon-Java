package FloorGenerator;

public class FloorTraversal {
    private final char[][] floor;
    private boolean isTraversable;
    private final int[] playerPosition;

    private final int FLOOR_HEIGHT = 32;
    private final int FLOOR_WIDTH = 56;

    public FloorTraversal(char[][] floor) {
        this.floor = floor;
        this.playerPosition = new int[2];
        for(int i = 0; i < FLOOR_HEIGHT; i++) {
            for(int j = 0; j < FLOOR_WIDTH; j++) {
                if(floor[i][j] == 'p') {
                    this.playerPosition[0] = i;
                    this.playerPosition[1] = j;
                }
            }
        }
    }

    private boolean isFloorTraversable(int currentRow, int currentColumn, int[][] memory) {
        if(currentRow < 0  || currentRow == FLOOR_HEIGHT || currentColumn < 0 || currentColumn == FLOOR_WIDTH) {
            return false;
        }
        if(this.floor[currentRow][currentColumn] == 's') { // Staircase
            return true;
        }
        if(memory[currentRow][currentColumn] != -1) {
            return (memory[currentRow][currentColumn] > 0);
        }
        if(this.floor[currentRow][currentColumn] != 'T') { // Not Traversable
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
        this.isTraversable = isFloorTraversable(this.playerPosition[0], this.playerPosition[1], memory);
    }

    public boolean getTraversable() {
        return this.isTraversable;
    }
}