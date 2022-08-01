package SQL;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTables {

    private SQLiteDataSource enemyTable;
    private Connection connection;
    private Statement statement;
    private String query;
    private int returnValue;

    public SQLTables() throws SQLException {
        createEnemyConnection();
        createEnemyTable();
        fillEnemyTable();
    }

    private void createEnemyTable() {
        this.enemyTable = new SQLiteDataSource();

         this.query = "CREATE TABLE IF NOT EXISTS enemyDB" +
                "  ( ID INTEGER PRIMARY KEY, " +
                "    NAME TEXT NOT NULL )";

        try {
            returnValue = statement.executeUpdate( query );
            System.out.println("executeUpdate() returned " + returnValue);
        } catch (SQLException e) {
            System.out.println("Error creating enemy table");
            System.exit(0);
        }
        System.out.println("Enemy table created successfully!");

    }

    private void createEnemyConnection() {
        this.enemyTable.setUrl("jdbc:sqlite:enemy.db");

        try {
            this.connection =  enemyTable.getConnection();
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error creating connection.");
            System.exit(0);
        }
        System.out.println("Connection created successfully!");
    }

    private void fillEnemyTable() {

        this.query = "INSERT INTO enemyDB (NAME, HP, MAXHP, DAMAGERANGE, ATTACK, SPECIALATTACK, DEFENSE, EVASION, SPRITE ) " +
                     "VALUES " +
                     "('Team Rocket Grunt',100,100,0,10,20,5,5,'TeamRocketGrunt.png')" +
                     "('Donkey Kong',100,100,0,10,20,5,5,'DonkeyKong.png')";

        try {
            returnValue = statement.executeUpdate( query );
            System.out.println("executeUpdate() returned " + returnValue);
        } catch (SQLException e) {
            System.out.println("Error filling values into enemy table");
            System.exit(0);
        }
        System.out.println("Values filled into enemy table successfully!");
    }
}
