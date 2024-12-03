package sggw.wzim.czasnawypad.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sggw.wzim.czasnawypad.dto.AttractionHourDTO;
import sggw.wzim.czasnawypad.service.AttractionHourService;

import java.util.List;

@RestController
@RequestMapping("/api/attraction-hours")
public class AttractionHourController {

    private final AttractionHourService service;

    public AttractionHourController(AttractionHourService service) {
        this.service = service;
    }

    @GetMapping("/{attractionId}")
    public ResponseEntity<List<AttractionHourDTO>> getHoursByAttractionId(@PathVariable Long attractionId) {
        return ResponseEntity.ok(service.getHoursByAttractionId(attractionId));
    }

    @PostMapping
    public ResponseEntity<AttractionHourDTO> createAttractionHour(@RequestBody AttractionHourDTO dto) {
        return ResponseEntity.ok(service.createAttractionHour(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttractionHourDTO> updateAttractionHour(@PathVariable Long id, @RequestBody AttractionHourDTO dto) {
        return ResponseEntity.ok(service.updateAttractionHour(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttractionHour(@PathVariable Long id) {
        service.deleteAttractionHour(id);
        return ResponseEntity.noContent().build();
    }
}
