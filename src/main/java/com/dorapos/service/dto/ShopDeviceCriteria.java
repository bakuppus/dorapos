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
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the {@link com.dorapos.domain.ShopDevice} entity. This class is used
 * in {@link com.dorapos.web.rest.ShopDeviceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /shop-devices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ShopDeviceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter deviceName;

    private StringFilter deviceModel;

    private ZonedDateTimeFilter registeredDate;

    private LongFilter shopId;

    public ShopDeviceCriteria(){
    }

    public ShopDeviceCriteria(ShopDeviceCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.deviceName = other.deviceName == null ? null : other.deviceName.copy();
        this.deviceModel = other.deviceModel == null ? null : other.deviceModel.copy();
        this.registeredDate = other.registeredDate == null ? null : other.registeredDate.copy();
        this.shopId = other.shopId == null ? null : other.shopId.copy();
    }

    @Override
    public ShopDeviceCriteria copy() {
        return new ShopDeviceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(StringFilter deviceName) {
        this.deviceName = deviceName;
    }

    public StringFilter getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(StringFilter deviceModel) {
        this.deviceModel = deviceModel;
    }

    public ZonedDateTimeFilter getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(ZonedDateTimeFilter registeredDate) {
        this.registeredDate = registeredDate;
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
        final ShopDeviceCriteria that = (ShopDeviceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(deviceName, that.deviceName) &&
            Objects.equals(deviceModel, that.deviceModel) &&
            Objects.equals(registeredDate, that.registeredDate) &&
            Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        deviceName,
        deviceModel,
        registeredDate,
        shopId
        );
    }

    @Override
    public String toString() {
        return "ShopDeviceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (deviceName != null ? "deviceName=" + deviceName + ", " : "") +
                (deviceModel != null ? "deviceModel=" + deviceModel + ", " : "") +
                (registeredDate != null ? "registeredDate=" + registeredDate + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
            "}";
    }

}
