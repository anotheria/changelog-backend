package net.anotheria.changelog.api.changelog;

import net.anotheria.changelog.api.changelog.bean.ChangeLogAO;
import net.anotheria.changelog.biz.changelog.bean.ChangeLogBO;

import java.util.List;
import java.util.stream.Collectors;

public final class ChangeLogObjectMapper {

    public static List<ChangeLogAO> mapItems(List<ChangeLogBO> toMap) {
        return toMap.stream().map(ChangeLogObjectMapper::map).collect(Collectors.toList());
    }

    public static ChangeLogBO map(ChangeLogAO toMap) {
        ChangeLogBO result = new ChangeLogBO();
        result.setId(toMap.getId());
        result.setAuthor(toMap.getAuthor());
        result.setMessage(toMap.getMessage());
        result.setReason(toMap.getReason());
        result.setTags(toMap.getTags());
        result.setType(toMap.getType());
        result.setTimestamp(toMap.getTimestamp());
        return result;
    }

    public static ChangeLogAO map(ChangeLogBO toMap) {
        ChangeLogAO result = new ChangeLogAO();
        result.setId(toMap.getId());
        result.setAuthor(toMap.getAuthor());
        result.setMessage(toMap.getMessage());
        result.setReason(toMap.getReason());
        result.setTags(toMap.getTags());
        result.setType(toMap.getType());
        result.setTimestamp(toMap.getTimestamp());
        return result;
    }


}
