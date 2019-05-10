package com.dorapos.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.dorapos.domain.enumeration.ConfigType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.dorapos.domain.SystemConfig} entity. This class is used
 * in {@link com.dorapos.web.rest.SystemConfigResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /system-configs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SystemConfigCriteria implements Serializable, Criteria {
    /**
     * Class for filtering ConfigType
     */
    public static class ConfigTypeFilter extends Filter<ConfigType> {

        public ConfigTypeFilter() {
        }

        public ConfigTypeFilter(ConfigTypeFilter filter) {
            super(filter);
        }

        @Override
        public ConfigTypeFilter copy() {
            return new ConfigTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter key;

    private StringFilter value;

    private ConfigTypeFilter configurationType;

    private StringFilter note;

    private BooleanFilter enabled;

    private LongFilter shopId;

    public SystemConfigCriteria(){
    }

    public SystemConfigCriteria(SystemConfigCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.key = other.key == null ? null : other.key.copy();
        this.value = other.value == null ? null : other.value.copy();
        this.configurationType = other.configurationType == null ? null : other.configurationType.copy();
        this.note = other.note == null ? null : other.note.copy();
        this.enabled = other.enabled == null ? null : other.enabled.copy();
        this.shopId = other.shopId == null ? null : other.shopId.copy();
    }

    @Override
    public SystemConfigCriteria copy() {
        return new SystemConfigCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getKey() {
        return key;
    }

    public void setKey(StringFilter key) {
        this.key = key;
    }

    public StringFilter getValue() {
        return value;
    }

    public void setValue(StringFilter value) {
        this.value = value;
    }

    public ConfigTypeFilter getConfigurationType() {
        return configurationType;
    }

    public void setConfigurationType(ConfigTypeFilter configurationType) {
        this.configurationType = configurationType;
    }

    public StringFilter getNote() {
        return note;
    }

    public void setNote(StringFilter note) {
        this.note = note;
    }

    public BooleanFilter getEnabled() {
        return enabled;
    }

    public void setEnabled(BooleanFilter enabled) {
        this.enabled = enabled;
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
        final SystemConfigCriteria that = (SystemConfigCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(key, that.key) &&
            Objects.equals(value, that.value) &&
            Objects.equals(configurationType, that.configurationType) &&
            Objects.equals(note, that.note) &&
            Objects.equals(enabled, that.enabled) &&
            Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        key,
        value,
        configurationType,
        note,
        enabled,
        shopId
        );
    }

    @Override
    public String toString() {
        return "SystemConfigCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (key != null ? "key=" + key + ", " : "") +
                (value != null ? "value=" + value + ", " : "") +
                (configurationType != null ? "configurationType=" + configurationType + ", " : "") +
                (note != null ? "note=" + note + ", " : "") +
                (enabled != null ? "enabled=" + enabled + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
            "}";
    }

}
