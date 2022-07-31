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
        createEnemyTable();
    }

    private void createEnemyTable() {
        enemyTable = new SQLiteDataSource();
        enemyTable.setUrl("jdbc:sqlite:enemy.db");

        try {
            connection = enemyTable.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Error creating enemy table");
            System.exit(0);
        }
        System.out.println("Table created successfully!");

        query = "CREATE TABLE IF NOT EXISTS enemyDB" +
                "  ( ID INTEGER PRIMARY KEY, " +
                "    NAME TEXT NOT NULL )";

        try {
            returnValue = statement.executeUpdate( query );
            System.out.println("executeUpdate() returned " + returnValue);
        } catch (SQLException e) {
            System.out.println("Error creating enemy table");
            System.exit(0);
        }

        query = "INSERT INTO enemyDB (NAME, HP, MAXHP, DAMAGERANGE, ATTACK, SPECIALATTACK, DEFENSE, EVASION, SPRITE ) " +
                "VALUES ('Team Rocket Grunt',100,100,0,10,20,5,5,'TeamRocketGrunt.png')";

        try {
            returnValue = statement.executeUpdate( query );
            System.out.println("executeUpdate() returned " + returnValue);
        } catch (SQLException e) {
            System.out.println("Error creating enemy table");
            System.exit(0);
        }

    }
}
