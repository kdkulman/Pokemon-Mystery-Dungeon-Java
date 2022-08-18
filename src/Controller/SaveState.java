package Controller;

import java.io.Serializable;
import DungeonCharacter.Hero.*;
import FloorGenerator.Floor;

public class SaveState implements Serializable{
    public Hero hero;
    public Floor floor;

    public SaveState(final Floor floor){
        this.floor = floor;
        hero = floor.player;
    }
}