package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.ProductCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductCategory} and its DTO {@link ProductCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShopMapper.class})
public interface ProductCategoryMapper extends EntityMapper<ProductCategoryDTO, ProductCategory> {

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopShopName")
    ProductCategoryDTO toDto(ProductCategory productCategory);

    @Mapping(source = "shopId", target = "shop")
    @Mapping(target = "products", ignore = true)
    ProductCategory toEntity(ProductCategoryDTO productCategoryDTO);

    default ProductCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        return productCategory;
    }
}
