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

/**
 * Criteria class for the {@link com.dorapos.domain.ProductVariant} entity. This class is used
 * in {@link com.dorapos.web.rest.ProductVariantResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /product-variants?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProductVariantCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter variantName;

    private StringFilter description;

    private FloatFilter percentage;

    private StringFilter fullPhotoUrl;

    private StringFilter thumbnailPhotoUrl;

    private BigDecimalFilter price;

    private LongFilter productId;

    public ProductVariantCriteria(){
    }

    public ProductVariantCriteria(ProductVariantCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.variantName = other.variantName == null ? null : other.variantName.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.percentage = other.percentage == null ? null : other.percentage.copy();
        this.fullPhotoUrl = other.fullPhotoUrl == null ? null : other.fullPhotoUrl.copy();
        this.thumbnailPhotoUrl = other.thumbnailPhotoUrl == null ? null : other.thumbnailPhotoUrl.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.productId = other.productId == null ? null : other.productId.copy();
    }

    @Override
    public ProductVariantCriteria copy() {
        return new ProductVariantCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getVariantName() {
        return variantName;
    }

    public void setVariantName(StringFilter variantName) {
        this.variantName = variantName;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public FloatFilter getPercentage() {
        return percentage;
    }

    public void setPercentage(FloatFilter percentage) {
        this.percentage = percentage;
    }

    public StringFilter getFullPhotoUrl() {
        return fullPhotoUrl;
    }

    public void setFullPhotoUrl(StringFilter fullPhotoUrl) {
        this.fullPhotoUrl = fullPhotoUrl;
    }

    public StringFilter getThumbnailPhotoUrl() {
        return thumbnailPhotoUrl;
    }

    public void setThumbnailPhotoUrl(StringFilter thumbnailPhotoUrl) {
        this.thumbnailPhotoUrl = thumbnailPhotoUrl;
    }

    public BigDecimalFilter getPrice() {
        return price;
    }

    public void setPrice(BigDecimalFilter price) {
        this.price = price;
    }

    public LongFilter getProductId() {
        return productId;
    }

    public void setProductId(LongFilter productId) {
        this.productId = productId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductVariantCriteria that = (ProductVariantCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(variantName, that.variantName) &&
            Objects.equals(description, that.description) &&
            Objects.equals(percentage, that.percentage) &&
            Objects.equals(fullPhotoUrl, that.fullPhotoUrl) &&
            Objects.equals(thumbnailPhotoUrl, that.thumbnailPhotoUrl) &&
            Objects.equals(price, that.price) &&
            Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        variantName,
        description,
        percentage,
        fullPhotoUrl,
        thumbnailPhotoUrl,
        price,
        productId
        );
    }

    @Override
    public String toString() {
        return "ProductVariantCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (variantName != null ? "variantName=" + variantName + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (percentage != null ? "percentage=" + percentage + ", " : "") +
                (fullPhotoUrl != null ? "fullPhotoUrl=" + fullPhotoUrl + ", " : "") +
                (thumbnailPhotoUrl != null ? "thumbnailPhotoUrl=" + thumbnailPhotoUrl + ", " : "") +
                (price != null ? "price=" + price + ", " : "") +
                (productId != null ? "productId=" + productId + ", " : "") +
            "}";
    }

}
