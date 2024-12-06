package sggw.wzim.czasnawypad.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionAverageRatingDTO;
import sggw.wzim.czasnawypad.db.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.db.dto.CreateAttractionRatingDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.db.entity.AttractionRating;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.mapper.AttractionRatingMapper;
import sggw.wzim.czasnawypad.repository.AttractionRatingRepository;
import sggw.wzim.czasnawypad.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AttractionRatingService {
    private final AttractionRatingRepository ratingRepository;
    private final AttractionRepository attractionRepository;
    private final UserRepository userRepository;
    private final AttractionRatingMapper mapper;

    public List<AttractionRatingDTO> getRatingsForAttraction(Integer attractionId) {
        List<AttractionRating> ratings = ratingRepository.findByAttractionId(attractionId);
        return mapper.toDtoList(ratings);
    }
    
    public List<AttractionRatingDTO> getRatingsByUser(Integer userId) {
        List<AttractionRating> ratings = ratingRepository.findByUserId(userId);
        return mapper.toDtoList(ratings);
    }

    public AttractionRatingDTO addRating(CreateAttractionRatingDTO dto) {
        Attraction attraction = attractionRepository.findById(dto.getAttractionId())
                .orElseThrow(() -> new IllegalArgumentException("Attraction not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(("User not found")));
        Date date = new Date();
        AttractionRating rating = AttractionRating.builder()
                .user(user)
                .attraction(attraction)
                .rating(dto.getRating())
                .notes(dto.getNotes())
                .date(date)
                .build();
        return mapper.toDto(ratingRepository.save(rating));
    }

    public AttractionRatingDTO updateRating(Integer id, Integer userId, CreateAttractionRatingDTO dto) {
        AttractionRating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rating not found"));
        if (rating.getUser().getId()!=(userId)) {
            throw new SecurityException("You can only update your own ratings.");
        }
        rating.setRating(dto.getRating());
        rating.setNotes(dto.getNotes());
        return mapper.toDto(ratingRepository.save(rating));
    }

    public List<AttractionAverageRatingDTO> findAttractionsByMinimumAverageRating(double minRating) {
        return ratingRepository.findAttractionsByMinimumAverageRating(minRating);
    }
}
