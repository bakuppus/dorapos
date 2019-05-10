package com.dorapos.repository;

import com.dorapos.domain.Shop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Shop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>, JpaSpecificationExecutor<Shop> {

}
