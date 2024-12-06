package sggw.wzim.czasnawypad.db.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateFavouriteAttractionDTO {
	@NotNull
	private Integer attractionId;
	@NotNull
	private Integer userId;

}
