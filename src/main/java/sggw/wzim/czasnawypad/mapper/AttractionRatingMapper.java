package sggw.wzim.czasnawypad.mapper;

import org.mapstruct.*;
import sggw.wzim.czasnawypad.db.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.db.entity.AttractionRating;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AttractionRatingMapper {
    @Mapping(target = "attractionId", source = "attraction.id")
    @Mapping(target = "attractionName", source = "attraction.name")
    @Mapping(target = "userName", source = "user.name")
    AttractionRatingDTO toDto(AttractionRating rating);

    List<AttractionRatingDTO> toDtoList(List<AttractionRating> ratings);
}
