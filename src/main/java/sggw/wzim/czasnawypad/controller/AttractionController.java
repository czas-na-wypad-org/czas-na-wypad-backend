package sggw.wzim.czasnawypad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.service.AttractionService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("/attractions")
    public ResponseEntity<List<Attraction>> getAllAttractions() {
        log.debug("@GET getAllAttractions called");
        List<Attraction> attractions = attractionService.getAllAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

}