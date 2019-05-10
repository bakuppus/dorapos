package com.dorapos.repository.search;

import com.dorapos.domain.ProductVariant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ProductVariant} entity.
 */
public interface ProductVariantSearchRepository extends ElasticsearchRepository<ProductVariant, Long> {
}
