import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = getSessionFactory();

        Session session = sessionFactory.openSession();
        Course course = session.get(Course.class, 1);
        System.out.println(course.getName() + ": количество студентов - " + course.getStudentsCount());

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
    private static SessionFactory getSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}