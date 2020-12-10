package via.sdj3.battleshipdb.dao;

import via.sdj3.battleshipdb.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

    User getUserByName(String username) throws SQLException;
    boolean checkPasswordByInput(String username,String password) throws SQLException;
}
