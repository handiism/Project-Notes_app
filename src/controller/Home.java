/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license Click
 * nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this
 * template
 */
package controller;

import helper.NonEditableTableModel;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.entity.Note;
import model.repository.NoteRepository;

public class Home {
  private view.Home viewHome;
  private NoteRepository repository;

  public view.Home getView() { return viewHome; }

  public void setView(view.Home view) { this.viewHome = view; }

  public NoteRepository getRepository() { return repository; }

  public void setRepository(NoteRepository repository) {
    this.repository = repository;
  }

  public Home(view.Home view, NoteRepository repository) {
    this.viewHome = view;
    this.repository = repository;

    initController();
    
    this.viewHome.setTitle("HOME");
    this.viewHome.setLocationRelativeTo(null);
    this.viewHome.setResizable(false);
    this.viewHome.pack();
  }

  private void initController() {
    DefaultTableModel defaultTableModel = new NonEditableTableModel();
    String tableNames[] = {"ID", "TITLE"};
    for (int i = 0; i < tableNames.length; i++) {
      defaultTableModel.addColumn(tableNames[i]);
    }
    List<Note> notes = repository.readAll();
    for (Note note : notes) {
      defaultTableModel.addRow(new Object[] {note.getId(), note.getTitle()});
    }
    viewHome.getTableNote().setModel(defaultTableModel);
    viewHome.getTableNote().setRowSelectionAllowed(false);
    viewHome.getButtonAdd().addActionListener((l) -> {
      controller.Add controllerAdd =
          new controller.Add(new view.Add(), repository);
      viewHome.setVisible(false);
      controllerAdd.getView().setVisible(true);
    });

    viewHome.getButtonSearch().addActionListener((l) -> {
      controller.Search controllerSearch =
          new controller.Search(new view.Search(), repository);
      viewHome.setVisible(false);
      controllerSearch.getView().setVisible(true);
    });
  }
}
