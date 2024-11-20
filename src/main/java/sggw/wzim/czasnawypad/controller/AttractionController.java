package sggw.wzim.czasnawypad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.service.AttractionService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping(value = "/attractions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AttractionDTO>> getAllAttractions() {
        log.debug("@GET getAllAttractions called");
        List<AttractionDTO> attractions = attractionService.getAllAttractions();
        log.debug("Returning all attractions");
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

}
