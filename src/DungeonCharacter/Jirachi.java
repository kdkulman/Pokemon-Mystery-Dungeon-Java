package DungeonCharacter;

public class Jirachi extends Hero{
    Jirachi() {
        super();
        this.myHP = 75;
        this.myDamageRange = 0.20;
        this.myAttack = 50;
        this.mySpecialAttack = this.myTarget.getHP() / 2;
        this.myDefense = 2;
        this.myEvasion = 0.05;
    }
}