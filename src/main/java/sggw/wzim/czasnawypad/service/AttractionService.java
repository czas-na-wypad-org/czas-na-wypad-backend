package sggw.wzim.czasnawypad.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.mapper.AttractionDTOMapper;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final AttractionDTOMapper attractionDTOMapper;

    public List<AttractionDTO> getAllAttractions() {
        log.debug("getAllAttractions() called");
        List<Attraction> attractions = attractionRepository.findAllByIsDeletedFalse();
        return attractionDTOMapper.fromList(attractions);
    }

    public List<AttractionDTO> getAllAttractionsByType(String attractionType) {
        log.debug("getAllAttractionsByType() called");
        List<Attraction> attractionsByType = attractionRepository.findAllByIsDeletedFalseAndType(attractionType);
        return attractionDTOMapper.fromList(attractionsByType);
    }

    public List<AttractionDTO> getAllAttractionsByPriceLevel(String priceLevel) {
        log.debug("getAllAttractionsByPriceLevel() called");
        List<Attraction> attractionsByPriceLevel = attractionRepository
                .findAllByIsDeletedFalseAndPriceLevel(priceLevel);
        return attractionDTOMapper.fromList(attractionsByPriceLevel);
    }

    public List<AttractionDTO> getAllAttractionsByDistanceFromCustomer(
            BigDecimal latitude,
            BigDecimal longitude,
            BigDecimal maxDistance) {
        log.debug("getAllAttractionsByDistanceFromCustomer() called");
        List<Attraction> allWithinDistanceAndNotDeleted = attractionRepository
                .findAllWithinDistance(latitude, longitude, maxDistance);
        return attractionDTOMapper.fromList(allWithinDistanceAndNotDeleted);
    }

}
