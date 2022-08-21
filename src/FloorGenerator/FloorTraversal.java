package FloorGenerator;

import TileObjects.TileObject;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class FloorTraversal {
    private final TileObject[][] myFloor;
    private boolean myTraversableBoolean;
    private final int myPlayerRow;
    private final int myPlayerColumn;
    private final int myFloorHeight;
    private final int myFloorWidth;

    /**
     * Constructor for objects of class FloorTraversal
     * @param theFloor
     * @param thePlayerRow
     * @param myPlayerColumn
     */
    public FloorTraversal(final TileObject[][] theFloor, final int thePlayerRow, final int myPlayerColumn) {
        this.myFloor = theFloor;
        myFloorHeight = theFloor.length;
        myFloorWidth = theFloor[0].length;
        this.myPlayerColumn = myPlayerColumn;
        this.myPlayerRow = thePlayerRow;
        this.setTraversable();
    }

    private boolean isFloorTraversable(final int currentRow, final int currentColumn, final boolean[][] memory){
        //check if out of bounds
        if(currentRow < 0  || currentRow == myFloorHeight || currentColumn < 0 || currentColumn == myFloorWidth) {
            return false;
        }
        //check if already visited this tile
        if(memory[currentRow][currentColumn]){
            return false;
        }

        //set this tile as visited
        memory[currentRow][currentColumn] = true;

        //check if tile is staircase
        if(this.myFloor[currentRow][currentColumn].toString().equals("s")){
            return true; //found staircase
        }

        //check if tile is a wall
        if(myFloor[currentRow][currentColumn].toString().equals("W")){
            return false;
        }

        //check adjacent tiles
        boolean result =    (isFloorTraversable(currentRow + 1, currentColumn, memory) ||
                isFloorTraversable(currentRow - 1, currentColumn, memory) ||
                isFloorTraversable(currentRow, currentColumn + 1, memory) ||
                isFloorTraversable(currentRow, currentColumn - 1, memory));

        return result;
    }

    //DEBUG: prints the entire myFloor and shows where the DFS visited
    private void printFloor(final int currR, final int currC, final boolean[][] memory){
        StringBuilder output = new StringBuilder();
        for(int r = 0; r < myFloorHeight; r++){
            for(int c = 0; c < myFloorWidth; c++){
                if(r == currR && c == currC){
                    output.append('+');//player's location
                    continue;
                }

                if(myFloor[r][c].toString().equals("s")){
                    output.append('S');//staircase
                }
                else if(myFloor[r][c].toString().equals("t")){
                    output.append('^');//spiketip
                }
                else if(myFloor[r][c].toString().equals("W")){
                    output.append('X');//wall
                }
                else if(myFloor[r][c].toString().equals("T")){
                    if(memory[r][c]) output.append('*');
                    else output.append('.');//myFloor
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
        boolean[][] memory = new boolean [myFloorHeight][myFloorWidth];

        this.myTraversableBoolean = isFloorTraversable(myPlayerRow, myPlayerColumn, memory);
    }

    public boolean getMyTraversableBoolean() {
        return this.myTraversableBoolean;
    }
}