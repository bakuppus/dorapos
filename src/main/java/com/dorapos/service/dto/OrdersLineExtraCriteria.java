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
 * Criteria class for the {@link com.dorapos.domain.OrdersLineExtra} entity. This class is used
 * in {@link com.dorapos.web.rest.OrdersLineExtraResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /orders-line-extras?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrdersLineExtraCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ordersLineExtraName;

    private StringFilter ordersLineExtraValue;

    private FloatFilter ordersLineExtraPrice;

    private StringFilter ordersOptionDescription;

    private StringFilter fullPhotoUrl;

    private StringFilter thumbnailPhotoUrl;

    private LongFilter ordersLineVariantId;

    public OrdersLineExtraCriteria(){
    }

    public OrdersLineExtraCriteria(OrdersLineExtraCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ordersLineExtraName = other.ordersLineExtraName == null ? null : other.ordersLineExtraName.copy();
        this.ordersLineExtraValue = other.ordersLineExtraValue == null ? null : other.ordersLineExtraValue.copy();
        this.ordersLineExtraPrice = other.ordersLineExtraPrice == null ? null : other.ordersLineExtraPrice.copy();
        this.ordersOptionDescription = other.ordersOptionDescription == null ? null : other.ordersOptionDescription.copy();
        this.fullPhotoUrl = other.fullPhotoUrl == null ? null : other.fullPhotoUrl.copy();
        this.thumbnailPhotoUrl = other.thumbnailPhotoUrl == null ? null : other.thumbnailPhotoUrl.copy();
        this.ordersLineVariantId = other.ordersLineVariantId == null ? null : other.ordersLineVariantId.copy();
    }

    @Override
    public OrdersLineExtraCriteria copy() {
        return new OrdersLineExtraCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOrdersLineExtraName() {
        return ordersLineExtraName;
    }

    public void setOrdersLineExtraName(StringFilter ordersLineExtraName) {
        this.ordersLineExtraName = ordersLineExtraName;
    }

    public StringFilter getOrdersLineExtraValue() {
        return ordersLineExtraValue;
    }

    public void setOrdersLineExtraValue(StringFilter ordersLineExtraValue) {
        this.ordersLineExtraValue = ordersLineExtraValue;
    }

    public FloatFilter getOrdersLineExtraPrice() {
        return ordersLineExtraPrice;
    }

    public void setOrdersLineExtraPrice(FloatFilter ordersLineExtraPrice) {
        this.ordersLineExtraPrice = ordersLineExtraPrice;
    }

    public StringFilter getOrdersOptionDescription() {
        return ordersOptionDescription;
    }

    public void setOrdersOptionDescription(StringFilter ordersOptionDescription) {
        this.ordersOptionDescription = ordersOptionDescription;
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

    public LongFilter getOrdersLineVariantId() {
        return ordersLineVariantId;
    }

    public void setOrdersLineVariantId(LongFilter ordersLineVariantId) {
        this.ordersLineVariantId = ordersLineVariantId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrdersLineExtraCriteria that = (OrdersLineExtraCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ordersLineExtraName, that.ordersLineExtraName) &&
            Objects.equals(ordersLineExtraValue, that.ordersLineExtraValue) &&
            Objects.equals(ordersLineExtraPrice, that.ordersLineExtraPrice) &&
            Objects.equals(ordersOptionDescription, that.ordersOptionDescription) &&
            Objects.equals(fullPhotoUrl, that.fullPhotoUrl) &&
            Objects.equals(thumbnailPhotoUrl, that.thumbnailPhotoUrl) &&
            Objects.equals(ordersLineVariantId, that.ordersLineVariantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ordersLineExtraName,
        ordersLineExtraValue,
        ordersLineExtraPrice,
        ordersOptionDescription,
        fullPhotoUrl,
        thumbnailPhotoUrl,
        ordersLineVariantId
        );
    }

    @Override
    public String toString() {
        return "OrdersLineExtraCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ordersLineExtraName != null ? "ordersLineExtraName=" + ordersLineExtraName + ", " : "") +
                (ordersLineExtraValue != null ? "ordersLineExtraValue=" + ordersLineExtraValue + ", " : "") +
                (ordersLineExtraPrice != null ? "ordersLineExtraPrice=" + ordersLineExtraPrice + ", " : "") +
                (ordersOptionDescription != null ? "ordersOptionDescription=" + ordersOptionDescription + ", " : "") +
                (fullPhotoUrl != null ? "fullPhotoUrl=" + fullPhotoUrl + ", " : "") +
                (thumbnailPhotoUrl != null ? "thumbnailPhotoUrl=" + thumbnailPhotoUrl + ", " : "") +
                (ordersLineVariantId != null ? "ordersLineVariantId=" + ordersLineVariantId + ", " : "") +
            "}";
    }

}
