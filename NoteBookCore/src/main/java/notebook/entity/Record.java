package notebook.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Маша on 08.06.2017.
 */
@Entity
@Table(name = "notebook")
public class Record implements Serializable {

    @Id
    @Column(name = "recordID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordID;

    @Column(nullable = false)
    private String recordText;

    @Column
    private String author;

    @Column
    private String title;

    @Column(name = "recordType")
    private String type;

    @Column(name = "deadline")
    private Date deadlineDate;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private Date updatedDate;

    public Record(){}

    public Record(String recordText, String author, String title, String type, Date deadlineDate) {
        this.recordText = recordText;
        this.author = author;
        this.title = title;
        this.type = type;
        this.deadlineDate = deadlineDate;
        this.updatedDate = this.createdDate = new Date();
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public void setRecordText(String recordText) {
        this.recordText = recordText;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
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

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }
}
