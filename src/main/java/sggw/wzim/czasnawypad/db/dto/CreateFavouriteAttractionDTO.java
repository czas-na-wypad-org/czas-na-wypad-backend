package sggw.wzim.czasnawypad.db.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateFavouriteAttractionDTO {
	@NotNull
	private Long attractionId;
	@NotNull
	private Long userId;

	
}	