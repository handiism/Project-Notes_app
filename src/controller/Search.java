/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license Click
 * nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this
 * template
 */
package controller;

import javax.swing.JOptionPane;
import model.entity.Note;
import model.repository.NoteRepository;

public class Search {
  private view.Search viewSearch;
  private NoteRepository repository;
  private Note note = new Note();
  private boolean found = false;

  public view.Search getView() { return viewSearch; }

  public void setView(view.Search view) { this.viewSearch = view; }

  public NoteRepository getRepository() { return repository; }

  public void setRepository(NoteRepository repository) {
    this.repository = repository;
  }

  public Search(view.Search view, NoteRepository repository) {
    this.viewSearch = view;
    this.repository = repository;
    initController();

    viewSearch.setTitle("SEARCH NOTE");
    viewSearch.setLocationRelativeTo(null);
    viewSearch.setResizable(false);
  }

  private void initController() {
    setInputEnabled(false);

    viewSearch.getButtonSearch().addActionListener((l) -> {
      String id = viewSearch.getTextFieldId().getText();
      int idAsInt = -1;
      try {
        idAsInt = Integer.parseInt(id);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Bad Input ID");
        found = false;
        setEmptyInput();
        setInputEnabled(false);
        note = null;
        return;
      }

      note = repository.read(idAsInt);
      if (note == null) {
        JOptionPane.showMessageDialog(null, "ID Not Found");
        setEmptyInput();
        setInputEnabled(false);
        found = false;
      } else {
        setInputEnabled(true);
        viewSearch.getTextFieldTitle().setText(note.getTitle());
        viewSearch.getTextAreaDescription().setText(note.getDescription());
        found = true;
      }
    });

    viewSearch.getButtonEdit().addActionListener((l) -> {
      if (found) {
        note.setTitle(viewSearch.getTextFieldTitle().getText());
        note.setDescription(viewSearch.getTextAreaDescription().getText());
        int input =
            JOptionPane.showConfirmDialog(null, "Do You Want To Continue");
        if (input != 0) {
          JOptionPane.showMessageDialog(null, "Operation Canceled");
        } else {
          note = repository.update(note);
          JOptionPane.showMessageDialog(null, "Note Edited Succesfully");
        }
      }
    });

    viewSearch.getButtonDelete().addActionListener((l) -> {
      if (found) {
        int input =
            JOptionPane.showConfirmDialog(null, "Do You Want To Continue");
        if (input != 0) {
          JOptionPane.showMessageDialog(null, "Operation Canceled");
        } else {
          repository.delete(note.getId());
          controller.Home controllerHome =
              new controller.Home(new view.Home(), repository);
          viewSearch.setVisible(false);
          controllerHome.getView().setVisible(true);
          JOptionPane.showMessageDialog(null, "Note Deleted Succesfully");
        }
      }
    });
    viewSearch.getButtonBack().addActionListener((l) -> {
      controller.Home controllerHome =
          new controller.Home(new view.Home(), repository);
      viewSearch.setVisible(false);
      controllerHome.getView().setVisible(true);
    });
  }

  private void setInputEnabled(boolean state) {
    viewSearch.getTextFieldTitle().setEnabled(state);
    viewSearch.getTextAreaDescription().setEnabled(state);
    viewSearch.getButtonEdit().setEnabled(state);
    viewSearch.getButtonDelete().setEnabled(state);
  }

  private void setEmptyInput() {
    viewSearch.getTextFieldTitle().setText("");
    viewSearch.getTextAreaDescription().setText("");
  }
}
