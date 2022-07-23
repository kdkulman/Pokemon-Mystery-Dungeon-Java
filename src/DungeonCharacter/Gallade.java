package DungeonCharacter;

public class Gallade extends Hero{
    private final double SELF_INFLICTION = 12.5;

    public Gallade() {
        super();
        this.myHP = 100;
        this.myDamageRange = 0.30;
        this.myAttack = 20;
        this.mySpecialAttack = this.myAttack * 1.5;
        this.myDefense = 3;
        this.myEvasion = 0.15;
    }

    @Override
    public void specialAttack() {
        this.myTarget.takeDamage(this.mySpecialAttack);
        this.takeDamage(SELF_INFLICTION);
    }
}
