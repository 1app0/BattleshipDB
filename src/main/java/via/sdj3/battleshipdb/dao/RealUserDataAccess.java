package via.sdj3.battleshipdb.dao;

import via.sdj3.battleshipdb.model.User;

import java.sql.*;

public class RealUserDataAccess implements UserDAO {

    private Connection getConnection() throws SQLException
    {
        return DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/SEP3_Battleship",
                        "postgres", "VolkovaS1793");
    }

    @Override public User getUserByName(String username) throws SQLException {

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
            System.out.println("error!!!!!!");
            return false;
        }
    }





}
