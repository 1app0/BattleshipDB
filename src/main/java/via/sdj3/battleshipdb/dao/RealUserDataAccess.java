package via.sdj3.battleshipdb.dao;

import Exceptions.InvalidUsernameException;
import via.sdj3.battleshipdb.model.User;

import java.sql.*;

public class RealUserDataAccess implements UserDAO {

    private Connection getConnection() throws SQLException
    {
        return DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/SEP3_Battleship",
                        "postgres", "2031");
    }

    @Override public User getUserByName(String username)
        throws SQLException, InvalidUsernameException {

        User filteredUser = null;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM login WHERE username=?");
        statement.setString(1, username);
        System.out.println("login attempt");
        ResultSet rs = statement.executeQuery();
        while(rs.next())
        {
            String usernameDB = rs.getString("username");
            String passwordDB = rs.getString("password");
            String accessTypeDB = rs.getString("accessType");
            filteredUser=new User(usernameDB,passwordDB,accessTypeDB);
            statement.close();
            connection.close();
        }
        if (filteredUser == null){
            throw new InvalidUsernameException("User not found");
        }
        return filteredUser;
    }

    @Override
    public boolean checkPasswordByInput(String username, String password) throws SQLException
    {
        Connection connection = getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM login WHERE username=? AND password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("error!");
            return false;
        }
    }

    @Override public boolean checkNameByInput(String username) throws SQLException
    {

        Connection connection = getConnection();
        try
        {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT COUNT(*) FROM login WHERE username=?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("error!!!!!!");
            return false;
        }

    }

    @Override
    public void createUser(String username, String password) throws SQLException {
        Connection connection=getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO login(username,password,accessType) VALUES(?,?,?);");
            statement.setString(1,username);
            statement.setString(2, password);
            statement.setString(3,"registeredUser");
            statement.executeUpdate();
            statement.close();
            connection.close();

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("error creating user(register)");
            System.exit(0);
        }
    }


}
