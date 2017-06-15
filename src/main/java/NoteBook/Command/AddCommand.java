package NoteBook.Command;

import NoteBook.Entity.Record;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
public class AddCommand extends Command {

    private String text;
    private String author;
    private String type;
    private String title;

    public AddCommand(){}

    public AddCommand(Map<String, Object> preparedParams) {
        this.text = (String)preparedParams.get("text");
        this.author = (String)preparedParams.get("author");
        this.type = (String)preparedParams.get("type");
        this.title = (String)preparedParams.get("title");
    };

    @Override
    public void execute() {
        Record record = new Record(text, author, title, type);
        noteBookService.addRecord(record);
    }

    @Override
    public Command newCommand(Map<String, Object> preparedParams) {
        return new AddCommand(preparedParams);
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
