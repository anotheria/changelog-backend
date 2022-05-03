package net.anotheria.changelog.biz.changelog;

import net.anotheria.changelog.biz.changelog.bean.ChangeLogBO;
import net.anotheria.changelog.biz.changelog.bean.ChangeLogObjectMapper;
import net.anotheria.changelog.biz.changelog.exception.ChangeLogNotFoundServiceException;
import net.anotheria.changelog.biz.changelog.exception.ChangeLogServiceException;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogEntity;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogRepository;
import net.anotheria.changelog.biz.changelog.persistence.ChangeLogTagEntity;
import net.anotheria.changelog.biz.changelog.specification.ChangeLogFilterSpecificationBuilder;
import net.anotheria.changelog.biz.changelog.specification.ChangeLogSearchCriteria;
import net.anotheria.changelog.biz.changelog.specification.ChangeLogSortProperty;
import net.anotheria.changelog.biz.changelog.specification.ChangeLogSpecifications;
import net.anotheria.changelog.biz.shared.filter.Order;
import net.anotheria.changelog.biz.shared.filter.PagingResult;
import net.anotheria.changelog.biz.shared.filter.SearchResult;
import net.anotheria.changelog.biz.shared.filter.criteria.SortCriteria;
import net.anotheria.changelog.biz.shared.filter.util.SpecificationsFactory;
import net.anotheria.changelog.biz.shared.time.TimeRange;
import net.anotheria.changelog.biz.util.persistence.PageRequestHelper;
import net.anotheria.moskito.aop.annotation.Monitor;
import net.anotheria.util.concurrency.IdBasedLock;
import net.anotheria.util.concurrency.IdBasedLockManager;
import net.anotheria.util.concurrency.SafeIdBasedLockManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = ChangeLogServiceException.class)
    public int save(ChangeLogBO changeLog) throws ChangeLogServiceException {
        IdBasedLock<Integer> lock = lockManager.obtainLock(changeLog.getId());
        lock.lock();
        try {
            ChangeLogEntity toSave = ChangeLogObjectMapper.map(changeLog);
            if (toSave.getId() == null) {
                List<ChangeLogTagEntity> tags = toSave.getTags();
                toSave.setTags(null);
                toSave = repository.save(toSave);
                Integer id = toSave.getId();
                tags.forEach(t -> t.getId().setChangelogId(id));
                toSave.setTags(tags);
            }
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
    public SearchResult<ChangeLogBO, ChangeLogSortProperty> list(ChangeLogSearchCriteria criteria) throws ChangeLogServiceException {
        try {
            if (criteria.getSort() == null || criteria.getSort().isEmpty()) {
                criteria.addSortable(new SortCriteria<>(Order.DESC, ChangeLogSortProperty.TIMESTAMP));
            }

            Specifications<ChangeLogEntity> searchSpec = Specifications.where(null);
            searchSpec = setCommonSpecifications(searchSpec, criteria);
            PageRequest pageRequest = PageRequestHelper.getPageRequest(criteria);
            Page<ChangeLogEntity> result = repository.findAll(searchSpec, pageRequest);

            PagingResult pagingResult = new PagingResult(criteria.getPaging(), result.getTotalElements());
            return new SearchResult<>(criteria.getSort(), pagingResult, ChangeLogObjectMapper.mapItems(result.getContent()));
        } catch (Exception e) {
            throw new ChangeLogServiceException(String.format("Failed to get list entity"), e);
        }
    }

    @Override
    public List<String> getTags() throws ChangeLogServiceException {
        try {
            return repository.findAllTags();
        } catch (Exception e) {
            throw new ChangeLogServiceException(String.format("Failed to get list entity"), e);
        }
    }

    private Specifications<ChangeLogEntity> setCommonSpecifications(Specifications<ChangeLogEntity> searchSpec, ChangeLogSearchCriteria criteria) {
        searchSpec = searchSpec
                .and(ChangeLogSpecifications.searchTerm(criteria.getSearchTerm()));

        if (criteria.getTimeRange() != null) {
            TimeRange timeRange = criteria.getTimeRange();
            searchSpec = searchSpec.and(SpecificationsFactory.between("timestamp", timeRange.getStartDate(), timeRange.getEndDate()));
        }

        Specifications<ChangeLogEntity> filterSpec = new ChangeLogFilterSpecificationBuilder()
                .withCriteriaFilters(criteria.getFilters())
                .withGroupFilters(criteria.getGroupFilters())
                .build();

        searchSpec = searchSpec.and(filterSpec);
        return searchSpec;
    }
}
