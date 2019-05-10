package com.dorapos.repository.search;

import com.dorapos.domain.SystemEventsHistory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SystemEventsHistory} entity.
 */
public interface SystemEventsHistorySearchRepository extends ElasticsearchRepository<SystemEventsHistory, Long> {
}
