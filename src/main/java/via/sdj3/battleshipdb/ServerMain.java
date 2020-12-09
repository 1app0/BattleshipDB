package via.sdj3.battleshipdb;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import via.sdj3.battleshipdb.dataaccess.UserHome;
import via.sdj3.battleshipdb.dataaccess.InMemoryUser;
import via.sdj3.battleshipdb.mediator.Connector;

import java.sql.SQLException;

public class ServerMain {
  public static void main(String[] args) throws SQLException, InvalidPasswordException, InvalidUsernameException {
    UserHome userHome = new InMemoryUser();
    Thread thread = new Thread(new Connector(userHome));
    thread.start();

    userHome.validateUser("alex","1234");
  }
}
