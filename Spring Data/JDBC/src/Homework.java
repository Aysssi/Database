import java.sql.*;

import java.util.Properties;
import java.util.Scanner;

public class Homework {
    private static final String CONNECTION_MINIONS_TABLE = "jdbc:mysql://localhost:3306/minions_db";
    private Connection connection;

    private   Scanner scanner ;

    public Homework() {
        this.scanner = new Scanner(System.in);
    }

    public void setConnection(String user, String password) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user",user);
        properties.setProperty("password",password);

        connection = DriverManager.getConnection(CONNECTION_MINIONS_TABLE, properties);
    }

    public void getVillainsNamesEx2() throws SQLException {
         String query =" SELECT v.name, COUNT(DISTINCT MV.minion_id) AS minions_count FROM villains AS v" +
        " JOIN minions_villains mv on v.id = mv.villain_id" +
                " GROUP BY mv.villain_id" +
                " HAVING minions_count > 15" +
                " ORDER BY minions_count";


        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.printf("%s %d%n",
                    resultSet.getString("name"),
                     resultSet.getInt(2));

        }
    }

    public void getMinionsNamesEx3() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter villain id");
        int villainId = Integer.parseInt(scanner.nextLine());

        String villainName = getEntityNameById(villainId, "villains");
        if (villainName == null) {
            System.out.printf("No villain with id %d",villainId);
            return;
        }
        System.out.printf("Villain: %s%n",villainName);

       String query = "SELECT m.name, m.age FROM minions as m\n" +
               "JOIN minions_db.minions_villains mv on m.id = mv.minion_id\n" +
               "WHERE mv.villain_id = ?";

       PreparedStatement preparedStatement = connection.prepareStatement(query);
       preparedStatement.setInt(1,villainId);

       ResultSet resultSet = preparedStatement.executeQuery();
       int counter = 1;
       while (resultSet.next()){
           System.out.printf("%d.  %s %d %n",
                   counter++, resultSet.getString("name"),
                   resultSet.getInt("age"));
       }

    }
    private String  getEntityNameById(int entityId, String tableName) throws SQLException {
        String query = String.format("SELECT name FROM %s WHERE id = ?",tableName );

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, entityId);

        ResultSet resultSet = preparedStatement.executeQuery();

      return  resultSet.next() ? resultSet.getString("name") : null;
    }


    public void addMinionsEx4() throws SQLException {
        System.out.println("Enter minions info: name, age, town name");
        String [] minionsInfo = scanner.nextLine().split("\\s+");
        String minionsName = minionsInfo[0];
        int age = Integer.parseInt(minionsInfo[1]);
        String townName = minionsInfo[2];


        int townId = getEntityIdByName(townName,"towns");

        if (townId < 0){
            insertEntityInTown(townName);
        }
     }

    private void insertEntityInTown(String townName) throws SQLException {
        String query = ("INSERT INTO towns(name) value (?)");

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,townName);
        preparedStatement.execute();
    }

    private int getEntityIdByName(String entityName, String tableName) throws SQLException {
        String query = String.format("SELECT id FROM %s WHERE name = ? ",tableName);

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,entityName);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next() ? resultSet.getInt(1) : -1;

    }

    public void changeTownNameCasingEx5() throws SQLException {
        System.out.println("Enter country name");
        String  countryName = scanner.nextLine();

        String query = "UPDATE towns SET name = UPPER(name) WHERE country = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,countryName);

        int townsAffected = preparedStatement.executeUpdate();
        System.out.printf("%d town names were affected.%n",townsAffected);

    }
}
