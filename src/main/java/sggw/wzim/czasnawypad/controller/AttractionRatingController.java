package sggw.wzim.czasnawypad.controller;

import java.util.List;

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
import sggw.wzim.czasnawypad.model.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.model.dto.CreateAttractionRatingDTO;
import sggw.wzim.czasnawypad.service.AttractionRatingService;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class AttractionRatingController {

    private final AttractionRatingService ratingService;

    @GetMapping
    public List<AttractionRatingDTO> getUserRatings(@AuthenticationPrincipal User user) {
        return ratingService.getRatingsByUser(user);
    }

     @PostMapping
    public AttractionRatingDTO addRating(@AuthenticationPrincipal User user,
                                         @Valid @RequestBody CreateAttractionRatingDTO dto) {
        return ratingService.addRating(user, dto);
    }

    @PutMapping("/{id}")
    public AttractionRatingDTO updateRating(@PathVariable Long id,
                                             @AuthenticationPrincipal User user,
                                             @RequestBody CreateAttractionRatingDTO dto) {
        return ratingService.updateRating(id, user, dto);
    }

    @GetMapping("/filter")
    public List<Attraction> filterAttractionsByRating(@RequestParam double minRating) {
        return ratingService.findAttractionsByMinimumAverageRating(minRating);
    }
}
