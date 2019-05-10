package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.PaymentMethodConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentMethodConfig} and its DTO {@link PaymentMethodConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaymentMethodMapper.class})
public interface PaymentMethodConfigMapper extends EntityMapper<PaymentMethodConfigDTO, PaymentMethodConfig> {

    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    @Mapping(source = "paymentMethod.paymentMethod", target = "paymentMethodPaymentMethod")
    PaymentMethodConfigDTO toDto(PaymentMethodConfig paymentMethodConfig);

    @Mapping(source = "paymentMethodId", target = "paymentMethod")
    PaymentMethodConfig toEntity(PaymentMethodConfigDTO paymentMethodConfigDTO);

    default PaymentMethodConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentMethodConfig paymentMethodConfig = new PaymentMethodConfig();
        paymentMethodConfig.setId(id);
        return paymentMethodConfig;
    }
}
