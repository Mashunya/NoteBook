package NoteBook.Entity;

import java.io.Serializable;

/**
 * Created by Маша on 08.06.2017.
 */
public class Record implements Serializable {
    private int recordID;
    private String recordText;

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
