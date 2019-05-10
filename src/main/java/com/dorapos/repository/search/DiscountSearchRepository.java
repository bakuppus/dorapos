package com.dorapos.repository.search;

import com.dorapos.domain.Discount;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Discount} entity.
 */
public interface DiscountSearchRepository extends ElasticsearchRepository<Discount, Long> {
}
