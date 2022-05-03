package net.anotheria.changelog.biz.changelog.bean;

import net.anotheria.changelog.biz.changelog.persistence.ChangeLogEntity;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogTagEntity;

import java.util.Date;
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
        result.setTags(toMap.getTags().stream().map(ChangeLogTagEntity::getTag).collect(Collectors.toList()));
        result.setType(toMap.getType());
        result.setTimestamp(toMap.getTimestamp().getTime());
        return result;
    }

    public static ChangeLogEntity map(ChangeLogBO toMap) {
        ChangeLogEntity result = new ChangeLogEntity();
        result.setId(toMap.getId());
        result.setAuthor(toMap.getAuthor());
        result.setMessage(toMap.getMessage());
        result.setReason(toMap.getReason());
        result.setTags(toMap.getTags().stream().map(tag -> new ChangeLogTagEntity(new ChangeLogTagEntity.Id(toMap.getId(), tag))).collect(Collectors.toList()));
        result.setType(toMap.getType());
        result.setTimestamp(new Date(toMap.getTimestamp()));
        return result;
    }
}
