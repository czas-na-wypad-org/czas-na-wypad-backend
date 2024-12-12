package sggw.wzim.czasnawypad.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.UserRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionAverageRatingDTO;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.dto.AttractionRatingDTO;
import sggw.wzim.czasnawypad.db.dto.CreateAttractionRatingDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.db.entity.AttractionRating;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.exception.ApplicationExceptions;
import sggw.wzim.czasnawypad.mapper.AttractionMapper;
import sggw.wzim.czasnawypad.mapper.AttractionRatingMapper;
import sggw.wzim.czasnawypad.repository.AttractionRatingRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionRatingService {
    private final AttractionRatingRepository ratingRepository;
    private final AttractionRepository attractionRepository;
    private final UserRepository userRepository;
    private final AttractionMapper attractionMapper;
    private final UserService userService;
    private final AttractionRatingMapper mapper;

    public List<AttractionRatingDTO> getRatingsForAttraction(Integer attractionId) {
        List<AttractionRating> ratings = ratingRepository.findByAttractionId(attractionId);
        return mapper.toDtoList(ratings);
    }

    public List<AttractionRatingDTO> getRatingsByUser(HttpServletRequest request) {
        int userId = userService.getUserIdFromToken(getTokenFromRequest(request));
        List<AttractionRating> ratings = ratingRepository.findByUserId(userId);
        return mapper.toDtoList(ratings);
    }

    public AttractionRatingDTO addRating(CreateAttractionRatingDTO dto, HttpServletRequest request) {
        Attraction attraction = attractionRepository.findById(dto.getAttractionId())
                .orElseThrow(() -> new ApplicationExceptions.AttractionNotFoundException("Attraction not found"));

        User user = userRepository.findById(userService.getUserIdFromToken(getTokenFromRequest(request)))
                .orElseThrow(() -> new ApplicationExceptions.UserNotFoundException(("User not found")));
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

    public AttractionRatingDTO updateRating(Integer id, HttpServletRequest request, CreateAttractionRatingDTO dto) {
        AttractionRating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ApplicationExceptions.RatingNotFoundException("Rating not found"));
        int userId = userService.getUserIdFromToken(getTokenFromRequest(request));
        if (rating.getUser().getId() != (userId)) {
            throw new ApplicationExceptions.NotYourRatingException("You can only update your own ratings!");
        }
        rating.setRating(dto.getRating());
        rating.setNotes(dto.getNotes());
        return mapper.toDto(ratingRepository.save(rating));
    }

    public List<AttractionDTO> findAttractionsByMinimumAverageRating(double minRating) {
        List<AttractionAverageRatingDTO> results = ratingRepository.findAttractionsByMinimumAverageRating(minRating);

        // Mapowanie wyników do listy atrakcji
        List<Integer> attractionIds = results.stream()
                .map(AttractionAverageRatingDTO::getAttractionId) // ID atrakcji
                .toList();

        // Pobierz pełne obiekty Attraction
        List<Attraction> attractionRepositoryAllById = attractionRepository.findAllById(attractionIds);
        return attractionMapper.fromList(attractionRepositoryAllById);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(bearerToken)) {
            throw new InvalidBearerTokenException("Bearer token is missing");
        }
        return bearerToken.substring("Bearer ".length());
    }
}
