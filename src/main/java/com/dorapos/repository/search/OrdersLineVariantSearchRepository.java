package com.dorapos.repository.search;

import com.dorapos.domain.OrdersLineVariant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link OrdersLineVariant} entity.
 */
public interface OrdersLineVariantSearchRepository extends ElasticsearchRepository<OrdersLineVariant, Long> {
}
