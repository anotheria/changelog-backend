package net.anotheria.changelog.api;

import net.anotheria.anoplass.api.APIFactory;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 13.04.22 14:52
 */
public class ChangeLogAPIFactory implements APIFactory<ChangeLogAPI> {
	@Override
	public ChangeLogAPI createAPI() {
		return new ChangeLogAPIImpl();
	}
}
