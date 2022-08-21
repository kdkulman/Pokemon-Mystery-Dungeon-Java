package DungeonCharacter;
import TileObjects.TileObject;

import java.awt.image.BufferedImage;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public abstract class DungeonCharacter extends TileObject {
    private int myHP;
    private int myMaxHP;
    private int myDamageRange;
    private int myAttack;
    private int mySpecialAttack;
    private int myDefense;
    private int myEvasion;
    private DungeonCharacter myTarget;
    private boolean myBattleStatus;

    /**
     * Constructor for DungeonCharacter
     * @param theName name of the character
     * @param theHP health of the character
     * @param theMaxHP maximum health of the character
     * @param theDamageRange damage range of the character
     * @param theAttack attack of the character
     * @param theSpecialAttack special attack of the character
     * @param theDefense defense of the character
     * @param theEvasion evasion of the character
     */
    public DungeonCharacter(final String theName, final int theHP, final int theMaxHP, final int theDamageRange,
                     final int theAttack, final int theSpecialAttack, final int theDefense, final int theEvasion){

        super(theName,"", true);
        this.myMaxHP = theMaxHP;
        this.myHP = theHP;
        this.myDamageRange = theDamageRange;
        this.myAttack = theAttack;
        this.mySpecialAttack = theSpecialAttack;
        this.myDefense = theDefense;
        this.myEvasion = theEvasion;
        this.myTarget = null;
        this.myBattleStatus = true;
    }

    /**
     * Default constructor for DungeonCharacter for testings
     * @param THE_NAME the name of the DungeonCharacter
     */
    public DungeonCharacter(final String THE_NAME) {
        super(THE_NAME, "", true);
        this.myMaxHP = 1;
        this.myHP = 1;
        this.myDamageRange = 1;
        this.myAttack = 1;
        this.mySpecialAttack = 1;
        this.myDefense = 1;
        this.myEvasion = 1;
        this.myTarget = null;
        this.myBattleStatus = true;
    }

    /**
     * clears myTarget value
     */
    public void clearTarget() {
        this.myTarget = null;
    }

    /**
     * HP getter
     * @return int HP
     */
    public int getHP() {
        return this.myHP;
    }

    /**
     * Damage range getter
     * @return int damage range
     */
    public int getDamageRange() {
        return this.myDamageRange;
    }

    /**
     * Attack getter
     * @return int attack
     */
    public double getAttack() {
        return this.myAttack;
    }

    /**
     * Defense getter
     * @return int defense
     */
    public int getDefense() {
        return this.myDefense;
    }

    /**
     * Set target method
     * @param THE_ENEMY target
     */
    public void setTarget(final DungeonCharacter THE_ENEMY) {
        this.myTarget = THE_ENEMY;
    }

    /**
     * Attack method
     * @return string damage
     */
    public String attack() {
        String damage = this.myTarget.takeDamage(this.myAttack);
        String message = this.getName() + " did " + damage + " damage to " + this.myTarget.getName() + "!" +
                "(" + this.myTarget.getHP() + " HP remaining)";
        return message;
    }

    /**
     * Special attack method
     * @return String damage
     */
    public String specialAttack() {
        String damage = this.myTarget.takeDamage(this.mySpecialAttack);
        String message = this.getName() + " did " + damage + " special damage to " + this.myTarget.getName() + "!";
        return message;
    }

    /**
     * take damage method
     * @param theDamage to be taken
     * @return string damage
     */
    public String takeDamage(final int theDamage) {
        int finalDamage = theDamage - this.myDefense;
        if (finalDamage < 0) {
            finalDamage = 0;
        }
        if(characterIsHit()) {
            this.myHP -= finalDamage;
            return String.valueOf(finalDamage);
        } else {
            return this.getName() + " evaded the attack!";
        }

    }

    /**
     * Checks if character is hit
     * @return boolean
     */
    private boolean characterIsHit() {
        if (this.myEvasion < Math.random()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Heal method
     * @param THE_HEALING amount to be healed
     * @return string heal message
     */
    public String heal(final int THE_HEALING) {
        String message = "";
        int amount = 0;
        if(THE_HEALING + this.myHP > this.myMaxHP) {
            this.myHP = this.myMaxHP;
            amount += this.myMaxHP - this.myHP;
        } else {
            this.myHP += THE_HEALING;
            amount += THE_HEALING;
        }
        message += this.getName() + " healed for " + amount + " HP! " + "(" + this.getHP() + " HP remaining)";
        return message;
    }

    /**
     * sets if character is able to battle NYI
     * @param THE_SETTING true or false
     */
    public void setBattleStatus(boolean THE_SETTING) {
        this.myBattleStatus = THE_SETTING;
    }

    /**
     * maxHP getter
     * @return int maxHP
     */
    public int getMaxHP() {
        return this.myMaxHP;
    }

    /**
     * battle status getter
     * @return boolean battle status
     */
    public boolean getBattleStatus() {
        return this.myBattleStatus;
    }

    /**
     * target getter
     * @return DungeonCharacter target
     */
    public DungeonCharacter getTarget() {
        return this.myTarget;
    }

    /**
     * special attack getter
     * @return int special attack
     */
    public int getMySpecialAttack() {
        return this.mySpecialAttack;
    }

    /**
     * method for manual setting of enemy values (enemy SQL tables)
     * @param THE_NAME name of enemy
     * @param THE_HP HP of enemy
     * @param THE_MAX_HP max HP of enemy
     * @param THE_DAMAGE_RANGE damage range of enemy
     * @param THE_ATTACK attack of enemy
     * @param THE_SPECIAL_ATTACK special attack of enemy
     * @param THE_DEFENSE defense of enemy
     * @param THE_EVASION evasion of enemy
     */
    public void setEnemyValues(final String THE_NAME, final int THE_HP, final int THE_MAX_HP, final int THE_DAMAGE_RANGE,
                     final int THE_ATTACK, final int THE_SPECIAL_ATTACK, final int THE_DEFENSE, final int THE_EVASION){
        this.myMaxHP = THE_MAX_HP;
        this.myHP = THE_HP;
        this.myDamageRange = THE_DAMAGE_RANGE;
        this.myAttack = THE_ATTACK;
        this.mySpecialAttack = THE_SPECIAL_ATTACK;
        this.myDefense = THE_DEFENSE;
        this.myEvasion = THE_EVASION;
    }


}
