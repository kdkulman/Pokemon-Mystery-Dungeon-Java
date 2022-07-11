public class DungeonCharacter {
    protected String myName;
    protected double myHP;
    protected double myDamageRange;
    protected double myAttack;
    protected double mySpecialAttack;
    protected int myDefense;
    protected double myEvasion;
    protected DungeonCharacter myTarget;
    protected boolean myBattleStatus;

    DungeonCharacter(final String THE_NAME) {
        this.myName = THE_NAME;
        this.myHP = 0;
        this.myDamageRange = 0;
        this.myAttack = 0;
        this.mySpecialAttack = 0;
        this.myDefense = 0;
        this.myEvasion = 0.05;
        this.myTarget = null;
        this.myBattleStatus = true;
    }

    public String getName() {
        return this.myName;
    }

    public double getHP() {
        return this.myHP;
    }

//    public int getDamage() {
//        return this.myDamage;
//    }

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
        this.myTarget.takeDamage(this.myAttack * this.myDamageRange);
    }

    public void specialAttack() {
        this.myTarget.takeDamage(this.mySpecialAttack * this.myDamageRange);
    }

    public void takeDamage(final double THE_DAMAGE) {
        double finalDamage = THE_DAMAGE - this.myDefense;
        if (finalDamage < 0) {
            finalDamage = 0;
        }
        if(characterIsHit()) {
            this.myHP -= finalDamage;
        }
    }

    private boolean characterIsHit() {
        if (Math.random() < this.myEvasion) {
            return false;
        } else {
            return true;
        }
    }

}
