package net.anotheria.changelog.api.changelog;

import net.anotheria.anoplass.api.API;
import net.anotheria.anoplass.api.APIException;
import net.anotheria.changelog.api.changelog.bean.ChangeLogAO;
import net.anotheria.changelog.biz.changelog.bean.ChangeLogType;
import net.anotheria.changelog.biz.changelog.specification.ChangeLogSearchCriteria;
import net.anotheria.changelog.biz.changelog.specification.ChangeLogSortProperty;
import net.anotheria.changelog.biz.shared.filter.SearchResult;

import java.util.List;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 13.04.22 14:49
 */
public interface ChangeLogAPI extends API {

    int save(ChangeLogAO changeLogAO) throws APIException;

    ChangeLogAO get(int id) throws APIException;

    void delete(int id) throws APIException;

    SearchResult<ChangeLogAO, ChangeLogSortProperty> list(ChangeLogSearchCriteria criteria) throws APIException;

    List<ChangeLogAO> getEntries() throws APIException;

	List<ChangeLogAO> searchEntries(String criteria) throws APIException;

    List<ChangeLogType> getTypes() throws APIException;

    List<String> getTags() throws APIException;
}
