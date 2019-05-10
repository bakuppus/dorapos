package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.SystemConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SystemConfig} and its DTO {@link SystemConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {ShopMapper.class})
public interface SystemConfigMapper extends EntityMapper<SystemConfigDTO, SystemConfig> {

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "shop.shopName", target = "shopShopName")
    SystemConfigDTO toDto(SystemConfig systemConfig);

    @Mapping(source = "shopId", target = "shop")
    SystemConfig toEntity(SystemConfigDTO systemConfigDTO);

    default SystemConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setId(id);
        return systemConfig;
    }
}
