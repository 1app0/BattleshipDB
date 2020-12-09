package via.sdj3.battleshipdb;

import via.sdj3.battleshipdb.dataaccess.UserHome;
import via.sdj3.battleshipdb.dataaccess.InMemoryUser;
import via.sdj3.battleshipdb.mediator.Connector;

import java.sql.SQLException;

public class ServerMain {
  public static void main(String[] args) throws SQLException {
    UserHome userHome = new InMemoryUser();
    Thread thread = new Thread(new Connector(userHome));
    thread.start();

    userHome.validateUser("alex","12434");
  }
}
