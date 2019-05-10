package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.ProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profile} and its DTO {@link ProfileDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ShopMapper.class})
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopShopName")
    ProfileDTO toDto(Profile profile);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "shopId", target = "shop")
    Profile toEntity(ProfileDTO profileDTO);

    default Profile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setId(id);
        return profile;
    }
}
