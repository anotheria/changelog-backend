package net.anotheria.changelog.api.changelog.bean;

import net.anotheria.util.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 13.04.22 14:50
 */
public class ChangeLogAO {

	private long timestamp;
	private String author;
	private String message;
	private String reason;
	private List<String> tags = new ArrayList<>();
	private ChangeLogType type;
	private String dateAsString;

	public void addTag(String tag) {
		tags.add(tag);
	}

	public void setTimestamp(long l) {
		timestamp = l;
		dateAsString = NumberUtils.makeDigitalDateString(l)+" "+NumberUtils.makeTimeString(l);
	}

	public enum ChangeLogType{
		CHANGE, CRASH;
	};

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

	public boolean doesMatch(String criteria){
		if (criteria==null)
			criteria = "";
		criteria = criteria.toLowerCase().trim();
		if (type!=null && type.name().toLowerCase().equals(criteria))
			return true;

		if (doesMatch(author, criteria))
			return true;
		if (doesMatch(message, criteria))
			return true;
		if (doesMatch(reason, criteria))
			return true;

		if (tags !=null) {
			for (String tag : tags) {
				if (tag.toLowerCase().equals(criteria))
					return true;
			}
		}

		return false;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getDateAsString() {
		return dateAsString;
	}

	public boolean doesMatch(String partOfMe, String criteria){
		if (partOfMe == null || partOfMe.length()==0)
			return false;

		return partOfMe.toLowerCase().contains(criteria);
	}
}
