package sggw.wzim.czasnawypad.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAttractionRatingDTO {
    @NotNull
    private Long attractionId;
    @NotNull
    @Min(1)
    @Max(5)
    private Byte rating; // 1-5
    private String notes;
}
