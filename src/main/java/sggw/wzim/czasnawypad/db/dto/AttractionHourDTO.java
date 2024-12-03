package sggw.wzim.czasnawypad.db.dto;

import lombok.*;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttractionHourDTO {
    private Integer id;
    private Integer attractionId;
    private LocalTime hourFrom;
    private LocalTime hourTo;
    private String dayOfWeek;
}