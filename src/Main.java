

import database.*;
import model.repository.*;

public class Main {

    public static void main(String[] args) {
    Database database = new MySQL();
    NoteRepository noteRepository =
        new NoteRepositoryImpl(database.getConnection());
    view.Home view = new view.Home();
    controller.Home controller = new controller.Home(view, noteRepository);
    controller.getView().setVisible(true);
    }
    
}
