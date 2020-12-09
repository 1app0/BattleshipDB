package via.sdj3.battleshipdb.model;

public class User {
  private final String username;
  private final String password;
  private final String accessType;

  public User(String username, String password, String accessType) {
    this.username = username;
    this.password = password;
    this.accessType = accessType;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getAccessType() {
    return accessType;
  }
}
