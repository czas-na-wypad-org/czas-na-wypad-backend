package sggw.wzim.czasnawypad.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.db.dto.CreateAttractionRatingDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.db.entity.AttractionRating;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.repository.AttractionRatingRepository;

@Service
@RequiredArgsConstructor
public class AttractionRatingService {
    private final AttractionRatingRepository ratingRepository;
    private final AttractionRepository attractionRepository;

    public List<AttractionRatingDTO> getRatingsForAttraction(Integer attractionId) {
        List<AttractionRating> ratings = ratingRepository.findByAttractionId(attractionId);
        return ratings.stream()
                      .map(this::toDto)
                      .collect(Collectors.toList());
    }
    
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

    public AttractionRatingDTO updateRating(Integer id, User user, CreateAttractionRatingDTO dto) {
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

        List<Integer> attractionIds = results.stream()
                .map(result -> (Integer) result[0])
                .toList();

        return attractionRepository.findAllById(attractionIds);
    }
}
