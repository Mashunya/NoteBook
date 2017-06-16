package NoteBook.View;

import NoteBook.Command.CommandResult.CommandResult;
import NoteBook.Entity.Record;

import java.util.List;

/**
 * Created by Маша on 11.06.2017.
 */

public class ConsoleView implements View {

    @Override
    public void show(List<CommandResult> results) {
        Class messageClass;
        Object message;
        for(CommandResult result: results) {
            message = result.getResultMessage();
            messageClass = message.getClass();
            if(messageClass.equals(Record.class)) {
                show((Record)message, result.getStatus());
            } else if(messageClass.equals(List.class)) {
                show((List<Record>)message, result.getStatus());
            } else {
                show(message.toString(), result.getStatus());
            }
        }
    }

    @Override
    public void show(String message, int status) {
        switch(status) {
            case CommandResult.SUCCESS:
                System.out.println("SUCCESS: " + message);
                break;
            case CommandResult.INFO:
                System.out.println("INFO: " + message);
                break;
            case CommandResult.WARNING:
                System.out.println("WARNING: " + message);
                break;
            case CommandResult.ERROR:
                System.out.println("ERROR: " + message);
                break;
        }
    }

    @Override
    public void show(Record record, int status) {
        System.out.println("ID: " + record.getRecordID() + "\n" +
                "Title: " + record.getTitle() + "\n" +
                "Text: " + record.getRecordText() + "\n" +
                "Author: " + record.getAuthor() + "\n" +
                "Type: " + record.getType() + "\n" +
                "CreatedDate: " + record.getCreatedDate().toString() + "\n" +
                "UpdateDate: " + record.getUpdatedDate().toString() + "\n");
    }

    @Override
    public void show(List<Record> records, int status) {
        for(Record record: records) {
            show(record, status);
        }
    }
}
