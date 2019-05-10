package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.SystemEventsHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SystemEventsHistory} and its DTO {@link SystemEventsHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface SystemEventsHistoryMapper extends EntityMapper<SystemEventsHistoryDTO, SystemEventsHistory> {

    @Mapping(source = "triggedBy.id", target = "triggedById")
    @Mapping(source = "triggedBy.firstName", target = "triggedByFirstName")
    SystemEventsHistoryDTO toDto(SystemEventsHistory systemEventsHistory);

    @Mapping(source = "triggedById", target = "triggedBy")
    SystemEventsHistory toEntity(SystemEventsHistoryDTO systemEventsHistoryDTO);

    default SystemEventsHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        SystemEventsHistory systemEventsHistory = new SystemEventsHistory();
        systemEventsHistory.setId(id);
        return systemEventsHistory;
    }
}
