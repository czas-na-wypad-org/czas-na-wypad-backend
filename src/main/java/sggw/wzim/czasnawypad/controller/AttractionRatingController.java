package sggw.wzim.czasnawypad.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sggw.wzim.czasnawypad.db.dto.AttractionAverageRatingDTO;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.db.dto.CreateAttractionRatingDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.service.AttractionRatingService;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class AttractionRatingController {

    private final AttractionRatingService ratingService;

    @GetMapping("/attraction/{attractionId}")
    public ResponseEntity<List<AttractionRatingDTO>> getRatingsForAttraction(@PathVariable Integer attractionId) {
        List<AttractionRatingDTO> attractionRatings = ratingService.getRatingsForAttraction(attractionId);
        return ResponseEntity.ok(attractionRatings);
    }

    @GetMapping
    public ResponseEntity<List<AttractionRatingDTO>> getUserRatings(HttpServletRequest request) {
        List<AttractionRatingDTO> userRatings = ratingService.getRatingsByUser(request);
        return ResponseEntity.ok(userRatings);
    }

    @PostMapping
    public ResponseEntity<AttractionRatingDTO> addRating(@Valid @RequestBody CreateAttractionRatingDTO dto, HttpServletRequest request) {
        AttractionRatingDTO createdRatingDTO = ratingService.addRating(dto, request);
        return ResponseEntity.ok().body(createdRatingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttractionRatingDTO> updateRating(@PathVariable Integer id,
                                          HttpServletRequest request,
                                          @RequestBody CreateAttractionRatingDTO dto) {
        AttractionRatingDTO updatedRatingDTO = ratingService.updateRating(id, request, dto);
        return ResponseEntity.ok(updatedRatingDTO);
    }


}
