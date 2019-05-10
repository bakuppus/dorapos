package com.dorapos.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link EmployeeTimesheetSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class EmployeeTimesheetSearchRepositoryMockConfiguration {

    @MockBean
    private EmployeeTimesheetSearchRepository mockEmployeeTimesheetSearchRepository;

}
