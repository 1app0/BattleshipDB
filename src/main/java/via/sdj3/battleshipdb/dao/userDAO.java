package via.sdj3.battleshipdb.dao;

import java.sql.SQLException;

public interface userDAO {

    boolean getUserByName(String username) throws SQLException;
    boolean checkPasswordByInput(String username,String password) throws SQLException;
}
