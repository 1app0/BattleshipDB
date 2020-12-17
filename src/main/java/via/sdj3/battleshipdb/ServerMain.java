package via.sdj3.battleshipdb;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import via.sdj3.battleshipdb.dao.RealUserDataAccess;
import via.sdj3.battleshipdb.dao.UserDAO;
import via.sdj3.battleshipdb.dataaccess.UserHome;
import via.sdj3.battleshipdb.dataaccess.InMemoryUser;
import via.sdj3.battleshipdb.mediator.Connector;
import via.sdj3.battleshipdb.model.User;

import java.sql.SQLException;

public class ServerMain {
  public static void main(String[] args) throws SQLException, InvalidPasswordException, InvalidUsernameException {
    UserDAO realUserDataAccess = new RealUserDataAccess();
    UserHome userHome = new InMemoryUser(realUserDataAccess);
    Thread thread = new Thread(new Connector(userHome));
    thread.start();



  }
}
