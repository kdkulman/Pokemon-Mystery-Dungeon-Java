package SQL;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class SQLTables {
    private SQLiteDataSource myEnemyTable;
    private Connection myConnection;
    private Statement myStatement;
    private String myQuery;
    private int myReturnValue;
    private ResultSet myResultSet;

    /**
     * Constructor for objects of class SQLTables
     * @throws SQLException if there is an error with the SQL construction
     */
    public SQLTables() throws SQLException {
        createEnemyTable();
        fillEnemyTable();
    }

    /**
     * Creates the enemy table in the database
     * @Throws SQLException if there is an error with table creation
     */
    private void createEnemyTable() {
        this.myEnemyTable = new SQLiteDataSource();

         this.myQuery = "CREATE TABLE enemyDB (" +
                      "NAME TEXT PRIMARY KEY," +
                      "HP TEXT NOT NULL," +
                      "MAXHP TEXT NOT NULL," +
                      "DAMAGERANGE TEXT NOT NULL," +
                      "ATTACK TEXT NOT NULL," +
                      "SPECIALATTACK TEXT NOT NULL," +
                      "DEFENSE TEXT NOT NULL," +
                      "EVASION TEXT NOT NULL);";

        try {
            this.myConnection =  myEnemyTable.getConnection();
            this.myStatement = myConnection.createStatement();
            myReturnValue = myStatement.executeUpdate(myQuery);
            System.out.println("executeUpdate() returned " + myReturnValue);
        } catch (SQLException e) {
            System.out.println("Error creating enemy table");
            System.exit(0);
        }
        System.out.println("Enemy table created successfully!");

    }

    /**
     * Fills the enemy table with data
     */
    private void fillEnemyTable() {

        this.myQuery = "INSERT INTO 'enemyDB' " +
                "('NAME','HP','MAXHP','DAMAGERANGE','ATTACK','SPECIALATTACK','DEFENSE','EVASION') VALUES" +
                "('DONKEY KONG', '70','70','0','25','10','5','5')," +
                "('TEAM ROCKET GRUNT', '50','50','0','20','10','6','5');";

        try {
            myReturnValue = myStatement.executeUpdate(myQuery);
            System.out.println("executeUpdate() returned " + myReturnValue);
        } catch (SQLException e) {
            System.out.println("Error filling values into enemy table");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        System.out.println("Values filled into enemy table successfully!");
    }

    /**
     * Gets the Donkey Kong stats from the database
     * @return Donkey Kong stats
     */
    public String extractDonkeyKongInfo() {
        String result = "";
        //this.query = "SELECT * FROM 'enemyDB';";
        this.myQuery = "SELECT * FROM enemyDB LIMIT 1 OFFSET 0";
        //this.query = "SELECT rowid, * FROM enemyDB WHERE NAME = 'Donkey Kong'";

        try {
            this.myResultSet = this.myStatement.executeQuery(this.myQuery);
            while(this.myResultSet.next()) {
                result += this.myResultSet.getString("NAME") + "\n";
                result += this.myResultSet.getString("HP") + "\n";
                result += this.myResultSet.getString("MAXHP") + "\n";
                result += this.myResultSet.getString("DAMAGERANGE") + "\n";
                result += this.myResultSet.getString("ATTACK") + "\n";
                result += this.myResultSet.getString("SPECIALATTACK") + "\n";
                result += this.myResultSet.getString("DEFENSE") + "\n";
                result += this.myResultSet.getString("EVASION");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving Donkey Kong info from table.");
            System.out.println(e.getMessage());
            System.exit(0);
        }

        return result;
    }

    /**
     * Gets the Team Rocket Grunt stats from the database
     * @return Team Rocket Grunt stats
     */
    public String extractTeamRocketInfo() {
        String result = "";
        this.myQuery = "SELECT * FROM enemyDB LIMIT 1 OFFSET 1";

        try {
            this.myResultSet = this.myStatement.executeQuery(this.myQuery);
            while(this.myResultSet.next()) {
                result += this.myResultSet.getString("NAME") + "\n";
                result += this.myResultSet.getString("HP") + "\n";
                result += this.myResultSet.getString("MAXHP") + "\n";
                result += this.myResultSet.getString("DAMAGERANGE") + "\n";
                result += this.myResultSet.getString("ATTACK") + "\n";
                result += this.myResultSet.getString("SPECIALATTACK") + "\n";
                result += this.myResultSet.getString("DEFENSE") + "\n";
                result += this.myResultSet.getString("EVASION");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error retrieving Team Rocket Grunt info from table.");
            System.exit(0);
        }

        return result;
    }
}
