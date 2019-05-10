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
 * Criteria class for the {@link com.dorapos.domain.OrdersLine} entity. This class is used
 * in {@link com.dorapos.web.rest.OrdersLineResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /orders-lines?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrdersLineCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ordersLineName;

    private StringFilter ordersLineValue;

    private FloatFilter ordersLinePrice;

    private StringFilter ordersLineDescription;

    private StringFilter fullPhotoUrl;

    private StringFilter thumbnailPhotoUrl;

    private LongFilter ordersId;

    private LongFilter ordersLineVariantsId;

    private LongFilter productId;

    public OrdersLineCriteria(){
    }

    public OrdersLineCriteria(OrdersLineCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ordersLineName = other.ordersLineName == null ? null : other.ordersLineName.copy();
        this.ordersLineValue = other.ordersLineValue == null ? null : other.ordersLineValue.copy();
        this.ordersLinePrice = other.ordersLinePrice == null ? null : other.ordersLinePrice.copy();
        this.ordersLineDescription = other.ordersLineDescription == null ? null : other.ordersLineDescription.copy();
        this.fullPhotoUrl = other.fullPhotoUrl == null ? null : other.fullPhotoUrl.copy();
        this.thumbnailPhotoUrl = other.thumbnailPhotoUrl == null ? null : other.thumbnailPhotoUrl.copy();
        this.ordersId = other.ordersId == null ? null : other.ordersId.copy();
        this.ordersLineVariantsId = other.ordersLineVariantsId == null ? null : other.ordersLineVariantsId.copy();
        this.productId = other.productId == null ? null : other.productId.copy();
    }

    @Override
    public OrdersLineCriteria copy() {
        return new OrdersLineCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOrdersLineName() {
        return ordersLineName;
    }

    public void setOrdersLineName(StringFilter ordersLineName) {
        this.ordersLineName = ordersLineName;
    }

    public StringFilter getOrdersLineValue() {
        return ordersLineValue;
    }

    public void setOrdersLineValue(StringFilter ordersLineValue) {
        this.ordersLineValue = ordersLineValue;
    }

    public FloatFilter getOrdersLinePrice() {
        return ordersLinePrice;
    }

    public void setOrdersLinePrice(FloatFilter ordersLinePrice) {
        this.ordersLinePrice = ordersLinePrice;
    }

    public StringFilter getOrdersLineDescription() {
        return ordersLineDescription;
    }

    public void setOrdersLineDescription(StringFilter ordersLineDescription) {
        this.ordersLineDescription = ordersLineDescription;
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

    public LongFilter getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(LongFilter ordersId) {
        this.ordersId = ordersId;
    }

    public LongFilter getOrdersLineVariantsId() {
        return ordersLineVariantsId;
    }

    public void setOrdersLineVariantsId(LongFilter ordersLineVariantsId) {
        this.ordersLineVariantsId = ordersLineVariantsId;
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
        final OrdersLineCriteria that = (OrdersLineCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ordersLineName, that.ordersLineName) &&
            Objects.equals(ordersLineValue, that.ordersLineValue) &&
            Objects.equals(ordersLinePrice, that.ordersLinePrice) &&
            Objects.equals(ordersLineDescription, that.ordersLineDescription) &&
            Objects.equals(fullPhotoUrl, that.fullPhotoUrl) &&
            Objects.equals(thumbnailPhotoUrl, that.thumbnailPhotoUrl) &&
            Objects.equals(ordersId, that.ordersId) &&
            Objects.equals(ordersLineVariantsId, that.ordersLineVariantsId) &&
            Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ordersLineName,
        ordersLineValue,
        ordersLinePrice,
        ordersLineDescription,
        fullPhotoUrl,
        thumbnailPhotoUrl,
        ordersId,
        ordersLineVariantsId,
        productId
        );
    }

    @Override
    public String toString() {
        return "OrdersLineCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ordersLineName != null ? "ordersLineName=" + ordersLineName + ", " : "") +
                (ordersLineValue != null ? "ordersLineValue=" + ordersLineValue + ", " : "") +
                (ordersLinePrice != null ? "ordersLinePrice=" + ordersLinePrice + ", " : "") +
                (ordersLineDescription != null ? "ordersLineDescription=" + ordersLineDescription + ", " : "") +
                (fullPhotoUrl != null ? "fullPhotoUrl=" + fullPhotoUrl + ", " : "") +
                (thumbnailPhotoUrl != null ? "thumbnailPhotoUrl=" + thumbnailPhotoUrl + ", " : "") +
                (ordersId != null ? "ordersId=" + ordersId + ", " : "") +
                (ordersLineVariantsId != null ? "ordersLineVariantsId=" + ordersLineVariantsId + ", " : "") +
                (productId != null ? "productId=" + productId + ", " : "") +
            "}";
    }

}
