package sggw.wzim.czasnawypad.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geolatte.geom.Geometry;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.entity.Attraction;

@RestController
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor
public class TestApiController {

    private final AttractionRepository attractionRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Attraction> helloWorld() {
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

        Coordinate coordinate = new Coordinate(4.326, 43.26);

        Point point = gf.createPoint(coordinate);

        Attraction attraction = Attraction.builder()
                .name("test")
                .localization(point)
                .isDeleted(false)
                .build();

        attraction = attractionRepository.save(attraction);

        attraction = attractionRepository.findById(attraction.getId()).orElseThrow();

        return new ResponseEntity<>(attraction, HttpStatus.OK);
    }

}