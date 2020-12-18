package via.sdj3.battleshipdb.dataaccess;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import via.sdj3.battleshipdb.dao.GameDAO;
import via.sdj3.battleshipdb.model.User;

import java.sql.SQLException;

public class DbDirectAccess implements DBAccess {
  private GameDAO realUserDataAccess;

  public DbDirectAccess(GameDAO realUserDataAccess) {
    this.realUserDataAccess = realUserDataAccess;
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
  public void registerUser(String username, String password)
      throws SQLException, UsernameTakenException {
    tryClass();

    if(findUser(username))
    {
      System.out.println("This username already exists");
      throw new UsernameTakenException("This username already exists");
    }
    else {
      realUserDataAccess.createUser(username,password);
      System.out.println("Register was successful");
    }
  }

  @Override public String matchSave(String username, String playerMatch,
      String botMatch, int numberOfShipsBot, int numberOfShipsPlayer)
      throws SQLException {
    return realUserDataAccess.matchSave(username, playerMatch, botMatch, numberOfShipsBot, numberOfShipsPlayer);
  }

  //TODO finish these methods
  @Override public String matchLoad(String username) throws SQLException {
    return null;
  }

  @Override public void deleteSave(String username) throws SQLException {

  }

  private boolean findUser(String username) throws SQLException
  {
    return realUserDataAccess.checkNameByInput(username);
  }
}
