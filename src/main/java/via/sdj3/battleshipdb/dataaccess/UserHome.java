package via.sdj3.battleshipdb.dataaccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import util.Message;
import via.sdj3.battleshipdb.model.User;

import java.sql.SQLException;

public interface UserHome {
<<<<<<< HEAD
  User validateUser(String username, String password) throws SQLException, InvalidUsernameException, InvalidPasswordException;
=======
  User validateUser(String username, String password)
          throws SQLException, InvalidUsernameException, InvalidPasswordException;
  //Creating it because it is pretty simple, if what deleting it is not an issue
>>>>>>> de89f13bbdc54b89257fc8b5d45dc40a1973f16c
  void registerUser(String name, String password)
      throws SQLException, UsernameTakenException;
}
