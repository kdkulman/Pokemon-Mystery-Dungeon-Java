package DungeonCharacter.Enemy;

import DungeonCharacter.DungeonCharacter;
import java.sql.SQLException;

import SQL.SQLTables;

public class Enemy extends DungeonCharacter {
    private final double MY_CHANCE_TO_HEAL = 0.05;

    public Enemy(final String THE_NAME) {
        super(THE_NAME);
        setMyEnemyStatus(true);
    }

    public String enemyDecision() {
        String message = "";

        if(MY_CHANCE_TO_HEAL > Math.random()) {
            System.out.println("Enemy is healing.");
            message += this.getName() + " healed!";
            this.heal(10);
        } else {
            System.out.println("Enemy is attacking.");
            message += this.getName() + " attacked " + this.getTarget() + "!";
            this.attack();
        }
        return message;
    }

}
