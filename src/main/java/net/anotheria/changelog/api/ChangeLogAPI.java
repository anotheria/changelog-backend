package net.anotheria.changelog.api;

import net.anotheria.anoplass.api.API;
import net.anotheria.anoplass.api.APIException;

import java.util.List;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 13.04.22 14:49
 */
public interface ChangeLogAPI extends API {
	List<ChangeLogAO> getEntries() throws APIException;

	List<ChangeLogAO> searchEntries(String criteria) throws APIException;
}
