package com.dorapos.repository.search;

import com.dorapos.domain.EmployeeTimesheet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link EmployeeTimesheet} entity.
 */
public interface EmployeeTimesheetSearchRepository extends ElasticsearchRepository<EmployeeTimesheet, Long> {
}
