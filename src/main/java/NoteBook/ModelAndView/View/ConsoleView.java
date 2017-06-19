package NoteBook.ModelAndView.View;

import NoteBook.Entity.Record;
import NoteBook.ModelAndView.Model.Message;

import java.util.List;

/**
 * Created by Маша on 11.06.2017.
 */
//TODO: Think of multiple views (that show data on console) and command can decide which view to use to display it's data
public class ConsoleView {

    public void show(Message message) {
        switch(message.getStatus()) {
            case Message.SUCCESS:
                System.out.println("SUCCESS: " + message);
                break;
            case Message.INFO:
                System.out.println("INFO: " + message);
                break;
            case Message.WARNING:
                System.out.println("WARNING: " + message);
                break;
            case Message.ERROR:
                System.out.println("ERROR: " + message);
                break;
        }
    }

    public void show(Record record) {
        System.out.println("ID: " + record.getRecordID() + "\n" +
                "Title: " + record.getTitle() + "\n" +
                "Text: " + record.getRecordText() + "\n" +
                "Author: " + record.getAuthor() + "\n" +
                "Type: " + record.getType() + "\n" +
                "CreatedDate: " + record.getCreatedDate().toString() + "\n" +
                "UpdateDate: " + record.getUpdatedDate().toString() + "\n");
    }

    public void show(List<Record> records) {
        for(Record record: records) {
            show(record);
        }
    }
}
