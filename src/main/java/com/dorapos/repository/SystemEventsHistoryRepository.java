package com.dorapos.repository;

import com.dorapos.domain.SystemEventsHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SystemEventsHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemEventsHistoryRepository extends JpaRepository<SystemEventsHistory, Long>, JpaSpecificationExecutor<SystemEventsHistory> {

}
