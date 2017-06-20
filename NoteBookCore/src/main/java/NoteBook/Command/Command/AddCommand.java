package NoteBook.Command.Command;

import NoteBook.Entity.Record;
import NoteBook.ModelAndView.ModelAndView;

import java.util.Map;

/**
 * Created by Маша on 08.06.2017.
 */
//TODO: try in branch to use command with execute(Map<String, String>) instead of fields
public class AddCommand extends CommandWorkedWithNoteBook {

    private String text;
    private String author;
    private String type;
    private String title;

    private Map<String, String> globalParams;

    @Override
    public ModelAndView execute() {
        Record record = new Record(text, author, title, type);
        return noteBookService.addRecord(record);
    }

    @Override
    public Map<String, String> getGlobalParams() {
        return globalParams;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGlobalParams(Map<String, String> globalParams) {
        this.globalParams = globalParams;
    }
}
