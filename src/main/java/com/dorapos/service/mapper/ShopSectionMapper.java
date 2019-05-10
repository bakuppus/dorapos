package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.ShopSectionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShopSection} and its DTO {@link ShopSectionDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShopMapper.class})
public interface ShopSectionMapper extends EntityMapper<ShopSectionDTO, ShopSection> {

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopShopName")
    ShopSectionDTO toDto(ShopSection shopSection);

    @Mapping(source = "shopId", target = "shop")
    ShopSection toEntity(ShopSectionDTO shopSectionDTO);

    default ShopSection fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShopSection shopSection = new ShopSection();
        shopSection.setId(id);
        return shopSection;
    }
}
