package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.ProductTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductType} and its DTO {@link ProductTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShopMapper.class})
public interface ProductTypeMapper extends EntityMapper<ProductTypeDTO, ProductType> {

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopShopName")
    ProductTypeDTO toDto(ProductType productType);

    @Mapping(source = "shopId", target = "shop")
    ProductType toEntity(ProductTypeDTO productTypeDTO);

    default ProductType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductType productType = new ProductType();
        productType.setId(id);
        return productType;
    }
}
