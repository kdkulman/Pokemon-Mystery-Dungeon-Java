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

    private boolean isFloorTraversable(int currentRow, int currentColumn, boolean[][] memory){
        //check if out of bounds
        if(currentRow < 0  || currentRow == FLOOR_HEIGHT || currentColumn < 0 || currentColumn == FLOOR_WIDTH) {
            return false;
        }
        //check if already visited this tile
        if(memory[currentRow][currentColumn]){
            return false;
        }

        //set this tile as visited
        memory[currentRow][currentColumn] = true;

        //check if tile is staircase
        if(this.floor[currentRow][currentColumn].toString().equals("s")){
            return true; //found staircase
        }

        //check if tile is a wall
        if(floor[currentRow][currentColumn].toString().equals("W")){
            return false;
        }

        //check adjacent tiles
        boolean result =    (isFloorTraversable(currentRow + 1, currentColumn, memory) ||
                isFloorTraversable(currentRow - 1, currentColumn, memory) ||
                isFloorTraversable(currentRow, currentColumn + 1, memory) ||
                isFloorTraversable(currentRow, currentColumn - 1, memory));

        return result;
    }

    //DEBUG: prints the entire floor and shows where the DFS visited
    private void printFloor(int currR, int currC, boolean[][] memory){
        StringBuilder output = new StringBuilder();
        for(int r = 0; r < FLOOR_HEIGHT; r++){
            for(int c = 0; c < FLOOR_WIDTH; c++){
                if(r == currR && c == currC){
                    output.append('+');//player's location
                    continue;
                }

                if(floor[r][c].toString().equals("s")){
                    output.append('S');//staircase
                }
                else if(floor[r][c].toString().equals("t")){
                    output.append('^');//spiketip
                }
                else if(floor[r][c].toString().equals("W")){
                    output.append('X');//wall
                }
                else if(floor[r][c].toString().equals("T")){
                    if(memory[r][c]) output.append('*');
                    else output.append('.');//floor
                }
                else{
                    output.append('%');//anything else, typically enemies
                }
            }
            output.append('\n');
        }
        System.out.println(output.toString());
    }

    public void setTraversable() {
        boolean[][] memory = new boolean [FLOOR_HEIGHT][FLOOR_WIDTH];

        this.isTraversable = isFloorTraversable(playerRow, playerColumn, memory);
    }

    public boolean getTraversable() {
        return this.isTraversable;
    }
}