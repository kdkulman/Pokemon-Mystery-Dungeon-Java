package DungeonCharacter;

public abstract class DungeonCharacter extends com.TileObject {

    protected double myHp;
    protected double myDamageRange;
    protected double myAttack;
    protected double mySpecialAttack;
    protected int myDefense;
    protected double myEvasion;
    protected DungeonCharacter myTarget;
    protected boolean myBattleStatus;

    public void setTarget(DungeonCharacter THE_ENEMY) {
        this.myTarget = THE_ENEMY;
    }

    public void attack() {}

    public void specialAttack() {}

    public void takeDamage(double damage) {
        this.myDamageRange = damage;
    }

    private boolean characterIsHit() {
        return myBattleStatus;
    }

    public String getName() {
        return super.getName();
    }

    public double getHP() {
        return myHp;
    }

    public int getDamage() {
        return getDamage();
    }

    public double getAttack() {
        return myAttack;
    }

    public int getDefense() {
        return myDefense;
    }
}
