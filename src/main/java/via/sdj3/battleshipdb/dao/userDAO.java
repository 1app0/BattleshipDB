package via.sdj3.battleshipdb.dao;

import via.sdj3.battleshipdb.model.User;

import java.sql.SQLException;

public interface userDAO {

    User getUserByName(String username) throws SQLException;
    boolean checkPasswordByInput(String username,String password) throws SQLException;
}
