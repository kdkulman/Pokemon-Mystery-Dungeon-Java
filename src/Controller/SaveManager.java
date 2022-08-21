package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import FloorGenerator.Floor;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class SaveManager {
    /**
     * method to save current game state
     * @param theFloor the current floor
     * @throws IOException
     */
    public static void saveCurrentGameState(final Floor theFloor) throws IOException{
        File myFile = new File("save.ser");
        SaveState myState = new SaveState(theFloor);
        ObjectOutputStream myOOS = new ObjectOutputStream(new FileOutputStream(myFile));
        myOOS.writeObject(myState);
        myOOS.close();
    }

    /**
     * method to load saved game state
     * @return the saved game state
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static SaveState getSavedGameState() throws IOException, ClassNotFoundException{
        File saveFile = new File("save.ser");
        if(saveFile.isFile() && saveFile.canRead()){
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(saveFile));
            SaveState state = (SaveState) input.readObject();

            input.close();

            return state;
        }

        //Something went wrong loading save
        System.out.println("Error loading save.");
        return null;
    }

}