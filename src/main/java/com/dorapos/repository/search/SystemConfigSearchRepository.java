package com.dorapos.repository.search;

import com.dorapos.domain.SystemConfig;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SystemConfig} entity.
 */
public interface SystemConfigSearchRepository extends ElasticsearchRepository<SystemConfig, Long> {
}
