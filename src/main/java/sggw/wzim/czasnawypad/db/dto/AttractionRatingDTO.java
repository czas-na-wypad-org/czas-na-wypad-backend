package sggw.wzim.czasnawypad.db.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttractionRatingDTO {
    private Integer id;
    private Integer attractionId;
    private String attractionName;
    private Byte rating;
    private String notes;
    private LocalDate date;
}
