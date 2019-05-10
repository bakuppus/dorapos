package com.dorapos.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.dorapos.domain.enumeration.ProfileType;
import com.dorapos.domain.enumeration.Gender;
import com.dorapos.domain.enumeration.ProfileStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the {@link com.dorapos.domain.Profile} entity. This class is used
 * in {@link com.dorapos.web.rest.ProfileResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /profiles?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProfileCriteria implements Serializable, Criteria {
    /**
     * Class for filtering ProfileType
     */
    public static class ProfileTypeFilter extends Filter<ProfileType> {

        public ProfileTypeFilter() {
        }

        public ProfileTypeFilter(ProfileTypeFilter filter) {
            super(filter);
        }

        @Override
        public ProfileTypeFilter copy() {
            return new ProfileTypeFilter(this);
        }

    }
    /**
     * Class for filtering Gender
     */
    public static class GenderFilter extends Filter<Gender> {

        public GenderFilter() {
        }

        public GenderFilter(GenderFilter filter) {
            super(filter);
        }

        @Override
        public GenderFilter copy() {
            return new GenderFilter(this);
        }

    }
    /**
     * Class for filtering ProfileStatus
     */
    public static class ProfileStatusFilter extends Filter<ProfileStatus> {

        public ProfileStatusFilter() {
        }

        public ProfileStatusFilter(ProfileStatusFilter filter) {
            super(filter);
        }

        @Override
        public ProfileStatusFilter copy() {
            return new ProfileStatusFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter email;

    private ProfileTypeFilter userType;

    private LocalDateFilter dateOfBirth;

    private GenderFilter gender;

    private ZonedDateTimeFilter registerationDate;

    private ZonedDateTimeFilter lastAccess;

    private ProfileStatusFilter profileStatus;

    private StringFilter telephone;

    private StringFilter mobile;

    private BigDecimalFilter hourlyPayRate;

    private StringFilter thumbnailPhotoUrl;

    private StringFilter fullPhotoUrl;

    private BooleanFilter active;

    private LongFilter shopChangeId;

    private LongFilter userId;

    private LongFilter shopId;

    public ProfileCriteria(){
    }

    public ProfileCriteria(ProfileCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.userType = other.userType == null ? null : other.userType.copy();
        this.dateOfBirth = other.dateOfBirth == null ? null : other.dateOfBirth.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.registerationDate = other.registerationDate == null ? null : other.registerationDate.copy();
        this.lastAccess = other.lastAccess == null ? null : other.lastAccess.copy();
        this.profileStatus = other.profileStatus == null ? null : other.profileStatus.copy();
        this.telephone = other.telephone == null ? null : other.telephone.copy();
        this.mobile = other.mobile == null ? null : other.mobile.copy();
        this.hourlyPayRate = other.hourlyPayRate == null ? null : other.hourlyPayRate.copy();
        this.thumbnailPhotoUrl = other.thumbnailPhotoUrl == null ? null : other.thumbnailPhotoUrl.copy();
        this.fullPhotoUrl = other.fullPhotoUrl == null ? null : other.fullPhotoUrl.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.shopChangeId = other.shopChangeId == null ? null : other.shopChangeId.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.shopId = other.shopId == null ? null : other.shopId.copy();
    }

    @Override
    public ProfileCriteria copy() {
        return new ProfileCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public ProfileTypeFilter getUserType() {
        return userType;
    }

    public void setUserType(ProfileTypeFilter userType) {
        this.userType = userType;
    }

    public LocalDateFilter getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateFilter dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public GenderFilter getGender() {
        return gender;
    }

    public void setGender(GenderFilter gender) {
        this.gender = gender;
    }

    public ZonedDateTimeFilter getRegisterationDate() {
        return registerationDate;
    }

    public void setRegisterationDate(ZonedDateTimeFilter registerationDate) {
        this.registerationDate = registerationDate;
    }

    public ZonedDateTimeFilter getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(ZonedDateTimeFilter lastAccess) {
        this.lastAccess = lastAccess;
    }

    public ProfileStatusFilter getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(ProfileStatusFilter profileStatus) {
        this.profileStatus = profileStatus;
    }

    public StringFilter getTelephone() {
        return telephone;
    }

    public void setTelephone(StringFilter telephone) {
        this.telephone = telephone;
    }

    public StringFilter getMobile() {
        return mobile;
    }

    public void setMobile(StringFilter mobile) {
        this.mobile = mobile;
    }

    public BigDecimalFilter getHourlyPayRate() {
        return hourlyPayRate;
    }

    public void setHourlyPayRate(BigDecimalFilter hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    public StringFilter getThumbnailPhotoUrl() {
        return thumbnailPhotoUrl;
    }

    public void setThumbnailPhotoUrl(StringFilter thumbnailPhotoUrl) {
        this.thumbnailPhotoUrl = thumbnailPhotoUrl;
    }

    public StringFilter getFullPhotoUrl() {
        return fullPhotoUrl;
    }

    public void setFullPhotoUrl(StringFilter fullPhotoUrl) {
        this.fullPhotoUrl = fullPhotoUrl;
    }

    public BooleanFilter getActive() {
        return active;
    }

    public void setActive(BooleanFilter active) {
        this.active = active;
    }

    public LongFilter getShopChangeId() {
        return shopChangeId;
    }

    public void setShopChangeId(LongFilter shopChangeId) {
        this.shopChangeId = shopChangeId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
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
        final ProfileCriteria that = (ProfileCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(userType, that.userType) &&
            Objects.equals(dateOfBirth, that.dateOfBirth) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(registerationDate, that.registerationDate) &&
            Objects.equals(lastAccess, that.lastAccess) &&
            Objects.equals(profileStatus, that.profileStatus) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(hourlyPayRate, that.hourlyPayRate) &&
            Objects.equals(thumbnailPhotoUrl, that.thumbnailPhotoUrl) &&
            Objects.equals(fullPhotoUrl, that.fullPhotoUrl) &&
            Objects.equals(active, that.active) &&
            Objects.equals(shopChangeId, that.shopChangeId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        firstName,
        lastName,
        email,
        userType,
        dateOfBirth,
        gender,
        registerationDate,
        lastAccess,
        profileStatus,
        telephone,
        mobile,
        hourlyPayRate,
        thumbnailPhotoUrl,
        fullPhotoUrl,
        active,
        shopChangeId,
        userId,
        shopId
        );
    }

    @Override
    public String toString() {
        return "ProfileCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (firstName != null ? "firstName=" + firstName + ", " : "") +
                (lastName != null ? "lastName=" + lastName + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (userType != null ? "userType=" + userType + ", " : "") +
                (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "") +
                (gender != null ? "gender=" + gender + ", " : "") +
                (registerationDate != null ? "registerationDate=" + registerationDate + ", " : "") +
                (lastAccess != null ? "lastAccess=" + lastAccess + ", " : "") +
                (profileStatus != null ? "profileStatus=" + profileStatus + ", " : "") +
                (telephone != null ? "telephone=" + telephone + ", " : "") +
                (mobile != null ? "mobile=" + mobile + ", " : "") +
                (hourlyPayRate != null ? "hourlyPayRate=" + hourlyPayRate + ", " : "") +
                (thumbnailPhotoUrl != null ? "thumbnailPhotoUrl=" + thumbnailPhotoUrl + ", " : "") +
                (fullPhotoUrl != null ? "fullPhotoUrl=" + fullPhotoUrl + ", " : "") +
                (active != null ? "active=" + active + ", " : "") +
                (shopChangeId != null ? "shopChangeId=" + shopChangeId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (shopId != null ? "shopId=" + shopId + ", " : "") +
            "}";
    }

}
