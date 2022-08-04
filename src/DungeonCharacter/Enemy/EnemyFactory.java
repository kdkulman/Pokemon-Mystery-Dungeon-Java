package DungeonCharacter.Enemy;

import java.io.IOException;

public class EnemyFactory {

    public EnemyFactory() {

    }

    public Enemy createEnemy() {
        if(Math.random() * 100 % 2 == 0) {
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
