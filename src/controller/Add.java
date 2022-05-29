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

public class Add {
  private view.Add viewAdd;
  private NoteRepository repository;

  public view.Add getView() { return viewAdd; }

  public void setView(view.Add view) { this.viewAdd = view; }

  public NoteRepository getRepository() { return repository; }

  public void setRepository(NoteRepository repository) {
    this.repository = repository;
  }

  public Add(view.Add view, NoteRepository repository) {
    this.viewAdd = view;
    this.repository = repository;

    initController();

    this.viewAdd.setTitle("ADD NOTES");
    this.viewAdd.setLocationRelativeTo(null);
    this.viewAdd.setResizable(false);
    this.viewAdd.pack();
  }

  private void initController() {
    viewAdd.getButtonAdd().addActionListener((l) -> {
      String title = viewAdd.getTextFieldTitle().getText();
      String description = viewAdd.getTextAreaDescription().getText();
      Note note = repository.create(title, description);
      if (note == null) {
        JOptionPane.showMessageDialog(null, "Failed to create a new note");
      } else {
        JOptionPane.showMessageDialog(null, "Note created succesfully");
      }
    });

    viewAdd.getButtonBack().addActionListener((l) -> {
      controller.Home controllerHome =
          new controller.Home(new view.Home(), repository);
      viewAdd.setVisible(false);
      controllerHome.getView().setVisible(true);
    });
  }
}
