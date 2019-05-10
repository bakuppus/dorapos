package com.dorapos.repository.search;

import com.dorapos.domain.ShopSection;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ShopSection} entity.
 */
public interface ShopSectionSearchRepository extends ElasticsearchRepository<ShopSection, Long> {
}
