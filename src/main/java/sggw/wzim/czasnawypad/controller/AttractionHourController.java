package sggw.wzim.czasnawypad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sggw.wzim.czasnawypad.db.dto.AttractionHourDTO;
import sggw.wzim.czasnawypad.service.AttractionHourService;

import java.util.List;

@RestController
@RequestMapping("/attraction-hours")
public class AttractionHourController {

    @Autowired
    private AttractionHourService service;

    @GetMapping("/{attractionId}")
    public List<AttractionHourDTO> getByAttractionId(@PathVariable Integer attractionId) {
        return service.getByAttractionId(attractionId);
    }

    @PostMapping
    public AttractionHourDTO createOrUpdate(@RequestBody AttractionHourDTO dto) {
        return service.saveOrUpdate(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}