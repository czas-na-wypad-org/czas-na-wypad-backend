package sggw.wzim.czasnawypad.db.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAttractionRatingDTO {
    @NotNull
    private int attractionId;
    @NotNull
    @Min(1)
    @Max(5)
    private Byte rating;
    private String notes;
}
