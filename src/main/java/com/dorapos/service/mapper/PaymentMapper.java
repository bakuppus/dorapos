package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.PaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payment} and its DTO {@link PaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShopMapper.class, ProfileMapper.class, PaymentMethodMapper.class, OrdersMapper.class})
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopShopName")
    @Mapping(source = "processedBy.id", target = "processedById")
    @Mapping(source = "processedBy.firstName", target = "processedByFirstName")
    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    @Mapping(source = "paymentMethod.paymentMethod", target = "paymentMethodPaymentMethod")
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "order.description", target = "orderDescription")
    PaymentDTO toDto(Payment payment);

    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "processedById", target = "processedBy")
    @Mapping(source = "paymentMethodId", target = "paymentMethod")
    @Mapping(source = "orderId", target = "order")
    Payment toEntity(PaymentDTO paymentDTO);

    default Payment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(id);
        return payment;
    }
}
