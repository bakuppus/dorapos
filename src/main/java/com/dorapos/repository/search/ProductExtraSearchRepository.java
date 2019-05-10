package com.dorapos.repository.search;

import com.dorapos.domain.ProductExtra;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ProductExtra} entity.
 */
public interface ProductExtraSearchRepository extends ElasticsearchRepository<ProductExtra, Long> {
}
