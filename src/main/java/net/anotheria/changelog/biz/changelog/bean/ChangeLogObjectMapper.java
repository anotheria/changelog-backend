package net.anotheria.changelog.biz.changelog.bean;

import net.anotheria.changelog.biz.changelog.persistence.ChangeLogEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class ChangeLogObjectMapper {

    public static List<ChangeLogBO> mapItems(List<ChangeLogEntity> toMap) {
        return toMap.stream().map(ChangeLogObjectMapper::map).collect(Collectors.toList());
    }

    public static ChangeLogBO map(ChangeLogEntity toMap) {
        ChangeLogBO result = new ChangeLogBO();
        result.setId(toMap.getId());
        result.setAuthor(toMap.getAuthor());
        result.setMessage(toMap.getMessage());
        result.setReason(toMap.getReason());
        //result.setTags(toMap.getTags());
        result.setType(toMap.getType());
        result.setTimestamp(toMap.getTimestamp());
        return result;
    }

    public static ChangeLogEntity map(ChangeLogBO toMap) {
        ChangeLogEntity result = new ChangeLogEntity();
        result.setId(toMap.getId());
        result.setAuthor(toMap.getAuthor());
        result.setMessage(toMap.getMessage());
        result.setReason(toMap.getReason());
        //result.setTags(toMap.getTags());
        result.setType(toMap.getType());
        result.setTimestamp(toMap.getTimestamp());
        return result;
    }
}
