import java.sql.*;
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "katyabelan9";
        String SQL =
                "SELECT pl.course_name, COUNT(*) / (MAX(month(pl.subscription_date)) - MIN(month(pl.subscription_date)) + 1) AS avg FROM PurchaseList pl GROUP BY pl.course_name;";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String avgValue = resultSet.getString("avg");
                System.out.println(courseName + " - " + avgValue);
            }
            connection.close();
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
