package Controller;

import java.io.Serializable;
import DungeonCharacter.Hero.*;
import FloorGenerator.Floor;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class SaveState implements Serializable{
    public Hero hero;
    public Floor floor;

    /**
     * Constructor for objects of class SaveState
     * @param theFloor the current floor
     */
    public SaveState(final Floor theFloor){
        this.floor = theFloor;
        hero = theFloor.myPlayer;
    }
}