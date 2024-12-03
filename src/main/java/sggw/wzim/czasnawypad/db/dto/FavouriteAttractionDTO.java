package sggw.wzim.czasnawypad.db.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavouriteAttractionDTO {
	private Integer id;
	private Integer attractionId;
	private Integer userId;
}
