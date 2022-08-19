package DungeonCharacter.Enemy;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EnemyFactoryTest {

    @Test
    void createEnemy() throws IOException {
        EnemyFactory enemyFactory = EnemyFactory.getInstance();
        Enemy[] eArray = new Enemy[100];
        for(int i = 0; i < eArray.length; i++) {
            eArray[i] = enemyFactory.createEnemy();
        }
        for(int i = 0; i < eArray.length; i++) {
            assertNotNull(eArray[i]);
        }
        for(int i = 0; i < eArray.length; i++) {
            System.out.println(eArray[i].getName());
            System.out.println(eArray[i].getHP());
            System.out.println(eArray[i].getAttack());
            System.out.println(eArray[i].getDefense());
        }
    }
}