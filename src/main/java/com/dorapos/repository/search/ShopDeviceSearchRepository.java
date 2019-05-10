package com.dorapos.repository.search;

import com.dorapos.domain.ShopDevice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ShopDevice} entity.
 */
public interface ShopDeviceSearchRepository extends ElasticsearchRepository<ShopDevice, Long> {
}
