package DungeonCharacter;

public class Gallade extends Hero{
    private final double SELF_INFLICTION = 12.5;

    public Gallade() {
        super("Gallade", 100, 100,30, 20, 30,
                3, 15, null);
    }

    @Override
    public void specialAttack() {
        this.getTarget().takeDamage(this.getMySpecialAttack());
        this.takeDamage(SELF_INFLICTION);
    }
}
