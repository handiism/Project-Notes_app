package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.entity.Note;

public class NoteRepositoryImpl implements NoteRepository {
  private Connection connection;

  public NoteRepositoryImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Note create(String title, String description) {
    try {
      String query = "INSERT INTO notes(title, description) VALUES (?,?)";
      PreparedStatement preparedStatement = connection.prepareStatement(
          query, PreparedStatement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, title);
      preparedStatement.setString(2, description);
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      Note note = new Note();

      if (resultSet.next()) {
        note.setId(resultSet.getInt(1));
        note.setTitle(title);
        note.setDescription(description);
      }
      return note;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public Note read(int id) {
    try {
      String query = "SELECT id, title, description FROM notes WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return new Note(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3));
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public List<Note> readAll() {
    try {
      List<Note> notes = new ArrayList<>();
      String query = "SELECT id, title, description FROM notes";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Note note = new Note(resultSet.getInt(1), resultSet.getString(2),
                             resultSet.getString(3));
        notes.add(note);
      }
      return notes;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public Note update(Note note) {
    try {
      String query = "UPDATE notes SET title = ?, description = ? WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, note.getTitle());
      preparedStatement.setString(2, note.getDescription());
      preparedStatement.setInt(3, note.getId());
      if (preparedStatement.executeUpdate() != 1) {
        return null;
      }
      return note;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public boolean delete(int id) {
    try {
      String query = "DELETE FROM notes WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      if (preparedStatement.executeUpdate() != 1) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
