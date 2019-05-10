package com.dorapos.service.mapper;

import com.dorapos.domain.*;
import com.dorapos.service.dto.EmailBalancerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmailBalancer} and its DTO {@link EmailBalancerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmailBalancerMapper extends EntityMapper<EmailBalancerDTO, EmailBalancer> {



    default EmailBalancer fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmailBalancer emailBalancer = new EmailBalancer();
        emailBalancer.setId(id);
        return emailBalancer;
    }
}
