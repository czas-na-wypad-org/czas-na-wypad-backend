package sggw.wzim.czasnawypad.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.dto.AttractionWithDistance;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.mapper.AttractionDTOMapper;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<AttractionDTO> getAllAttractionsByDistanceFromCustomer(double latitude,
            double longitude,
            double maxDistance) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);
        Point point = geometryFactory.createPoint(new Coordinate(latitude, longitude));

        // Query the database using the repository method
        List<Attraction> allWithinDistanceAndNotDeleted = attractionRepository
                .findAllWithinDistanceAndNotDeleted(point, maxDistance);
        return attractionDTOMapper.fromList(allWithinDistanceAndNotDeleted);
    }

    public List<AttractionWithDistance> getAttractionsWithDistance(double latitude, double longitude) {
        List<Object[]> results = attractionRepository.findAttractionsWithDistance(latitude, longitude);

        return results.stream()
                .map(result -> new AttractionWithDistance((Attraction) result[0], (Double) result[1]))
                .collect(Collectors.toList());
    }

}
