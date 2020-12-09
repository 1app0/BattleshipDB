package via.sdj3.battleshipdb.dao;

import Exceptions.InvalidUsernameException;
import via.sdj3.battleshipdb.model.User;

import java.sql.*;

public class RealUserDataAccess implements userDAO{

    private Connection getConnection() throws SQLException
    {
        return DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/SEP3_Battleship",
                        "postgres", "VolkovaS1793");
    }

    @Override public User checkNameByInput(String username) throws SQLException {

        User filteredUser;
        Connection connection = getConnection();

            PreparedStatement statement = connection
                    .prepareStatement("SELECT COUNT(*) FROM login WHERE username=?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;


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
