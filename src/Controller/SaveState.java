package Controller;

import java.io.Serializable;
import DungeonCharacter.Hero.*;
import FloorGenerator.Floor;

public class SaveState implements Serializable{
    public Hero hero;
    public Floor floor;

    public SaveState(final Floor theFloor){
        this.floor = theFloor;
        hero = theFloor.myPlayer;
    }
}