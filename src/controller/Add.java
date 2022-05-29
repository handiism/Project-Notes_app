/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license Click
 * nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this
 * template
 */
package controller;

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
    
    this.viewAdd.setLocationRelativeTo(null);
    this.viewAdd.setResizable(false);
    this.viewAdd.pack();
  }
  
  private void initController() {
      viewAdd.getButtonBack().addActionListener((l) -> {
          controller.Home controllerHome = new controller.Home(new view.Home(), repository);
          viewAdd.setVisible(false);
          controllerHome.getView().setVisible(true);
      });
  }
}
