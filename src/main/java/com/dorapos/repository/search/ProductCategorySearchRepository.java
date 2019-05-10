package com.dorapos.repository.search;

import com.dorapos.domain.ProductCategory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ProductCategory} entity.
 */
public interface ProductCategorySearchRepository extends ElasticsearchRepository<ProductCategory, Long> {
}
