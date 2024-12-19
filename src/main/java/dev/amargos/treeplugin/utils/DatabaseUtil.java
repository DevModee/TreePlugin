package dev.amargos.treeplugin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
  private final String url;
  private final String user;
  private final String password;

  public DatabaseUtil(String host, int port, String database, String user, String password) {
    this.url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false";
    this.user = user;
    this.password = password;
  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }
}
