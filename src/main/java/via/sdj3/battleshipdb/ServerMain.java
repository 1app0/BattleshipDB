package via.sdj3.battleshipdb;

import via.sdj3.battleshipdb.dao.UserDao;
import via.sdj3.battleshipdb.dao.UserDataAccess;
import via.sdj3.battleshipdb.mediator.Connector;

import java.io.IOException;

public class ServerMain {
  public static void main(String[] args) {
    UserDao userDao = new UserDataAccess();
    Thread thread = new Thread(new Connector(userDao));
    thread.start();
  }
}
