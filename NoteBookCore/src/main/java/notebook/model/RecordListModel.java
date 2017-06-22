package notebook.model;

import notebook.entity.Record;

import java.util.List;

/**
 * Created by Маша on 19.06.2017.
 */
public class RecordListModel extends MessageListModel {
    private List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RecordListModel that = (RecordListModel) o;

        return records != null ? records.equals(that.records) : that.records == null;
    }
}
