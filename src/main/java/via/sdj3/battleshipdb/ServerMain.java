package via.sdj3.battleshipdb;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import via.sdj3.battleshipdb.dao.RealGameDataAccess;
import via.sdj3.battleshipdb.dao.GameDAO;
import via.sdj3.battleshipdb.dataaccess.DBAccess;
import via.sdj3.battleshipdb.dataaccess.DbDirectAccess;
import via.sdj3.battleshipdb.mediator.Connector;

import java.sql.SQLException;

public class ServerMain {
  public static void main(String[] args) throws SQLException, InvalidPasswordException, InvalidUsernameException {
    GameDAO realUserDataAccess = new RealGameDataAccess();
    DBAccess dbAccess = new DbDirectAccess(realUserDataAccess);
    Thread thread = new Thread(new Connector(dbAccess));
    thread.start();
  }
}
