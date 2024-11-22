package sggw.wzim.czasnawypad.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sggw.wzim.czasnawypad.db.entity.Attraction;

@Getter
@Setter
@AllArgsConstructor
public class AttractionWithDistance {
    private Attraction attraction;
    private Double distance;
}
