package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import FloorGenerator.Floor;

public class SaveManager {

    public static void saveCurrentGameState(Floor floor) throws IOException{
        File saveFile = new File("save.ser");
        SaveState state = new SaveState(floor);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
        oos.writeObject(state);
        oos.close();
    }

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