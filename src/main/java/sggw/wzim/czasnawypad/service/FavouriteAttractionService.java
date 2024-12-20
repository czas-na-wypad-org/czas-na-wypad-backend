package sggw.wzim.czasnawypad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.FavouriteAttractionRepository;
import sggw.wzim.czasnawypad.db.UserRepository;
import sggw.wzim.czasnawypad.db.dto.CreateFavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.dto.FavouriteAttractionDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.db.entity.FavouriteAttraction;
import sggw.wzim.czasnawypad.db.entity.User;
import sggw.wzim.czasnawypad.mapper.FavouriteAttractionDTOMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouriteAttractionService {
	private final FavouriteAttractionRepository favouriteRepository;
	private final AttractionRepository attractionRepository;
	private final UserRepository userRepository;
	private final FavouriteAttractionDTOMapper mapper;

	public List<FavouriteAttractionDTO> getFavouritesByUser (Integer userId) {
		return favouriteRepository.findByUserId(userId).stream().map(mapper::toDto).collect(Collectors.toList());
	}

	public FavouriteAttractionDTO addFavourite(CreateFavouriteAttractionDTO dto) {
		Attraction attraction = attractionRepository.findById(dto.getAttractionId())
				.orElseThrow(() -> new IllegalArgumentException("Attraction not found"));
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new IllegalArgumentException(("User not found")));
		if (favouriteRepository.findByAttractionId(dto.getAttractionId()).isEmpty()) {
			FavouriteAttraction favourite = FavouriteAttraction.builder()
					.user(user)
					.attraction(attraction)
					.build();
			return mapper.toDto(favouriteRepository.save(favourite));
		}
		else
			return mapper.toDto(favouriteRepository.findByAttractionId(dto.getAttractionId()).getFirst());

	}

	public void deleteFavourite(Integer favouriteId) {
		favouriteRepository.deleteById(favouriteId);
	}

}	
