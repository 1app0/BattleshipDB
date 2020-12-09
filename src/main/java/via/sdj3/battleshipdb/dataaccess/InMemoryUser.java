package via.sdj3.battleshipdb.dataaccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import util.Message;
import util.MessageType;
import via.sdj3.battleshipdb.dao.RealUserDataAccess;
import via.sdj3.battleshipdb.model.User;

import java.sql.SQLException;
import java.util.List;

public class InMemoryUser implements UserHome {
  private List<User> userList;
  RealUserDataAccess realUserDataAccess;

  public InMemoryUser() {
    this.realUserDataAccess = new RealUserDataAccess();
  }
  public void tryClass()
  {
    try
    {
      Class.forName("org.postgresql.Driver");
      System.out.println("Database is working correctly");
    }
    catch (Exception e)
    {
      System.out.println("Error loading Sql Driver!");
      e.printStackTrace();
    }
  }

  @Override public User validateUser(String username, String password)
          throws InvalidUsernameException, InvalidPasswordException, SQLException {

    User filteredUser;
    try {
      filteredUser = userList.stream().filter(user -> user.getUsername().equals(username)).findFirst().get();
    }
    catch (Exception e) {
      throw new InvalidUsernameException("User not found");
    }
    if (!filteredUser.getPassword().equals(password)) {
      throw new InvalidPasswordException("Invalid password");
    }
    return filteredUser;
  }

//  private boolean findUser(String username) throws SQLException
//  {
//    return realUserDataAccess.checkNameByInput(username);
//  }
}
