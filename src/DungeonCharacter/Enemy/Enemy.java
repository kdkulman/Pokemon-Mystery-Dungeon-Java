package DungeonCharacter.Enemy;

import DungeonCharacter.DungeonCharacter;
import java.sql.SQLException;

import SQL.SQLTables;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public abstract class Enemy extends DungeonCharacter {
    private final double MY_CHANCE_TO_HEAL = 0.05;
    private final double MY_CHANCE_TO_SPECIAL = 0.10;
    private final int MY_HEALING_AMOUNT = 10;

    /**
     * Constructor for objects of class Enemy
     * @param theName The name of the enemy
     */
    public Enemy(final String theName) {
        super(theName);
        setMyEnemyStatus(true);
    }
    /**
     * Enemy decision method of whether to attack, heal, or special attack
     * @return message indicating enemy decision
     */
    public String enemyDecision() {
        String message = "";
        double chance = Math.random();

        if(MY_CHANCE_TO_HEAL > chance) {
            System.out.println("Enemy is healing.");
            message += this.heal(MY_HEALING_AMOUNT);
        } else if(MY_CHANCE_TO_SPECIAL > chance && MY_CHANCE_TO_HEAL < chance) {
            System.out.println("Enemy is special attacking.");
            message += this.specialAttack();
        } else {
            System.out.println("Enemy is attacking.");
            message += this.attack();
        }
        return message;
    }

}
