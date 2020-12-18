package via.sdj3.battleshipdb.dataaccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import util.Message;
import via.sdj3.battleshipdb.model.User;

import java.sql.SQLException;

public interface DBAccess {
  User validateUser(String username, String password) throws SQLException, InvalidUsernameException, InvalidPasswordException;
  void registerUser(String name, String password)
      throws SQLException, UsernameTakenException;
  String matchSave(String username,String playerMatch, String botMatch, int numberOfShipsBot, int numberOfShipsPlayer) throws SQLException;
  String matchLoad(String username) throws SQLException;
  void deleteSave(String username) throws SQLException;
}
