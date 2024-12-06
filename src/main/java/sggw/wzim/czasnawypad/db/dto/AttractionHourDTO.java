package sggw.wzim.czasnawypad.db.dto;

import lombok.Data;

@Data
public class AttractionHourDTO {

    private Integer id;
    private Integer attractionId;
    private String hourFrom;
    private String hourTo;
    private String dayOfWeek;

}
