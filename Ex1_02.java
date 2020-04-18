package Java2_04;

import java.sql.*;

public class Ex1_02 {
    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?" +
                                "&serverTimezone=UTC" +
                                "&useSSL=false" +
                                "&allowPublicKeyRetrieval=true",
                        "root",
                        ""
                );
                Statement stmt = connection.createStatement();
        ) {
            String select1 = "SELECT title, price FROM books WHERE author = 'CodeLean VN';";
            System.out.println("The SQL statement is: " + select1 + "\n");
            ResultSet set1 = stmt.executeQuery(select1);

            while (set1.next()) {
                System.out.println(set1.getString("title") + ", " + set1.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
