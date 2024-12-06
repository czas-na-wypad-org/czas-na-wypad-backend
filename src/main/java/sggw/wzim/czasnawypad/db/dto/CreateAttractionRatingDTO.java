package sggw.wzim.czasnawypad.db.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateAttractionRatingDTO {
    @NotNull
    private Integer attractionId;
    @NotNull
    private Integer userId;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    private String notes;
    private Date date;
}
