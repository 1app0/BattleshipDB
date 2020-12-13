package via.sdj3.battleshipdb.mediator;

import Exceptions.InvalidPasswordException;
import Exceptions.InvalidUsernameException;
import Exceptions.UsernameTakenException;
import com.google.gson.Gson;
import util.Message;
import util.MessageType;
import via.sdj3.battleshipdb.dataaccess.UserHome;
import via.sdj3.battleshipdb.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private Gson gson;
  private UserHome userHome;

  //changed everywhere from userDao to userHome
  public ClientHandler(Socket socket, UserHome userHome) throws IOException {
    this.socket = socket;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    gson = new Gson();
    this.userHome = userHome;
  }

  public void run() {
    running = true;
    while (running) {
      try {
        String request = in.readLine();
        Message message = gson.fromJson(request, Message.class);
        switch (message.getType()) {
          case VALIDATE_USER:
            User userToBeValidated = gson.fromJson(message.getMessage(), User.class);
            User validatedUser = userHome.validateUser(userToBeValidated.getUsername(), userToBeValidated.getPassword());
            String validatedUserAsJson = gson.toJson(validatedUser);
            Message answer = new Message(validatedUserAsJson, MessageType.VALID_USER);
            String answerAsJson = gson.toJson(answer);
            out.println(answerAsJson);
            break;
          case REGISTER_USER:
            User userToBeRegistered = gson.fromJson(message.getMessage(), User.class);
            userHome.registerUser(userToBeRegistered.getUsername(), userToBeRegistered.getPassword());
            String messageAsJson = gson.toJson(new Message(MessageType.REGISTER_USER_SUCCESS));
            out.println(messageAsJson);
            break;
        }
      } catch(IOException | SQLException e) {
        close();
      }
      catch (InvalidUsernameException e) {
        Message message = new Message(MessageType.USERNAME_NOT_FOUND);
        String json = gson.toJson(message);
        out.println(json);
      }
      catch (InvalidPasswordException e) {
        Message message = new Message(MessageType.INVALID_PASSWORD);
        String json = gson.toJson(message);
        out.println(json);
      }
      catch (UsernameTakenException e) {
        Message message = new Message(MessageType.REGISTER_USER_USERNAMETAKEN);
        String json = gson.toJson(message);
        out.println(json);
      }
    }
  }

  private void close() {
    running = false;
    try {
      in.close();
      out.close();
      socket.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
