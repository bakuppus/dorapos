package com.dorapos.repository;

import com.dorapos.domain.PaymentMethod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PaymentMethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long>, JpaSpecificationExecutor<PaymentMethod> {

}
