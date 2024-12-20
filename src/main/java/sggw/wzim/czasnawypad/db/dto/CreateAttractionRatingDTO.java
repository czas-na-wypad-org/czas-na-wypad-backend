package sggw.wzim.czasnawypad.db.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAttractionRatingDTO {
    @NotNull
    private Integer attractionId;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    private String notes;
    private LocalDate date;
}
