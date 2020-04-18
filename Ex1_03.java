package Java2_04;

import java.sql.*;

public class Ex1_03 {
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
            String select2 = "SELECT title, author, price, qty FROM books WHERE author = 'CodeLean VN' OR price >= 30 ORDER BY price DESC, id ASC ;";
            System.out.println("The SQL statement is: " + select2 + "\n");
            ResultSet set2 = stmt.executeQuery(select2);

            while (set2.next()) {
                System.out.println(set2.getString("title") +", " + set2.getString("author") + ", " + set2.getDouble("price") + ", " + set2.getInt("qty") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
