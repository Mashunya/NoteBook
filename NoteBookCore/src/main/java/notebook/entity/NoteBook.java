package notebook.entity;

import java.util.ArrayList;

/**
 * Created by Маша on 08.06.2017.
 */
public class NoteBook {

    private ArrayList<Record> records = new ArrayList<>();

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }
}
