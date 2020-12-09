package via.sdj3.battleshipdb.mediator;

import via.sdj3.battleshipdb.dao.UserDao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector implements Runnable{
  private final int port = 7990;
  private boolean running;
  private ServerSocket welcomeSocket;
  private UserDao userDao;

  public Connector(UserDao userDao){
    this.userDao = userDao;
  }

  public void run() {
    try {
      start();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void start() throws IOException {
    System.out.println("The server has started");
    welcomeSocket = new ServerSocket(port);
    running = true;
    while (running) {
      Socket socket = welcomeSocket.accept();
      Thread thread = new Thread(new ClientHandler(socket, userDao));
      thread.setDaemon(true);
      thread.start();
    }
  }
}
