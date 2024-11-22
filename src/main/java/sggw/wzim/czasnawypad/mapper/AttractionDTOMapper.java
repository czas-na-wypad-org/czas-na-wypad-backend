package sggw.wzim.czasnawypad.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AttractionDTOMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = {"id", "photo", "isDeleted"})
    AttractionDTO fromEntity(Attraction attraction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    Attraction fromDTO(AttractionDTO attractionDTO);

    List<AttractionDTO> fromList(List<Attraction> attractions);

    List<Attraction> fromDTOList(List<AttractionDTO> attractionDTOS);

}
