package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.PaymentMethodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentMethod} and its DTO {@link PaymentMethodDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShopMapper.class})
public interface PaymentMethodMapper extends EntityMapper<PaymentMethodDTO, PaymentMethod> {

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopShopName")
    PaymentMethodDTO toDto(PaymentMethod paymentMethod);

    @Mapping(source = "shopId", target = "shop")
    PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO);

    default PaymentMethod fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(id);
        return paymentMethod;
    }
}
