package notebook.model;

import notebook.entity.Record;

/**
 * Created by Маша on 19.06.2017.
 */
public class RecordModel extends MessageListModel {
    private Record record;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RecordModel that = (RecordModel) o;

        return record != null ? record.equals(that.record) : that.record == null;
    }
}
