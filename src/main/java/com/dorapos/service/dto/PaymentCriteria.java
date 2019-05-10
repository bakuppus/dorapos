package com.dorapos.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.dorapos.domain.enumeration.PaymentStatus;
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
 * Criteria class for the {@link com.dorapos.domain.Payment} entity. This class is used
 * in {@link com.dorapos.web.rest.PaymentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /payments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PaymentCriteria implements Serializable, Criteria {
    /**
     * Class for filtering PaymentStatus
     */
    public static class PaymentStatusFilter extends Filter<PaymentStatus> {

        public PaymentStatusFilter() {
        }

        public PaymentStatusFilter(PaymentStatusFilter filter) {
            super(filter);
        }

        @Override
        public PaymentStatusFilter copy() {
            return new PaymentStatusFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter paymentDate;

    private StringFilter paymentProvider;

    private BigDecimalFilter amount;

    private PaymentStatusFilter paymentStatus;

    private StringFilter curency;

    private StringFilter customerName;

    private LongFilter shopId;

    private LongFilter processedById;

    private LongFilter paymentMethodId;

    private LongFilter orderId;

    public PaymentCriteria(){
    }

    public PaymentCriteria(PaymentCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.paymentDate = other.paymentDate == null ? null : other.paymentDate.copy();
        this.paymentProvider = other.paymentProvider == null ? null : other.paymentProvider.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.paymentStatus = other.paymentStatus == null ? null : other.paymentStatus.copy();
        this.curency = other.curency == null ? null : other.curency.copy();
        this.customerName = other.customerName == null ? null : other.customerName.copy();
        this.shopId = other.shopId == null ? null : other.shopId.copy();
        this.processedById = other.processedById == null ? null : other.processedById.copy();
        this.paymentMethodId = other.paymentMethodId == null ? null : other.paymentMethodId.copy();
        this.orderId = other.orderId == null ? null : other.orderId.copy();
    }

    @Override
    public PaymentCriteria copy() {
        return new PaymentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ZonedDateTimeFilter getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(ZonedDateTimeFilter paymentDate) {
        this.paymentDate = paymentDate;
    }

    public StringFilter getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(StringFilter paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public BigDecimalFilter getAmount() {
        return amount;
    }

    public void setAmount(BigDecimalFilter amount) {
        this.amount = amount;
    }

    public PaymentStatusFilter getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusFilter paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public StringFilter getCurency() {
        return curency;
    }

    public void setCurency(StringFilter curency) {
        this.curency = curency;
    }

    public StringFilter getCustomerName() {
        return customerName;
    }

    public void setCustomerName(StringFilter customerName) {
        this.customerName = customerName;
    }

    public LongFilter getShopId() {
        return shopId;
    }

    public void setShopId(LongFilter shopId) {
        this.shopId = shopId;
    }

    public LongFilter getProcessedById() {
        return processedById;
    }

    public void setProcessedById(LongFilter processedById) {
        this.processedById = processedById;
    }

    public LongFilter getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(LongFilter paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public LongFilter getOrderId() {
        return orderId;
    }

    public void setOrderId(LongFilter orderId) {
        this.orderId = orderId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PaymentCriteria that = (PaymentCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(paymentDate, that.paymentDate) &&
            Objects.equals(paymentProvider, that.paymentProvider) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(paymentStatus, that.paymentStatus) &&
            Objects.equals(curency, that.curency) &&
            Objects.equals(customerName, that.customerName) &&
            Objects.equals(shopId, that.shopId) &&
            Objects.equals(processedById, that.processedById) &&
            Objects.equals(paymentMethodId, that.paymentMethodId) &&
            Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        paymentDate,
        paymentProvider,
        amount,
        paymentStatus,
        curency,
        customerName,
        shopId,
        processedById,
        paymentMethodId,
        orderId
        );
    }

    @Override
    public String toString() {
        return "PaymentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (paymentDate != null ? "paymentDate=" + paymentDate + ", " : "") +
                (paymentProvider != null ? "paymentProvider=" + paymentProvider + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (paymentStatus != null ? "paymentStatus=" + paymentStatus + ", " : "") +
                (curency != null ? "curency=" + curency + ", " : "") +
                (customerName != null ? "customerName=" + customerName + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
                (processedById != null ? "processedById=" + processedById + ", " : "") +
                (paymentMethodId != null ? "paymentMethodId=" + paymentMethodId + ", " : "") +
                (orderId != null ? "orderId=" + orderId + ", " : "") +
            "}";
    }

}
