package via.sdj3.battleshipdb.dao;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import util.Message;
import via.sdj3.battleshipdb.model.User;

public interface UserDao {
  User validateUser(String username, String password)
      throws InvalidUsernameException, InvalidPasswordException;
}
