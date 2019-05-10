package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.OrdersLineExtraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrdersLineExtra} and its DTO {@link OrdersLineExtraDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrdersLineVariantMapper.class})
public interface OrdersLineExtraMapper extends EntityMapper<OrdersLineExtraDTO, OrdersLineExtra> {

    @Mapping(source = "ordersLineVariant.id", target = "ordersLineVariantId")
    OrdersLineExtraDTO toDto(OrdersLineExtra ordersLineExtra);

    @Mapping(source = "ordersLineVariantId", target = "ordersLineVariant")
    OrdersLineExtra toEntity(OrdersLineExtraDTO ordersLineExtraDTO);

    default OrdersLineExtra fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrdersLineExtra ordersLineExtra = new OrdersLineExtra();
        ordersLineExtra.setId(id);
        return ordersLineExtra;
    }
}
