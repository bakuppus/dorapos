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
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the {@link com.dorapos.domain.Product} entity. This class is used
 * in {@link com.dorapos.web.rest.ProductResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /products?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProductCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter productName;

    private StringFilter productDescription;

    private BigDecimalFilter price;

    private IntegerFilter quantity;

    private StringFilter productImageFullUrl;

    private StringFilter productImageThumbUrl;

    private ZonedDateTimeFilter dateCreated;

    private StringFilter barcode;

    private StringFilter serialCode;

    private IntegerFilter priorityPosition;

    private BooleanFilter active;

    private BooleanFilter isVariantProduct;

    private LongFilter variantsId;

    private LongFilter extrasId;

    private LongFilter productTypesId;

    private LongFilter shopId;

    private LongFilter discountsId;

    private LongFilter taxesId;

    private LongFilter categoryId;

    public ProductCriteria(){
    }

    public ProductCriteria(ProductCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.productName = other.productName == null ? null : other.productName.copy();
        this.productDescription = other.productDescription == null ? null : other.productDescription.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.quantity = other.quantity == null ? null : other.quantity.copy();
        this.productImageFullUrl = other.productImageFullUrl == null ? null : other.productImageFullUrl.copy();
        this.productImageThumbUrl = other.productImageThumbUrl == null ? null : other.productImageThumbUrl.copy();
        this.dateCreated = other.dateCreated == null ? null : other.dateCreated.copy();
        this.barcode = other.barcode == null ? null : other.barcode.copy();
        this.serialCode = other.serialCode == null ? null : other.serialCode.copy();
        this.priorityPosition = other.priorityPosition == null ? null : other.priorityPosition.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.isVariantProduct = other.isVariantProduct == null ? null : other.isVariantProduct.copy();
        this.variantsId = other.variantsId == null ? null : other.variantsId.copy();
        this.extrasId = other.extrasId == null ? null : other.extrasId.copy();
        this.productTypesId = other.productTypesId == null ? null : other.productTypesId.copy();
        this.shopId = other.shopId == null ? null : other.shopId.copy();
        this.discountsId = other.discountsId == null ? null : other.discountsId.copy();
        this.taxesId = other.taxesId == null ? null : other.taxesId.copy();
        this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
    }

    @Override
    public ProductCriteria copy() {
        return new ProductCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getProductName() {
        return productName;
    }

    public void setProductName(StringFilter productName) {
        this.productName = productName;
    }

    public StringFilter getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(StringFilter productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimalFilter getPrice() {
        return price;
    }

    public void setPrice(BigDecimalFilter price) {
        this.price = price;
    }

    public IntegerFilter getQuantity() {
        return quantity;
    }

    public void setQuantity(IntegerFilter quantity) {
        this.quantity = quantity;
    }

    public StringFilter getProductImageFullUrl() {
        return productImageFullUrl;
    }

    public void setProductImageFullUrl(StringFilter productImageFullUrl) {
        this.productImageFullUrl = productImageFullUrl;
    }

    public StringFilter getProductImageThumbUrl() {
        return productImageThumbUrl;
    }

    public void setProductImageThumbUrl(StringFilter productImageThumbUrl) {
        this.productImageThumbUrl = productImageThumbUrl;
    }

    public ZonedDateTimeFilter getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTimeFilter dateCreated) {
        this.dateCreated = dateCreated;
    }

    public StringFilter getBarcode() {
        return barcode;
    }

    public void setBarcode(StringFilter barcode) {
        this.barcode = barcode;
    }

    public StringFilter getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(StringFilter serialCode) {
        this.serialCode = serialCode;
    }

    public IntegerFilter getPriorityPosition() {
        return priorityPosition;
    }

    public void setPriorityPosition(IntegerFilter priorityPosition) {
        this.priorityPosition = priorityPosition;
    }

    public BooleanFilter getActive() {
        return active;
    }

    public void setActive(BooleanFilter active) {
        this.active = active;
    }

    public BooleanFilter getIsVariantProduct() {
        return isVariantProduct;
    }

    public void setIsVariantProduct(BooleanFilter isVariantProduct) {
        this.isVariantProduct = isVariantProduct;
    }

    public LongFilter getVariantsId() {
        return variantsId;
    }

    public void setVariantsId(LongFilter variantsId) {
        this.variantsId = variantsId;
    }

    public LongFilter getExtrasId() {
        return extrasId;
    }

    public void setExtrasId(LongFilter extrasId) {
        this.extrasId = extrasId;
    }

    public LongFilter getProductTypesId() {
        return productTypesId;
    }

    public void setProductTypesId(LongFilter productTypesId) {
        this.productTypesId = productTypesId;
    }

    public LongFilter getShopId() {
        return shopId;
    }

    public void setShopId(LongFilter shopId) {
        this.shopId = shopId;
    }

    public LongFilter getDiscountsId() {
        return discountsId;
    }

    public void setDiscountsId(LongFilter discountsId) {
        this.discountsId = discountsId;
    }

    public LongFilter getTaxesId() {
        return taxesId;
    }

    public void setTaxesId(LongFilter taxesId) {
        this.taxesId = taxesId;
    }

    public LongFilter getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(LongFilter categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductCriteria that = (ProductCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(productName, that.productName) &&
            Objects.equals(productDescription, that.productDescription) &&
            Objects.equals(price, that.price) &&
            Objects.equals(quantity, that.quantity) &&
            Objects.equals(productImageFullUrl, that.productImageFullUrl) &&
            Objects.equals(productImageThumbUrl, that.productImageThumbUrl) &&
            Objects.equals(dateCreated, that.dateCreated) &&
            Objects.equals(barcode, that.barcode) &&
            Objects.equals(serialCode, that.serialCode) &&
            Objects.equals(priorityPosition, that.priorityPosition) &&
            Objects.equals(active, that.active) &&
            Objects.equals(isVariantProduct, that.isVariantProduct) &&
            Objects.equals(variantsId, that.variantsId) &&
            Objects.equals(extrasId, that.extrasId) &&
            Objects.equals(productTypesId, that.productTypesId) &&
            Objects.equals(shopId, that.shopId) &&
            Objects.equals(discountsId, that.discountsId) &&
            Objects.equals(taxesId, that.taxesId) &&
            Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        productName,
        productDescription,
        price,
        quantity,
        productImageFullUrl,
        productImageThumbUrl,
        dateCreated,
        barcode,
        serialCode,
        priorityPosition,
        active,
        isVariantProduct,
        variantsId,
        extrasId,
        productTypesId,
        shopId,
        discountsId,
        taxesId,
        categoryId
        );
    }

    @Override
    public String toString() {
        return "ProductCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (productName != null ? "productName=" + productName + ", " : "") +
                (productDescription != null ? "productDescription=" + productDescription + ", " : "") +
                (price != null ? "price=" + price + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (productImageFullUrl != null ? "productImageFullUrl=" + productImageFullUrl + ", " : "") +
                (productImageThumbUrl != null ? "productImageThumbUrl=" + productImageThumbUrl + ", " : "") +
                (dateCreated != null ? "dateCreated=" + dateCreated + ", " : "") +
                (barcode != null ? "barcode=" + barcode + ", " : "") +
                (serialCode != null ? "serialCode=" + serialCode + ", " : "") +
                (priorityPosition != null ? "priorityPosition=" + priorityPosition + ", " : "") +
                (active != null ? "active=" + active + ", " : "") +
                (isVariantProduct != null ? "isVariantProduct=" + isVariantProduct + ", " : "") +
                (variantsId != null ? "variantsId=" + variantsId + ", " : "") +
                (extrasId != null ? "extrasId=" + extrasId + ", " : "") +
                (productTypesId != null ? "productTypesId=" + productTypesId + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
                (discountsId != null ? "discountsId=" + discountsId + ", " : "") +
                (taxesId != null ? "taxesId=" + taxesId + ", " : "") +
                (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
            "}";
    }

}
