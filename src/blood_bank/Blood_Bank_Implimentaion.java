package blood_bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Blood_Bank_Implimentaion extends UnicastRemoteObject implements Blood_Bank_Remote_Interface {
    DbConnector con;

    public Blood_Bank_Implimentaion() throws RemoteException {
        con = new DbConnector();
    }

    @Override
    public void createDonor(String donorName, String bloodType, double blood_quantity) throws RemoteException {
        if (isValidBloodType(bloodType)) {
            try {
                // Prepare the SQL statement for inserting a new donor
                String sql = "insert into bloodbankmanagment(name,bloodgroup,quantity) values (?,?,?)";
                PreparedStatement statement = con.mkDataBase().prepareStatement(sql);
                statement.setString(1, donorName);
                statement.setString(2, bloodType);
                statement.setDouble(3, blood_quantity);

                // Execute the SQL statement
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Donor created: " + donorName);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create donor.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error creating donor: " + e.getMessage());
            }
        
        }
        else {
            JOptionPane.showMessageDialog(null, bloodType + " is not a a blood type");
        }
    }

//    @Override
//    public String getDonor(String donorName) throws RemoteException {
//        try {
//            // Prepare the SQL statement for retrieving a donor
//            String sql = "SELECT * FROM bloodbankmanagment WHERE name = ?";
//            PreparedStatement statement = con.mkDataBase().prepareStatement(sql);
//            statement.setString(1, donorName);
//
//            // Execute the SQL statement
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                String bloodType = resultSet.getString("blood_type");
//                int age = resultSet.getInt("age");
//                return "Donor: " + donorName + ", Blood Type: " + bloodType + ", Age: " + age;
//            } else {
//                return "Donor not found.";
//            }
//        } catch (SQLException e) {
//            System.err.println("Error getting donor: " + e.getMessage());
//            return "An error occurred while retrieving the donor.";
//        }
//    }

    @Override
    public List<Donor> getAllDonors() throws RemoteException {
    List<Donor> donors = new ArrayList<>();
        try {
            // Prepare the SQL statement for retrieving all donors
            String sql = "SELECT * FROM bloodbankmanagment";
            PreparedStatement statement = con.mkDataBase().prepareStatement(sql);

            // Execute the SQL statement
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Donor donor = new Donor();
                donor.setDonorName(resultSet.getString("name"));
                donor.setBloodType(resultSet.getString("bloodgroup"));
                donor.setQuantity(resultSet.getInt("quantity"));
                donors.add(donor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error getting all donors: " + e.getMessage());
        }
        return donors;
    }
    @Override
    public void updateDonor(String donorName, String bloodType, double blood_quantity) throws RemoteException {
        if (isValidBloodType(bloodType)) {    
            try {
                // Prepare the SQL statement for updating a donor
                String sql = "UPDATE bloodbankmanagment SET quantity = quantity + ?  WHERE name = ? AND bloodgroup = ?";
                PreparedStatement statement = con.mkDataBase().prepareStatement(sql);
                statement.setDouble(1, blood_quantity);
                statement.setString(2, donorName);
                statement.setString(3, bloodType);            
                


                // Execute the SQL statement
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                   JOptionPane.showMessageDialog(null, "Donor Updated: " + donorName);
                } else {
                    JOptionPane.showMessageDialog(null, "Donor not found.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error updating donor: " + e.getMessage());
            }
                }
        else {
            JOptionPane.showMessageDialog(null, bloodType + " is not a a blood type");
        }
    }

    @Override
    public void deleteDonor(String donorName) throws RemoteException {
        try {
            // Prepare the SQL statement for deleting a donor
            String sql = "DELETE FROM bloodbankmanagment WHERE name = ?";
            PreparedStatement statement = con.mkDataBase().prepareStatement(sql);
            statement.setString(1, donorName);

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Donor deleted: " + donorName);
            } else {
                JOptionPane.showMessageDialog(null, "Donor not found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting donor: " + e.getMessage());
        }
    }

    
    public boolean isValidBloodType(String bloodType) {
        // Blood type pattern: A, B, AB, or O followed by a positive or negative Rh factor (+ or -)
        String bloodTypePattern = "^(A|B|AB|O)[+-]$";

        return bloodType.matches(bloodTypePattern);
}
    
    @Override
    public boolean isAdminValid(String username, String password) {
    try {
        DbConnector dbConnector = new DbConnector();
        Connection con = dbConnector.mkDataBase();
        String query = "SELECT * FROM admin WHERE login_id = ? AND password = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        boolean isValid = rs.next();
        rs.close();
        stmt.close();
        con.close();
        return isValid;
    } catch (SQLException e) {
        return false;
    }
}
}