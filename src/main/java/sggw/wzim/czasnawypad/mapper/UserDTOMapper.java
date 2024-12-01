package sggw.wzim.czasnawypad.mapper;

import org.mapstruct.*;
import sggw.wzim.czasnawypad.db.dto.RegisterUserDTO;
import sggw.wzim.czasnawypad.db.entity.User;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDTOMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User fromRegisterUserDTO(RegisterUserDTO registerUserDTO);
}
