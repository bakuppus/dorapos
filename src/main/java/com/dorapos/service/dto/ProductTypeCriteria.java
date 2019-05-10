package com.dorapos.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.dorapos.domain.ProductType} entity. This class is used
 * in {@link com.dorapos.web.rest.ProductTypeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /product-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProductTypeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter productType;

    private StringFilter productTypeDescription;

    private LongFilter shopId;

    public ProductTypeCriteria(){
    }

    public ProductTypeCriteria(ProductTypeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.productType = other.productType == null ? null : other.productType.copy();
        this.productTypeDescription = other.productTypeDescription == null ? null : other.productTypeDescription.copy();
        this.shopId = other.shopId == null ? null : other.shopId.copy();
    }

    @Override
    public ProductTypeCriteria copy() {
        return new ProductTypeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getProductType() {
        return productType;
    }

    public void setProductType(StringFilter productType) {
        this.productType = productType;
    }

    public StringFilter getProductTypeDescription() {
        return productTypeDescription;
    }

    public void setProductTypeDescription(StringFilter productTypeDescription) {
        this.productTypeDescription = productTypeDescription;
    }

    public LongFilter getShopId() {
        return shopId;
    }

    public void setShopId(LongFilter shopId) {
        this.shopId = shopId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductTypeCriteria that = (ProductTypeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(productType, that.productType) &&
            Objects.equals(productTypeDescription, that.productTypeDescription) &&
            Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        productType,
        productTypeDescription,
        shopId
        );
    }

    @Override
    public String toString() {
        return "ProductTypeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (productType != null ? "productType=" + productType + ", " : "") +
                (productTypeDescription != null ? "productTypeDescription=" + productTypeDescription + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
            "}";
    }

}
