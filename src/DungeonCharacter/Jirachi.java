package DungeonCharacter;

public class Jirachi extends Hero{
    Jirachi() {
        super("Jirachi", 75, 75,20, 50, 0,
                2, 5, null);
    }

    @Override
    public void specialAttack() {
        this.getTarget().takeDamage(this.getTarget().getHP() / 2);
    }
}