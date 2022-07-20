package DungeonCharacter;

public class Magikarp extends Hero {
    private double myBigAttackChance;

    Magikarp() {
        super("Magikarp", 60, 60,0, 1, 0,
                2, 5, null);
        this.myBigAttackChance = 0.1;
    }

    @Override
    public void specialAttack() {
        if(Math.random() < this.myBigAttackChance) {
            this.getTarget().takeDamage(this.getTarget().getHP());
            if(this.myBigAttackChance < 1) {
                this.myBigAttackChance += 0.05;
            }
            this.heal(this.getMaxHP());
        }
    }
}
