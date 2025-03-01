package com.muaz.gym.mappers;

import com.muaz.gym.dto.MembershipDTO;
import com.muaz.gym.models.Membership;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembershipMapper {
    Membership toEntity(MembershipDTO membershipDTO);
    MembershipDTO toDto(Membership membership);
}
