package com.dorapos.repository.search;

import com.dorapos.domain.SectionTable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SectionTable} entity.
 */
public interface SectionTableSearchRepository extends ElasticsearchRepository<SectionTable, Long> {
}
