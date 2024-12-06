package sggw.wzim.czasnawypad.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sggw.wzim.czasnawypad.db.dto.AttractionHourDTO;
import sggw.wzim.czasnawypad.service.AttractionHourService;

import java.util.List;

@RestController
@RequestMapping("/attraction-hours")
public class AttractionHourController {

    private final AttractionHourService service;

    public AttractionHourController(AttractionHourService service) {
        this.service = service;
    }

    @GetMapping("/{attractionId}")
    public ResponseEntity<List<AttractionHourDTO>> getHoursByAttractionId(@PathVariable Integer attractionId) {
        return ResponseEntity.ok(service.getHoursByAttractionId(attractionId));
    }

}
