package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.ShopChangeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShopChange} and its DTO {@link ShopChangeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShopMapper.class, ProfileMapper.class})
public interface ShopChangeMapper extends EntityMapper<ShopChangeDTO, ShopChange> {

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopShopName")
    @Mapping(source = "changedBy.id", target = "changedById")
    @Mapping(source = "changedBy.firstName", target = "changedByFirstName")
    ShopChangeDTO toDto(ShopChange shopChange);

    @Mapping(source = "shopId", target = "shop")
    @Mapping(source = "changedById", target = "changedBy")
    ShopChange toEntity(ShopChangeDTO shopChangeDTO);

    default ShopChange fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShopChange shopChange = new ShopChange();
        shopChange.setId(id);
        return shopChange;
    }
}
