package sggw.wzim.czasnawypad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.FavouriteAttractionRepository;
import sggw.wzim.czasnawypad.db.dto.CreateFavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.dto.FavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.db.entity.FavouriteAttraction;
import sggw.wzim.czasnawypad.db.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouriteAttractionService {
	private final FavouriteAttractionRepository favouriteRepository;
	private final AttractionRepository attractionRepository;

	public List<FavouriteAttractionDTO> getFavouritesByUser (User user) {
		return favouriteRepository.findByUser(user).stream().map(this::toDto).collect(Collectors.toList());
	}

	public FavouriteAttractionDTO addFavourite(User user, CreateFavouriteAttractionDTO dto) {
		Attraction attraction = attractionRepository.findById(dto.getAttractionId())
			.orElseThrow(() -> new IllegalArgumentException("Attraction not found"));
		FavouriteAttraction favourite = FavouriteAttraction.builder()
			.user(user)
			.attraction(attraction)
			.build();
		return toDto(favouriteRepository.save(favourite));	
	}
	
    private FavouriteAttractionDTO toDto(FavouriteAttraction favourite) {
        return FavouriteAttractionDTO.builder()
            .id(favourite.getId())
            .attractionId(favourite.getAttraction().getId())
			.userId(favourite.getUser().getId())
			.build();
	}
	
	
}	
