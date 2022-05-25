package model.repository;

import java.util.List;
import model.entity.Note;

public interface NoteRepository {
  Note create(String title, String description);
  Note read(int id);
  List<Note> readAll();
  Note update(Note note);
  boolean delete(int id);
}
