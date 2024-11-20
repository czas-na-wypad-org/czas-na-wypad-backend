package sggw.wzim.czasnawypad.model.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttractionRatingDTO {
    private Long id;
    private Long attractionId;
    private String attractionName;
    private Byte rating;
    private String notes;
    private LocalDate date;
}
