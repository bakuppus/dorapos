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
 * Criteria class for the {@link com.dorapos.domain.ShopChange} entity. This class is used
 * in {@link com.dorapos.web.rest.ShopChangeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /shop-changes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ShopChangeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter change;

    private StringFilter changedEntity;

    private StringFilter note;

    private ZonedDateTimeFilter changeDate;

    private LongFilter shopId;

    private LongFilter changedById;

    public ShopChangeCriteria(){
    }

    public ShopChangeCriteria(ShopChangeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.change = other.change == null ? null : other.change.copy();
        this.changedEntity = other.changedEntity == null ? null : other.changedEntity.copy();
        this.note = other.note == null ? null : other.note.copy();
        this.changeDate = other.changeDate == null ? null : other.changeDate.copy();
        this.shopId = other.shopId == null ? null : other.shopId.copy();
        this.changedById = other.changedById == null ? null : other.changedById.copy();
    }

    @Override
    public ShopChangeCriteria copy() {
        return new ShopChangeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getChange() {
        return change;
    }

    public void setChange(StringFilter change) {
        this.change = change;
    }

    public StringFilter getChangedEntity() {
        return changedEntity;
    }

    public void setChangedEntity(StringFilter changedEntity) {
        this.changedEntity = changedEntity;
    }

    public StringFilter getNote() {
        return note;
    }

    public void setNote(StringFilter note) {
        this.note = note;
    }

    public ZonedDateTimeFilter getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(ZonedDateTimeFilter changeDate) {
        this.changeDate = changeDate;
    }

    public LongFilter getShopId() {
        return shopId;
    }

    public void setShopId(LongFilter shopId) {
        this.shopId = shopId;
    }

    public LongFilter getChangedById() {
        return changedById;
    }

    public void setChangedById(LongFilter changedById) {
        this.changedById = changedById;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ShopChangeCriteria that = (ShopChangeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(change, that.change) &&
            Objects.equals(changedEntity, that.changedEntity) &&
            Objects.equals(note, that.note) &&
            Objects.equals(changeDate, that.changeDate) &&
            Objects.equals(shopId, that.shopId) &&
            Objects.equals(changedById, that.changedById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        change,
        changedEntity,
        note,
        changeDate,
        shopId,
        changedById
        );
    }

    @Override
    public String toString() {
        return "ShopChangeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (change != null ? "change=" + change + ", " : "") +
                (changedEntity != null ? "changedEntity=" + changedEntity + ", " : "") +
                (note != null ? "note=" + note + ", " : "") +
                (changeDate != null ? "changeDate=" + changeDate + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
                (changedById != null ? "changedById=" + changedById + ", " : "") +
            "}";
    }

}
