package via.sdj3.battleshipdb.dao;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import util.Message;
import util.MessageType;
import via.sdj3.battleshipdb.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataAccess implements UserDao {
  private List<User> userList;

  public UserDataAccess() {
    userList = new ArrayList<>();
    userList.add(new User("alex", "1234", "registeredUser"));
    userList.add(new User("shrek", "shrekIsLife", "registeredUser"));
  }

  @Override public User validateUser(String username, String password)
      throws InvalidUsernameException, InvalidPasswordException {
    User filteredUser;
    try {
      filteredUser = userList.stream().filter(user -> user.getUsername().equals(username)).findFirst().get();
    } catch (Exception e) {
      throw new InvalidUsernameException("User not found");
    }
    if (!filteredUser.getPassword().equals(password)) {
      throw new InvalidPasswordException("Invalid password");
    }
    return filteredUser;
  }
}
