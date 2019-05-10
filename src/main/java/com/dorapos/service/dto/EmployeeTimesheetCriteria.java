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
 * Criteria class for the {@link com.dorapos.domain.EmployeeTimesheet} entity. This class is used
 * in {@link com.dorapos.web.rest.EmployeeTimesheetResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /employee-timesheets?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmployeeTimesheetCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter checkinTime;

    private ZonedDateTimeFilter checkOutTime;

    private IntegerFilter regularHoursWorked;

    private IntegerFilter overTimeHoursWorked;

    private BigDecimalFilter pay;

    private LongFilter profileId;

    private LongFilter shopId;

    public EmployeeTimesheetCriteria(){
    }

    public EmployeeTimesheetCriteria(EmployeeTimesheetCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.checkinTime = other.checkinTime == null ? null : other.checkinTime.copy();
        this.checkOutTime = other.checkOutTime == null ? null : other.checkOutTime.copy();
        this.regularHoursWorked = other.regularHoursWorked == null ? null : other.regularHoursWorked.copy();
        this.overTimeHoursWorked = other.overTimeHoursWorked == null ? null : other.overTimeHoursWorked.copy();
        this.pay = other.pay == null ? null : other.pay.copy();
        this.profileId = other.profileId == null ? null : other.profileId.copy();
        this.shopId = other.shopId == null ? null : other.shopId.copy();
    }

    @Override
    public EmployeeTimesheetCriteria copy() {
        return new EmployeeTimesheetCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ZonedDateTimeFilter getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(ZonedDateTimeFilter checkinTime) {
        this.checkinTime = checkinTime;
    }

    public ZonedDateTimeFilter getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(ZonedDateTimeFilter checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public IntegerFilter getRegularHoursWorked() {
        return regularHoursWorked;
    }

    public void setRegularHoursWorked(IntegerFilter regularHoursWorked) {
        this.regularHoursWorked = regularHoursWorked;
    }

    public IntegerFilter getOverTimeHoursWorked() {
        return overTimeHoursWorked;
    }

    public void setOverTimeHoursWorked(IntegerFilter overTimeHoursWorked) {
        this.overTimeHoursWorked = overTimeHoursWorked;
    }

    public BigDecimalFilter getPay() {
        return pay;
    }

    public void setPay(BigDecimalFilter pay) {
        this.pay = pay;
    }

    public LongFilter getProfileId() {
        return profileId;
    }

    public void setProfileId(LongFilter profileId) {
        this.profileId = profileId;
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
        final EmployeeTimesheetCriteria that = (EmployeeTimesheetCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(checkinTime, that.checkinTime) &&
            Objects.equals(checkOutTime, that.checkOutTime) &&
            Objects.equals(regularHoursWorked, that.regularHoursWorked) &&
            Objects.equals(overTimeHoursWorked, that.overTimeHoursWorked) &&
            Objects.equals(pay, that.pay) &&
            Objects.equals(profileId, that.profileId) &&
            Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        checkinTime,
        checkOutTime,
        regularHoursWorked,
        overTimeHoursWorked,
        pay,
        profileId,
        shopId
        );
    }

    @Override
    public String toString() {
        return "EmployeeTimesheetCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (checkinTime != null ? "checkinTime=" + checkinTime + ", " : "") +
                (checkOutTime != null ? "checkOutTime=" + checkOutTime + ", " : "") +
                (regularHoursWorked != null ? "regularHoursWorked=" + regularHoursWorked + ", " : "") +
                (overTimeHoursWorked != null ? "overTimeHoursWorked=" + overTimeHoursWorked + ", " : "") +
                (pay != null ? "pay=" + pay + ", " : "") +
                (profileId != null ? "profileId=" + profileId + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
            "}";
    }

}
