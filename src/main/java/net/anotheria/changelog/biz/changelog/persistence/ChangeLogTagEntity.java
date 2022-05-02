package net.anotheria.changelog.biz.changelog.persistence;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * ChangeLogTag DB entity
 *
 */
@Entity
@Table(name = "changelog_tag")
public class ChangeLogTagEntity {

    @EmbeddedId
    private Id id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date created;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updated;

    public ChangeLogTagEntity() {
    }

    public ChangeLogTagEntity(Id id) {
        this.id = id;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getTag() {
        return getId().getTag();
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
        return "ChangeLogTagEntity{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    /**
     * Primary key.
     */
    @Embeddable
    public static class Id implements Serializable {

        private static final long serialVersionUID = 7231275101129351640L;

        @Column(name = "changelog_id")
        private Integer changelogId;

        @Column(name = "tag")
        private String tag;

        public Id() {
        }

        public Id(Integer changelogId, String tag) {
            this.changelogId = changelogId;
            this.tag = tag;
        }

        public Integer getChangelogId() {
            return changelogId;
        }

        public void setChangelogId(Integer changelogId) {
            this.changelogId = changelogId;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id that = (Id) o;
            return Objects.equals(changelogId, that.changelogId) &&
                    Objects.equals(tag, that.tag);
        }

        @Override
        public int hashCode() {

            return Objects.hash(changelogId, tag);
        }

        @Override
        public String toString() {
            return "Id{" +
                    "changelogId=" + changelogId +
                    ", tag='" + tag + '\'' +
                    '}';
        }
    }
}
