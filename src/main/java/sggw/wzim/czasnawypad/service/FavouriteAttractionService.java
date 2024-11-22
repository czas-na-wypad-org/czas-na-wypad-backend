package sggw.wzim.czasnawypad.service;

import sggw.wzim.czasnawypad.db.FavouriteAttraction;
import sggw.wzim.czasnawypad.db.dto.FavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.dto.CreateFavouriteAttractionDTO;
import sggw.wzim.czasnawypad.repository.AttractionRatingRepository;

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
