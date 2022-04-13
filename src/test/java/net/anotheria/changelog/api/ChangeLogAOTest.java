package net.anotheria.changelog.api;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 13.04.22 15:14
 */
public class ChangeLogAOTest {
	@Test
	public void testSearchAuthor(){
		ChangeLogAO object = createTestEntry();
		assertFalse(object.doesMatch("Pushkin"));
		assertFalse(object.doesMatch("Hamlet"));
		assertTrue(object.doesMatch("Shake"));
		assertTrue(object.doesMatch("SPEAR"));
		assertTrue(object.doesMatch("ShakeSPear"));
		assertFalse(object.doesMatch("Shakespeares"));
	}

	public void testSearchType(){
		ChangeLogAO object = createTestEntry();
		assertFalse(object.doesMatch("chang"));
		assertFalse(object.doesMatch("c"));
		assertFalse(object.doesMatch("crash"));
		assertFalse(object.doesMatch("CRASH"));

		assertTrue(object.doesMatch("CHANGE"));
		assertTrue(object.doesMatch("change"));
	}

	private ChangeLogAO createTestEntry(){
		ChangeLogAO entry = new ChangeLogAO();

		entry.setAuthor("Shakespeare");
		entry.setType(ChangeLogAO.ChangeLogType.CHANGE);
		entry.setMessage("Wrote a Novel");
		entry.setReason("For money");
		entry.addTag("novel"); entry.addTag("pencil");

		return entry;
	}
}
