package com.dorapos.repository.search;

import com.dorapos.domain.OrdersLineExtra;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link OrdersLineExtra} entity.
 */
public interface OrdersLineExtraSearchRepository extends ElasticsearchRepository<OrdersLineExtra, Long> {
}
