package com.dorapos.repository.search;

import com.dorapos.domain.ShopChange;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ShopChange} entity.
 */
public interface ShopChangeSearchRepository extends ElasticsearchRepository<ShopChange, Long> {
}
