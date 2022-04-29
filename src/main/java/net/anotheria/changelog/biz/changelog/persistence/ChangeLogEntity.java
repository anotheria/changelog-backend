package net.anotheria.changelog.biz.changelog.persistence;

import net.anotheria.changelog.biz.changelog.bean.ChangeLogType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * ChangeLog DB entity
 *
 */
@Entity
@Table(name = "changelog_bbb")
public class ChangeLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "author")
    private String author;

    @Column(name = "message")
    private String message;

    @Column(name = "reason")
    private String reason;

//    private List<String> tags = new ArrayList<>();

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ChangeLogType type;

    @Column(name = "time_when")
    private long timestamp;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date created;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ChangeLogType getType() {
        return type;
    }

    public void setType(ChangeLogType type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "ChangeLogEntity{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", reason='" + reason + '\'' +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
