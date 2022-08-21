package DungeonCharacter.Enemy;

import SQL.SQLTables;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class EnemyFactory {

    private static EnemyFactory myInstance;
    private static SQLTables myEnemyTable;
    private static String myDKValues;
    private static String myTRGValues;
    /**
     * Constructor for EnemyFactory.
     */
    private EnemyFactory() {
        try {
            this.myEnemyTable = new SQLTables();
            this.myDKValues = this.myEnemyTable.extractDonkeyKongInfo();
            this.myTRGValues = this.myEnemyTable.extractTeamRocketInfo();
        } catch (SQLException e) {
        }
    }

    /**
     * Returns the singleton instance of EnemyFactory.
     * @return EnemyFactory
     */
    public static EnemyFactory getInstance() {
        if (myInstance == null) return new EnemyFactory();
        return myInstance;
    }
    /**
     * Returns a specified enemy.
     * @return Enemy
     */
    public Enemy createSpecificEnemy(final String theType) throws IOException {
        if(Objects.equals(theType, "DK")) {
            Enemy newDK = new DonkeyKong();
            setEnemyValues(newDK, "DK");
            return newDK;
        } else {
            Enemy newTRG = new TeamRocketGrunt();
            setEnemyValues(newTRG, "TRG");
            return newTRG;
        }
    }
    /**
     * Returns a random enemy.
     * @return Enemy
     */
    public static Enemy createEnemy() {
        Random rand = new Random();
        if (rand.nextInt(10) % 2 == 0) {
            try {
                return setEnemyValues(new DonkeyKong(), "DK");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Error creating Donkey Kong enemy!");
            }

        } else {
            try {
                return setEnemyValues(new TeamRocketGrunt(), "TRG");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Error creating Team Rocket Grunt enemy!");
            }
        }

        System.out.println("Enemy factory failed to create enemy object.");
        System.exit(0);
        return null;
    }


    /**
     * Sets the values of the enemy.
     * @param theEnemy Enemy to set values of.
     * @param theType String dictating the type of enemy.
     * @return Enemy
     */
    public static Enemy setEnemyValues( final Enemy theEnemy, final String theType){
        Enemy temp = theEnemy;
        Scanner valueScan = null;
        switch (theType) {
            case "DK":
                valueScan = new Scanner(myDKValues);
                temp.setEnemyValues(valueScan.nextLine(), valueScan.nextInt(), valueScan.nextInt(),
                        valueScan.nextInt(), valueScan.nextInt(), valueScan.nextInt(),
                        valueScan.nextInt(), valueScan.nextInt());
            case "TRG":
                valueScan = new Scanner(myTRGValues);
                temp.setEnemyValues(valueScan.nextLine(), valueScan.nextInt(), valueScan.nextInt(),
                        valueScan.nextInt(), valueScan.nextInt(), valueScan.nextInt(),
                        valueScan.nextInt(), valueScan.nextInt());
            default:
                break;
        }
        return temp;
    }

}
