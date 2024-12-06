package sggw.wzim.czasnawypad.mapper;

import org.mapstruct.*;
import sggw.wzim.czasnawypad.db.dto.FavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.entity.FavouriteAttraction;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedSourcePolicy = ReportingPolicy.ERROR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FavouriteAttractionDTOMapper {
    @Mapping(target = "attractionId", source = "attraction.id")
    @Mapping(target = "userId", source = "user.Id")
    FavouriteAttractionDTO toDto(FavouriteAttraction fav);

    List<FavouriteAttractionDTO> toDtoList(List<FavouriteAttraction> fav);

}
