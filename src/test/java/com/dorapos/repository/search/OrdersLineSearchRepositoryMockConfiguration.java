package com.dorapos.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link OrdersLineSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class OrdersLineSearchRepositoryMockConfiguration {

    @MockBean
    private OrdersLineSearchRepository mockOrdersLineSearchRepository;

}
