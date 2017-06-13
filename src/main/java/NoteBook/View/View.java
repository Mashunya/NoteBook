package NoteBook.View;

import NoteBook.Entity.Record;

/**
 * Created by Маша on 11.06.2017.
 */
public interface View {
    void showInfoMessage(String message);
    void showErrorMessage(String message);
    void showRecord(Record record);
}
