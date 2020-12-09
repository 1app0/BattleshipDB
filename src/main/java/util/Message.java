package util;

public class Message {
  private String message;
  private MessageType type;

  public Message(String message, MessageType type) {
    this.message = message;
    this.type = type;
  }

  public Message(MessageType type) {
    this.type = type;
    message = null;
  }

  public String getMessage() {
    return message;
  }

  public MessageType getType() {
    return type;
  }
}
