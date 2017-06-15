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
    public void showRecord(Record record) {
        System.out.println("ID: " + record.getRecordID() + "\n" +
                "Title: " + record.getTitle() + "\n" +
                "Text: " + record.getRecordText() + "\n" +
                "Author: " + record.getAuthor() + "\n" +
                "Type: " + record.getType() + "\n" +
                "CreatedDate: " + record.getCreatedDate().toString() + "\n" +
                "UpdateDate: " + record.getUpdatedDate().toString() + "\n");
    }
}
