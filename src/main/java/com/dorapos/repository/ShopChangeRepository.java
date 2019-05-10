package com.dorapos.repository;

import com.dorapos.domain.ShopChange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ShopChange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopChangeRepository extends JpaRepository<ShopChange, Long>, JpaSpecificationExecutor<ShopChange> {

}