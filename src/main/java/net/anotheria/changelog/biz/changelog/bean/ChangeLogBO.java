package net.anotheria.changelog.biz.changelog.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChangeLogBO implements Serializable {

    private static final long serialVersionUID = 4159315075712606705L;

    private Integer id;
    private long timestamp;
    private String author;
    private String message;
    private String reason;
    private List<String> tags = new ArrayList<>();
    private ChangeLogType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ChangeLogType getType() {
        return type;
    }

    public void setType(ChangeLogType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChangeLogBO{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", reason='" + reason + '\'' +
                ", tags=" + tags +
                ", type=" + type +
                '}';
    }
}
