package sggw.wzim.czasnawypad.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import sggw.wzim.czasnawypad.model.AttractionRating;
import sggw.wzim.czasnawypad.model.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.model.dto.CreateAttractionRatingDTO;
import sggw.wzim.czasnawypad.repository.AttractionRatingRepository;

public class AttractionRatingService {
    private final AttractionRatingRepository ratingRepository;
    private final AttractionRepository attractionRepository; // repository z atrakcji

    public List<AttractionRatingDTO> getRatingsByUser(User user) {
        return ratingRepository.findByUser(user).stream().map(this::toDto).collect(Collectors.toList());
    }

    public AttractionRatingDTO addRating(User user, CreateAttractionRatingDTO dto) {
        Attraction attraction = attractionRepository.findById(dto.getAttractionId())
                .orElseThrow(() -> new IllegalArgumentException("Attraction not found"));
        AttractionRating rating = AttractionRating.builder()
                .user(user)
                .attraction(attraction)
                .rating(dto.getRating())
                .notes(dto.getNotes())
                .date(LocalDate.now())
                .build();
        return toDto(ratingRepository.save(rating));
    }

    public AttractionRatingDTO updateRating(Long id, User user, CreateAttractionRatingDTO dto) {
        AttractionRating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rating not found"));
        if (!rating.getUser().equals(user)) {
            throw new SecurityException("You can only update your own ratings.");
        }
        rating.setRating(dto.getRating());
        rating.setNotes(dto.getNotes());
        return toDto(ratingRepository.save(rating));
    }

    private AttractionRatingDTO toDto(AttractionRating rating) {
        return AttractionRatingDTO.builder()
                .id(rating.getId())
                .attractionId(rating.getAttraction().getId())
                .attractionName(rating.getAttraction().getName())
                .rating(rating.getRating())
                .notes(rating.getNotes())
                .date(rating.getDate())
                .build();
    }

    public List<Attraction> findAttractionsByMinimumAverageRating(double minRating) {
        List<Object[]> results = ratingRepository.findAttractionsByMinimumAverageRating(minRating);

        // Mapowanie wyników do listy atrakcji
        List<Long> attractionIds = results.stream()
                .map(result -> (Long) result[0]) // ID atrakcji
                .toList();

        // Pobierz pełne obiekty Attraction
        return attractionRepository.findAllById(attractionIds);
    }
}
