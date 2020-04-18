package Java2_04;

import java.sql.*;

public class Ex1_01 {
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
            String select = "SELECT*FROM books;";
            System.out.println("The SQL statement is: " + select + "\n");
            ResultSet set = stmt.executeQuery(select);

            while (set.next()) {
                System.out.println(set.getInt("id") + ", " + set.getString("title") +", " + set.getString("author") + ", " + set.getDouble("price") + ", " + set.getInt("qty") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
