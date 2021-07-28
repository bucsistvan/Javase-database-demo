package hu.ulyssys.java.course.database.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DMLMain {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/demo";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "Admin123";
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", DATABASE_USER);
            properties.setProperty("password", DATABASE_PASSWORD);
            Connection connection = DriverManager.getConnection(DATABASE_URL, properties);
            //insert into a dog_owner tablaba
            //selectIdDemo(connection);
            selectIdEmployee(connection);

            System.out.println("Sikeresen legutott");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println("Vége a programunknak");
        }
    }

    private static void insertInto(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO dog_owner(first_name, last_name) VALUES  (?,?)");
        preparedStatement.setString(1,"Körte");
        preparedStatement.setString(2,"Cseresznye");
        preparedStatement.execute();
    }

    private static void delete(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM dog_owner WHERE first_name=?");
        preparedStatement.setString(1,"Alma");
        preparedStatement.execute();
    }

    private static void update(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE dog_owner set first_name=? where first_name=?");
        preparedStatement.setString(1, "Példa "+System.currentTimeMillis());
        preparedStatement.setString(2,"Béla");
        preparedStatement.execute();
    }

    private static void selectIdDemo(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM dog_owner WHERE first_name=?");
        preparedStatement.setString(1,"Bálint");
        ResultSet resultSet = preparedStatement.executeQuery();
        Long id = null;
        while (resultSet.next()){
            System.out.println("id értéke: "+resultSet.getLong("id"));
            id = resultSet.getLong("id");
        }
        System.out.println("A Bálint id-ja: " + id);
        selectDogDemo(connection,id);
    }

    private static void selectDogDemo(Connection connection, Long id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dog WHERE owner_id=?");
        preparedStatement.setLong(1,id);
        List<Dog> dogList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Dog dog = new Dog();
            dog.setId(resultSet.getLong("id"));
            dog.setColor(resultSet.getString("color"));
            dog.setName(resultSet.getString("name"));
            dog.setSpecies(resultSet.getString("species"));
            dogList.add(dog);
        }
        dogList.forEach(dog ->{
            System.out.println(dog.getId() + " " + dog.getName() + " " + dog.getSpecies() + " " + dog.getColor());
        });
    }

    private static void selectIdEmployee(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM employee WHERE first_name=?");
        preparedStatement.setString(1,"alma");
        ResultSet resultSet = preparedStatement.executeQuery();
        Long id = null;
        while (resultSet.next()){
            System.out.println("id értéke: "+resultSet.getLong("id"));
            id = resultSet.getLong("id");
        }
        System.out.println("A Bálint id-ja: " + id);
        selectAddressDemo(connection,id);
    }

    private static void selectAddressDemo(Connection connection, Long id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM address WHERE employee_id=?");
        preparedStatement.setLong(1,id);
        List<Address> addressList = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Address address = new Address();
            address.setId(resultSet.getLong("id"));
            address.setEmployeeId(resultSet.getLong("employee_id"));
            address.setValue(resultSet.getString("value"));
            address.setTest(resultSet.getString("test"));
            addressList.add(address);
        }
        addressList.forEach(address ->{
            System.out.println(address.getId() + " " + address.getEmployeeId() + " " + address.getValue() + " " + address.getTest());
        });
    }
}
