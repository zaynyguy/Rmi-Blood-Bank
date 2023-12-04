package blood_bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Blood_Bank_Implimentaion extends UnicastRemoteObject implements Blood_Bank_Remote_Interface {
    private Connection connection;

    public Blood_Bank_Implimentaion() throws RemoteException {
        try {
            // Establish the database connection
            String url = "jdbc:mariadb://localhost:3306/blood_bank";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    @Override
    public void createDonor(String donorName, String bloodType, int age) throws RemoteException {
        try {
            // Prepare the SQL statement for inserting a new donor
            String sql = "INSERT INTO donors (name, blood_type, age) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, donorName);
            statement.setString(2, bloodType);
            statement.setInt(3, age);

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Donor created: " + donorName);
            } else {
                System.out.println("Failed to create donor.");
            }
        } catch (SQLException e) {
            System.err.println("Error creating donor: " + e.getMessage());
        }
    }

    @Override
    public String getDonor(String donorName) throws RemoteException {
        try {
            // Prepare the SQL statement for retrieving a donor
            String sql = "SELECT * FROM donors WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, donorName);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String bloodType = resultSet.getString("blood_type");
                int age = resultSet.getInt("age");
                return "Donor: " + donorName + ", Blood Type: " + bloodType + ", Age: " + age;
            } else {
                return "Donor not found.";
            }
        } catch (SQLException e) {
            System.err.println("Error getting donor: " + e.getMessage());
            return "An error occurred while retrieving the donor.";
        }
    }

    @Override
    public List<String> getAllDonors() throws RemoteException {
        List<String> donorList = new ArrayList<>();
        try {
            // Prepare the SQL statement for retrieving all donors
            String sql = "SELECT * FROM donors";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String donorName = resultSet.getString("name");
                String bloodType = resultSet.getString("blood_type");
                int age = resultSet.getInt("age");
                donorList.add("Donor: " + donorName + ", Blood Type: " + bloodType + ", Age: " + age);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all donors: " + e.getMessage());
        }
        return donorList;
    }

    @Override
    public void updateDonor(String donorName, String bloodType, int age) throws RemoteException {
        try {
            // Prepare the SQL statement for updating a donor
            String sql = "UPDATE donors SET blood_type = ?, age = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bloodType);
            statement.setInt(2, age);
            statement.setString(3, donorName);

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Donor updated: " + donorName);
            } else {
                System.out.println("Donor not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating donor: " + e.getMessage());
        }
    }

    @Override
    public void deleteDonor(String donorName) throws RemoteException {
        try {
            // Prepare the SQL statement for deleting a donor
            String sql = "DELETE FROM donors WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, donorName);

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Donor deleted: " + donorName);
            } else {
                System.out.println("Donor not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting donor: " + e.getMessage());
        }
    }
}