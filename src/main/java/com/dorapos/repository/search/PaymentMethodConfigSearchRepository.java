package com.dorapos.repository.search;

import com.dorapos.domain.PaymentMethodConfig;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentMethodConfig} entity.
 */
public interface PaymentMethodConfigSearchRepository extends ElasticsearchRepository<PaymentMethodConfig, Long> {
}
