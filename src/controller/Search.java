/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license Click
 * nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this
 * template
 */
package controller;

import model.repository.NoteRepository;

public class Search {
  private view.Search viewSearch;
  private NoteRepository repository;

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
    viewSearch.setLocationRelativeTo(null);
    viewSearch.setResizable(false);
  }
  
  private void initController() {
      viewSearch.getButtonBack().addActionListener((l) -> {
          controller.Home controllerHome = new controller.Home(new view.Home(), repository);
          viewSearch.setVisible(false);
          controllerHome.getView().setVisible(true);
      });
  }
}
