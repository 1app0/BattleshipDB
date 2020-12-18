package via.sdj3.battleshipdb.dao;

import Exceptions.InvalidUsernameException;
import util.Message;
import via.sdj3.battleshipdb.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GameDAO {

    User getUserByName(String username) throws SQLException, InvalidUsernameException;
    boolean checkPasswordByInput(String username,String password) throws SQLException;
    void createUser(String username,String password) throws SQLException;
    boolean checkNameByInput(String name) throws SQLException;
    String matchSave(String username,String playerMatch, String botMatch, int numberOfShipsBot, int numberOfShipsPlayer) throws SQLException;
    String matchLoad(String username) throws SQLException;
    void deleteSave(String username) throws SQLException;
    boolean checkForSavedGame(String username) throws SQLException;
}
