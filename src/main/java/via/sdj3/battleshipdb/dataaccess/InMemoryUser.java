package via.sdj3.battleshipdb.dataaccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import util.Message;
import util.MessageType;
import via.sdj3.battleshipdb.dao.UserDAO;
import via.sdj3.battleshipdb.model.User;

import java.sql.SQLException;
import java.util.List;

public class InMemoryUser implements UserHome {
  private UserDAO realUserDataAccess;

  public InMemoryUser(UserDAO userDAO) {
    realUserDataAccess = userDAO;
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
      filteredUser = realUserDataAccess.getUserByName(username);
    }
    catch (InvalidUsernameException userNotFound) {
      throw userNotFound;
    }
    if (!filteredUser.getPassword().equals(password)) {
      throw new InvalidPasswordException("Invalid password");
    }
    return filteredUser;
  }

  @Override
  public Message registerUSer(String username, String password) throws SQLException
  {
    tryClass();

    if(findUser(username))
    {
      System.out.println("This username already exists");
      return  new Message("This username already exists",MessageType.REGISTER_RESULT);
    }
    else {
      realUserDataAccess.createUser(username,password);
      System.out.println("Register was successful");
      return new Message("Register was successful",MessageType.REGISTER_RESULT);
    }
  }

  private boolean findUser(String username) throws SQLException
  {
    return realUserDataAccess.checkNameByInput(username);
  }
}
