package com.dorapos.repository;

import com.dorapos.domain.SectionTable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SectionTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SectionTableRepository extends JpaRepository<SectionTable, Long>, JpaSpecificationExecutor<SectionTable> {

}
