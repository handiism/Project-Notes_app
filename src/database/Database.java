package database;

import java.sql.Connection;

public abstract class Database {
  public abstract Connection getConnection();
}
