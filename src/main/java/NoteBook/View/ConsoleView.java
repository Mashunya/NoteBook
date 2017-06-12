package NoteBook.View;

import NoteBook.Entity.Record;

/**
 * Created by Маша on 11.06.2017.
 */
public class ConsoleView implements View {
    @Override
    public void showInfoMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println("ERROR:" + message);
    }

    @Override
    public void shoeRecord(Record record) {
        System.out.println(record.getRecordID() + ": " + record.getRecordText());
    }
}
