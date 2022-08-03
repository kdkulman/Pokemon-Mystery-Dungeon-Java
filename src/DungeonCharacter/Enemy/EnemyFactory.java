package DungeonCharacter.Enemy;

public class EnemyFactory {
    private int myCounter;

    public EnemyFactory() {

    }

    public Enemy createEnemy() {
        if(Math.random() * 100 % 2 == 0) {
            return new DonkeyKong();
            this.myCounter++;
        } else {
            return new TeamRocketGrunt();
            this.myCounter++;
        }
    }


}
