package sggw.wzim.czasnawypad.dto;

import lombok.Data;

@Data
public class AttractionHourDTO {
    private Long id;
    private Long attractionId;
    private String openingTime;
    private String closingTime;
    private String dayOfWeek;
}
