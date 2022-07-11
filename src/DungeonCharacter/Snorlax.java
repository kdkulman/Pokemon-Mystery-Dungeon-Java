package DungeonCharacter;

public class Snorlax extends Hero{
    private int mySleepCounter;

    Snorlax() {
        super("DungeonCharacter.Snorlax");
        this.myHP = 200;
        this.myDamageRange = 0.15;
        this.myAttack = 20;
        this.mySpecialAttack = 0;
        this.myDefense = 10;
        this.myEvasion = 0.05;
        this.mySleepCounter = 0;
    }

    @Override
    public void specialAttack() {
        this.myHP = 200;
        this.myBattleStatus = false;
    }

    public void sleepCounter() {
        if(!this.myBattleStatus) {
            if(this.mySleepCounter == 2) {
                this.mySleepCounter = 0;
                this.myBattleStatus = true;
            } else {
                this.mySleepCounter++;
            }
        }
    }
}