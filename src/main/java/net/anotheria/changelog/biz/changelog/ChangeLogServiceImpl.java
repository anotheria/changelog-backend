package net.anotheria.changelog.biz.changelog;

import net.anotheria.changelog.biz.changelog.bean.ChangeLogBO;
import net.anotheria.changelog.biz.changelog.bean.ChangeLogObjectMapper;
import net.anotheria.changelog.biz.changelog.exception.ChangeLogNotFoundServiceException;
import net.anotheria.changelog.biz.changelog.exception.ChangeLogServiceException;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogEntity;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogRepository;
import net.anotheria.moskito.aop.annotation.Monitor;
import net.anotheria.util.concurrency.IdBasedLock;
import net.anotheria.util.concurrency.IdBasedLockManager;
import net.anotheria.util.concurrency.SafeIdBasedLockManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link ChangeLogService} implementation.
 *
 */
@Service
@Monitor
public class ChangeLogServiceImpl implements ChangeLogService {

    private static final Logger log = LoggerFactory.getLogger(ChangeLogServiceImpl.class);

    @Autowired
    private ChangeLogRepository repository;

    private IdBasedLockManager<Integer> lockManager;

    public ChangeLogServiceImpl() {
        this.lockManager = new SafeIdBasedLockManager<>();
    }


    @Override
    public int save(ChangeLogBO changeLog) throws ChangeLogServiceException {
        IdBasedLock<Integer> lock = lockManager.obtainLock(changeLog.getId());
        lock.lock();
        try {
            ChangeLogEntity toSave = ChangeLogObjectMapper.map(changeLog);
            return repository.save(toSave).getId();
        } catch (Exception e) {
            throw new ChangeLogServiceException("Failed to save entity: " + changeLog, e);
        } finally {
            lock.unlock();
        }
    }


    @Override
    public ChangeLogBO get(int id) throws ChangeLogServiceException {
        try {
            ChangeLogEntity changeLogEntity = repository.findOne(id);
            if (changeLogEntity != null) {
                return ChangeLogObjectMapper.map(changeLogEntity);
            }
        } catch (Exception e) {
            throw new ChangeLogServiceException(String.format("Failed to get entity with id:%d", id), e);
        }
        throw new ChangeLogNotFoundServiceException(String.format("No entity found with id:%d", id));
    }
    

    @Override
    public void delete(int id) throws ChangeLogServiceException {
        IdBasedLock<Integer> lock = lockManager.obtainLock(id);
        lock.lock();
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new ChangeLogServiceException(String.format("Failed to delete entity with id:%d", id), e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<ChangeLogBO> list() throws ChangeLogServiceException {
        try {
            List<ChangeLogEntity> entities = repository.findAll();
            return ChangeLogObjectMapper.mapItems(entities);
        } catch (Exception e) {
            throw new ChangeLogServiceException(String.format("Failed to get list entity"), e);
        }
    }
}
