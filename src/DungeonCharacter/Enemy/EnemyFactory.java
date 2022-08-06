package DungeonCharacter.Enemy;

import SQL.SQLTables;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class EnemyFactory {

    public EnemyFactory() {

    }

    public static Enemy createEnemy() {
        Random rand = new Random();
        if(rand.nextInt(10) % 2 == 0) {
            try {
                return new DonkeyKong();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Error creating Donkey Kong enemy!");
            }

        } else {
            try {
                return new TeamRocketGrunt();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Error creating Team Rocket Grunt enemy!");
            }
        }

        System.out.println("Enemy factory failed to create enemy object.");
        System.exit(0);
        return null;
    }


}
