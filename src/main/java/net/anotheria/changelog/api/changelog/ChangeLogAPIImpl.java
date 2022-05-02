package net.anotheria.changelog.api.changelog;

import net.anotheria.anoplass.api.APIException;
import net.anotheria.anoplass.api.APIInitException;
import net.anotheria.anoplass.api.AbstractAPIImpl;
import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.anoprise.metafactory.MetaFactoryException;
import net.anotheria.changelog.api.changelog.bean.ChangeLogAO;
import net.anotheria.changelog.biz.changelog.ChangeLogService;
import net.anotheria.changelog.biz.changelog.bean.ChangeLogBO;
import net.anotheria.changelog.biz.changelog.exception.ChangeLogServiceException;
import net.anotheria.changelog.biz.changelog.bean.ChangeLogType;

import java.util.ArrayList;
import java.util.Arrays;
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

	private ChangeLogService changeLogService;

	private List<ChangeLogAO> dummyData;

	@Override
	public void init() throws APIInitException {
		super.init();

		try {
			changeLogService = MetaFactory.get(ChangeLogService.class);
		} catch (MetaFactoryException e) {
			String failMsg = "MetaFactory failed during initialization";
			log.error(failMsg, e);
			throw new APIInitException(failMsg, e);
		}
		
		dummyData = generateDummyData();
	}

	@Override
	public int save(ChangeLogAO changeLogAO) throws APIException {
		try {
			ChangeLogBO changeLogBO = ChangeLogObjectMapper.map(changeLogAO);
			return changeLogService.save(changeLogBO);
		} catch (ChangeLogServiceException e) {
			log.error(e.getMessage(), e);
			throw new APIException(e.getMessage(), e);
		}
	}

	@Override
	public ChangeLogAO get(int id) throws APIException {
		try {
			ChangeLogBO changeLogBO = changeLogService.get(id);
			return ChangeLogObjectMapper.map(changeLogBO);
		} catch (ChangeLogServiceException e) {
			log.error(e.getMessage(), e);
			throw new APIException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(int id) throws APIException {
		try {
			changeLogService.delete(id);
		} catch (ChangeLogServiceException e) {
			log.error(e.getMessage(), e);
			throw new APIException(e.getMessage(), e);
		}
	}

	@Override
	public List<ChangeLogAO> list() throws APIException {
		try {
			List<ChangeLogBO> changeLogBOList = changeLogService.list();
			return ChangeLogObjectMapper.mapItems(changeLogBOList);
		} catch (ChangeLogServiceException e) {
			log.error(e.getMessage(), e);
			throw new APIException(e.getMessage(), e);
		}
	}

    @Override
    public List<ChangeLogType> getTypes() throws APIException {
        return Arrays.asList(ChangeLogType.values());
    }

    @Override
    public List<String> getTags() throws APIException {
        try {
            return changeLogService.getTags();
        } catch (ChangeLogServiceException e) {
            log.error(e.getMessage(), e);
            throw new APIException(e.getMessage(), e);
        }
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
			ao.setType(dummyPickOne(ChangeLogType.values()));
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
