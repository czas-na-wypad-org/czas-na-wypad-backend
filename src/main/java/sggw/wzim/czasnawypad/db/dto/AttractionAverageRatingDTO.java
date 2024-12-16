package sggw.wzim.czasnawypad.db.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AttractionAverageRatingDTO {
    private Integer attractionId;
    private String attractionName;
    private Double averageRating;
}
