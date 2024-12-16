package sggw.wzim.czasnawypad.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sggw.wzim.czasnawypad.db.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.db.dto.CreateAttractionRatingDTO;
import sggw.wzim.czasnawypad.service.AttractionRatingService;

import java.net.URI;
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

    @GetMapping("/user")
    public ResponseEntity<List<AttractionRatingDTO>> getUserRatings(HttpServletRequest request) {
        List<AttractionRatingDTO> userRatings = ratingService.getRatingsByUser(request);
        return ResponseEntity.ok(userRatings);
    }

    @PostMapping
    public ResponseEntity<AttractionRatingDTO> addRating(@Valid @RequestBody CreateAttractionRatingDTO dto, HttpServletRequest request) {
        AttractionRatingDTO createdRatingDTO = ratingService.addRating(dto, request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // Buduje URI na podstawie aktualnego endpointu
                .path("/{id}") // Dodaj ID do ścieżki (jeśli tworzysz nowe zasoby)
                .buildAndExpand(createdRatingDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdRatingDTO);
    }

    @PutMapping("/{rating-id}")
    public ResponseEntity<Void> updateRating(@PathVariable("rating-id") Integer ratingId,
                                             HttpServletRequest request,
                                             @RequestBody CreateAttractionRatingDTO dto) {
        ratingService.updateRating(ratingId, request, dto);
        return ResponseEntity.noContent().build();
    }


}
