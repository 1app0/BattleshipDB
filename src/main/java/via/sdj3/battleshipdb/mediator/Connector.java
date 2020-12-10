package via.sdj3.battleshipdb.mediator;

import via.sdj3.battleshipdb.dataaccess.UserHome;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector implements Runnable{
  private final int port = 7990;
  private boolean running;
  private ServerSocket welcomeSocket;
  private UserHome userHome;

  public Connector(UserHome userHome){
    this.userHome = userHome;
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
      Thread thread = new Thread(new ClientHandler(socket, userHome));
      thread.setDaemon(true);
      thread.start();
    }
  }
}
