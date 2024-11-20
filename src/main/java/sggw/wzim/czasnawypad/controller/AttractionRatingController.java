package sggw.wzim.czasnawypad.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sggw.wzim.czasnawypad.db.dto.AttractionAverageRatingDTO;
import sggw.wzim.czasnawypad.db.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.db.dto.CreateAttractionRatingDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.service.AttractionRatingService;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class AttractionRatingController {

    private final AttractionRatingService ratingService;

    @GetMapping("/attraction/{attractionId}")
    public ResponseEntity<?> getRatingsForAttraction(@PathVariable Integer attractionId) {
        List<AttractionRatingDTO> attractionRatings = ratingService.getRatingsForAttraction(attractionId);
        return ResponseEntity.ok(attractionRatings);
    }

    @GetMapping
    public ResponseEntity<?> getUserRatings(Integer userId) {
        List<AttractionRatingDTO> userRatings = ratingService.getRatingsByUser(userId);
        return ResponseEntity.ok(userRatings);
    }

    @PostMapping
    public ResponseEntity<?> addRating(@Valid @RequestBody CreateAttractionRatingDTO dto) {
        AttractionRatingDTO createdRatingDTO = ratingService.addRating(dto);
        return ResponseEntity.ok().body(createdRatingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRating(@PathVariable Integer id,
                                          Integer userId,
                                          @RequestBody CreateAttractionRatingDTO dto) {
        AttractionRatingDTO updatedRatingDTO = ratingService.updateRating(id, userId, dto);
        return ResponseEntity.ok(updatedRatingDTO);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterAttractionsByRating(@RequestParam double minRating) {
        List<AttractionAverageRatingDTO> attractionsByRating = ratingService.findAttractionsByMinimumAverageRating(minRating);
        return ResponseEntity.ok(attractionsByRating);
    }

    @GetMapping("/filter")
    public List<Attraction> filterAttractionsByRating(@RequestParam double minRating) {
        return ratingService.findAttractionsByMinimumAverageRating(minRating);
    }
}
