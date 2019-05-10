package com.dorapos.repository.search;

import com.dorapos.domain.EmailBalancer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link EmailBalancer} entity.
 */
public interface EmailBalancerSearchRepository extends ElasticsearchRepository<EmailBalancer, Long> {
}
