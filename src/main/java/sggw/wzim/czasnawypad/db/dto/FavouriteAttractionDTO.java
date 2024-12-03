package sggw.wzim.czasnawypad.db.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavouriteAttractionDTO {
	private Long id;
	private Long attractionId;
	private Long userId;


}	