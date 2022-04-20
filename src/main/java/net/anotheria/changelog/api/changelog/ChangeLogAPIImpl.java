package net.anotheria.changelog.api.changelog;

import net.anotheria.anoplass.api.APIException;
import net.anotheria.anoplass.api.APIInitException;
import net.anotheria.anoplass.api.AbstractAPIImpl;
import net.anotheria.changelog.api.changelog.bean.ChangeLogAO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 13.04.22 14:49
 */
public class ChangeLogAPIImpl extends AbstractAPIImpl implements ChangeLogAPI {


	private List<ChangeLogAO> dummyData;

	@Override
	public void init() throws APIInitException {
		super.init();

		dummyData = generateDummyData();


	}

	static Random dummyRnd = new Random(System.nanoTime());

	private static<T>  T dummyPickOne(T[] array){
		return array[dummyRnd.nextInt(array.length)];
	}

	private List<ChangeLogAO> generateDummyData(){
		ArrayList<ChangeLogAO> data = new ArrayList<>();
		String AUTHORS[] = {"Leon", "Sofia", "Patric"};
		String CHANGES[] = {"Killed everything", "Released new version", "wrote a book", "bought a chicken", "cooked a meal"};
		String REASONS[] = {"Needed money", "Needed glory", "Ebi said so", "War happened"};
		String TAGS[] = {"desktop", "mobile", "payment", "statistic", "release", "tcl"};

		for (int i=0; i<10; i++){
			ChangeLogAO ao = new ChangeLogAO();
			ao.setAuthor(dummyPickOne(AUTHORS));
			ao.setReason(dummyPickOne(REASONS));
			ao.setMessage(dummyPickOne(CHANGES));
			ao.setType(dummyPickOne(ChangeLogAO.ChangeLogType.values()));
			ao.setTimestamp(System.currentTimeMillis()-dummyRnd.nextInt(1000*3600*24));
			ao.addTag(dummyPickOne(TAGS));
			ao.addTag(dummyPickOne(TAGS));
			data.add(ao);

		}

		return data;
	}

	@Override
	public List<ChangeLogAO> getEntries() throws APIException {
		return dummyData;
	}

	@Override
	public List<ChangeLogAO> searchEntries(String criteria) throws APIException {

		List<ChangeLogAO> entries = getEntries();
		LinkedList<ChangeLogAO> ret = new LinkedList<>();

		for (ChangeLogAO entry : entries){
			if (entry.doesMatch(criteria))
				ret.add(entry);
		}

		return ret;
	}

}
