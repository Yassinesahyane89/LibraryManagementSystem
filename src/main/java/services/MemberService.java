package services;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberService {

    public boolean findMemberNumber(String memberNumber) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.err.println("Failed to connect to the database.");
            return false;
        }

        try {
            String sql = "SELECT COUNT(*) FROM borrowers WHERE memberNumber = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, memberNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return true;
            } else {
                System.out.println("Member number not found in the database.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
