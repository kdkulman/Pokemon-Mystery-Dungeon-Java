package DungeonCharacter;

import TileObjects.TileObject;

import java.awt.image.BufferedImage;

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

    public DungeonCharacter(final String THE_NAME, final int THE_HP, final int THE_MAX_HP, final int THE_DAMAGE_RANGE,
                     final int THE_ATTACK, final int THE_SPECIAL_ATTACK, final int THE_DEFENSE, final int THE_EVASION,
                     final BufferedImage THE_SPRITE){

        super(THE_SPRITE, THE_NAME,"");
        this.myMaxHP = THE_MAX_HP;
        this.myHP = THE_HP;
        this.myDamageRange = THE_DAMAGE_RANGE;
        this.myAttack = THE_ATTACK;
        this.mySpecialAttack = THE_SPECIAL_ATTACK;
        this.myDefense = THE_DEFENSE;
        this.myEvasion = THE_EVASION;
        this.myTarget = null;
        this.myBattleStatus = true;
    }

    public int getHP() {
        return this.myHP;
    }

    public int getDamageRange() {
        return this.myDamageRange;
    }

    public double getAttack() {
        return this.myAttack;
    }

    public int getDefense() {
        return this.myDefense;
    }

    public void setTarget(final DungeonCharacter THE_ENEMY) {
        this.myTarget = THE_ENEMY;
    }

    public void attack() {
        this.myTarget.takeDamage(this.myAttack);
    }

    public void specialAttack() {
        this.myTarget.takeDamage(this.mySpecialAttack);
    }

    public void takeDamage(final int THE_DAMAGE) {
        int finalDamage = THE_DAMAGE - this.myDefense;
        if (finalDamage < 0) {
            finalDamage = 0;
        }
        if(characterIsHit()) {
            this.myHP -= finalDamage;
        }
    }


    private boolean characterIsHit() {
        if (this.myEvasion < Math.random()) {
            return false;
        } else {
            return true;
        }
    }

    public void heal(final int THE_HEALING) {
        if(THE_HEALING + this.myHP > this.myMaxHP) {
            this.myHP = this.myMaxHP;
        } else {
            this.myHP += THE_HEALING;
        }
    }

    public void setBattleStatus(boolean THE_SETTING) {
        this.myBattleStatus = THE_SETTING;
    }

    public int getMaxHP() {
        return this.myMaxHP;
    }

    public boolean getBattleStatus() {
        return this.myBattleStatus;
    }

    public DungeonCharacter getTarget() {
        return this.myTarget;
    }

    public int getMySpecialAttack() {
        return this.mySpecialAttack;
    }

}
