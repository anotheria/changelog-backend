package net.anotheria.changelog.biz.changelog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeLogRepository extends JpaRepository<ChangeLogEntity, Integer> {

    @Query(value = "select distinct tag from changelog_tag", nativeQuery = true)
    List<String> findAllTags();

}
