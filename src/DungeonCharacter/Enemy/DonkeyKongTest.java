package DungeonCharacter.Enemy;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DonkeyKongTest {

    @Test
    void DonkeyKongTest() throws IOException {
        EnemyFactory testFactory = EnemyFactory.getInstance();
        assertNotNull(testFactory);
        Enemy testDK = testFactory.createSpecificEnemy("DK");
        assertNotNull(testDK);
        System.out.print("Health Check: ");
        assertEquals(50, testDK.getHP());
        assertEquals(20, testDK.getAttack());
        assertEquals(5, testDK.getDefense());
    }

}