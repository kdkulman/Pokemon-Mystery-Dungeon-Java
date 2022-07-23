package DungeonCharacter;

public class Magikarp extends Hero {
    private double myBigAttackChance;

    Magikarp() {
        super("DungeonCharacter.Magikarp");
//        this.myHP = 60;
        this.myAttack = 1;
        this.myDefense = 1;
        this.myEvasion = 0.05;
        this.myBigAttackChance = 0.1;
    }

    @Override
    public void specialAttack() {
        if(Math.random() < this.myBigAttackChance) {
            this.myTarget.takeDamage(this.myTarget.getHP());
            if(this.myBigAttackChance < 1) {
                this.myBigAttackChance += 0.05;
            }
//            this.myHP = 60;
        }
    }
}
