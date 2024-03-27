import java.sql.*;

public class ContactDAO {

    public static void addContact(String name, String phone, String email) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)"
             )) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Contact added successfully!");
            } else {
                System.out.println("Failed to add contact.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewContacts() {
        try (Connection connection = DBConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts")) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Phone: " + resultSet.getString("phone"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateContact(int id, String name, String phone, String email) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE contacts SET name=?, phone=?, email=? WHERE id=?"
             )) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Contact updated successfully!");
            } else {
                System.out.println("Failed to update contact.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteContact(int id) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM contacts WHERE id=?"
             )) {
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Contact deleted successfully!");
            } else {
                System.out.println("Failed to delete contact.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchContact(String searchKeyword) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM contacts WHERE name LIKE ? OR phone LIKE ? OR email LIKE ?"
             )) {
            preparedStatement.setString(1, "%" + searchKeyword + "%");
            preparedStatement.setString(2, "%" + searchKeyword + "%");
            preparedStatement.setString(3, "%" + searchKeyword + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Phone: " + resultSet.getString("phone"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
