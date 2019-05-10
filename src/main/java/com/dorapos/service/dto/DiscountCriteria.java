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
 * Criteria class for the {@link com.dorapos.domain.Discount} entity. This class is used
 * in {@link com.dorapos.web.rest.DiscountResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /discounts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DiscountCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter description;

    private FloatFilter percentage;

    private BigDecimalFilter amount;

    private BooleanFilter active;

    private LongFilter shopId;

    private LongFilter productId;

    public DiscountCriteria(){
    }

    public DiscountCriteria(DiscountCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.percentage = other.percentage == null ? null : other.percentage.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.shopId = other.shopId == null ? null : other.shopId.copy();
        this.productId = other.productId == null ? null : other.productId.copy();
    }

    @Override
    public DiscountCriteria copy() {
        return new DiscountCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public BigDecimalFilter getAmount() {
        return amount;
    }

    public void setAmount(BigDecimalFilter amount) {
        this.amount = amount;
    }

    public BooleanFilter getActive() {
        return active;
    }

    public void setActive(BooleanFilter active) {
        this.active = active;
    }

    public LongFilter getShopId() {
        return shopId;
    }

    public void setShopId(LongFilter shopId) {
        this.shopId = shopId;
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
        final DiscountCriteria that = (DiscountCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(description, that.description) &&
            Objects.equals(percentage, that.percentage) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(active, that.active) &&
            Objects.equals(shopId, that.shopId) &&
            Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        description,
        percentage,
        amount,
        active,
        shopId,
        productId
        );
    }

    @Override
    public String toString() {
        return "DiscountCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (percentage != null ? "percentage=" + percentage + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (active != null ? "active=" + active + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
                (productId != null ? "productId=" + productId + ", " : "") +
            "}";
    }

}
