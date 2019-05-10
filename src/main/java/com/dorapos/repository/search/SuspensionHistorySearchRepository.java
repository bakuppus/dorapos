package com.dorapos.repository.search;

import com.dorapos.domain.SuspensionHistory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SuspensionHistory} entity.
 */
public interface SuspensionHistorySearchRepository extends ElasticsearchRepository<SuspensionHistory, Long> {
}
