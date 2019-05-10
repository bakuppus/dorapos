package com.dorapos.repository.search;

import com.dorapos.domain.OrdersLine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link OrdersLine} entity.
 */
public interface OrdersLineSearchRepository extends ElasticsearchRepository<OrdersLine, Long> {
}
