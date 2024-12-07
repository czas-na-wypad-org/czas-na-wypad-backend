package sggw.wzim.czasnawypad.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import sggw.wzim.czasnawypad.db.dto.AttractionHourDTO;
import sggw.wzim.czasnawypad.db.entity.AttractionHour;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AttractionHourMapper {

    @Mapping(target = "attractionId", source = "attractionHour.attraction.id")
    AttractionHourDTO fromEntity(AttractionHour attractionHour);

    @InheritInverseConfiguration
    AttractionHour fromDTO(AttractionHourDTO attractionHourDTO);

    List<AttractionHourDTO> fromEntityList(List<AttractionHour> attractionHourList);

    List<AttractionHour> fromDTOList(List<AttractionHourDTO> attractionHourDTOList);

}
