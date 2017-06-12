package NoteBook.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Маша on 08.06.2017.
 */
public class Record implements Serializable {
    private int recordID;
    private String recordText;
//    private String author;
//    private Date createdDate;
//    private Date updatedDate;
//    private String type;
//    private String title;

    public Record(int recordID, String recordText) {
        this.recordID = recordID;
        this.recordText = recordText;
    }

    public int getRecordID() {
        return recordID;
    }

    public String getRecordText() {
        return recordText;
    }
}
