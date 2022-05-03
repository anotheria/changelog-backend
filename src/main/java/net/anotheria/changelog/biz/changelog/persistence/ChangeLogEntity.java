package net.anotheria.changelog.biz.changelog.persistence;

import net.anotheria.changelog.biz.changelog.bean.ChangeLogType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * ChangeLog DB entity
 *
 */
@Entity
@Table(name = "changelog")
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

    @OneToMany(mappedBy = "id.changelogId", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ChangeLogTagEntity> tags;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ChangeLogType type;

    @Column(name = "time_when")
    private Date timestamp;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date created;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
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

    public List<ChangeLogTagEntity> getTags() {
        return tags;
    }

    public void setTags(List<ChangeLogTagEntity> tags) {
        this.tags = tags;
    }

    public ChangeLogType getType() {
        return type;
    }

    public void setType(ChangeLogType type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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
                ", tags=" + tags +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
