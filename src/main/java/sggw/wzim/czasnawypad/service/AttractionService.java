package sggw.wzim.czasnawypad.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.mapper.AttractionMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final AttractionMapper attractionMapper;

    public List<AttractionDTO> getAllAttractionsByUserFilters(BigDecimal latitude,
                                                              BigDecimal longitude,
                                                              BigDecimal maxDistance,
                                                              String type,
                                                              String priceLevel,
                                                              Double minRating) {
        log.debug("getAllAttractionsByUserFilters() called");
        List<Attraction> attractions = new ArrayList<>();
        if (minRating != null) {
            if (StringUtils.isBlank(priceLevel) && StringUtils.isBlank(type)) {
                attractions = attractionRepository.findAllByIsDeletedFalseAndWithingMaxDistanceAndMinRating(latitude,
                                                                                                longitude,
                                                                                                maxDistance,
                                                                                                minRating);
            } else if (StringUtils.isBlank(priceLevel) && StringUtils.isNotBlank(type)) {
                attractions = attractionRepository
                        .findAllByIsDeletedFalseAndTypeAndMinRating(type, latitude, longitude, maxDistance, minRating);
            } else if (StringUtils.isNotBlank(priceLevel) && StringUtils.isBlank(type)) {
                attractions = attractionRepository
                        .findAllByIsDeletedFalseAndPriceLevelAndMinRating(priceLevel, latitude, longitude, maxDistance, minRating);
            } else if (StringUtils.isNotBlank(priceLevel) && StringUtils.isNotBlank(type)) {
                attractions = attractionRepository
                        .findAllByIsDeletedFalseAndPriceLevelAndTypeAndMinRating(priceLevel, type, latitude, longitude, maxDistance, minRating);
            }
        } else {
            if (StringUtils.isBlank(priceLevel) && StringUtils.isBlank(type)) {
                attractions = attractionRepository.findAllByIsDeletedFalseAndWithingMaxDistance(latitude,
                                                                                                longitude,
                                                                                                maxDistance);
            } else if (StringUtils.isBlank(priceLevel) && StringUtils.isNotBlank(type)) {
                attractions = attractionRepository
                        .findAllByIsDeletedFalseAndType(type, latitude, longitude, maxDistance);
            } else if (StringUtils.isNotBlank(priceLevel) && StringUtils.isBlank(type)) {
                attractions = attractionRepository
                        .findAllByIsDeletedFalseAndPriceLevel(priceLevel, latitude, longitude, maxDistance);
            } else if (StringUtils.isNotBlank(priceLevel) && StringUtils.isNotBlank(type)) {
                attractions = attractionRepository
                        .findAllByIsDeletedFalseAndPriceLevelAndType(priceLevel, type, latitude, longitude, maxDistance);
            }
        }
        return attractionMapper.fromList(attractions);
    }

}
