package NoteBook.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Маша on 08.06.2017.
 */
public class Record implements Serializable {
    private int recordID;
    private String recordText;
    private String author;
    private String title;
    private String type;
    private Date createdDate;
    private Date updatedDate;

    public Record(String recordText, String author, String title, String type) {
        this.recordText = recordText;
        this.author = author;
        this.title = title;
        this.type = type;

        //TODO: add new param, e.g. deadlineDate that is provided by user
        this.updatedDate = this.createdDate = new Date();
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getRecordID() {
        return recordID;
    }

    public String getRecordText() {
        return recordText;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }
}
