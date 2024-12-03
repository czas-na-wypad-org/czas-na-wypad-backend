package sggw.wzim.czasnawypad.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "attraction_hour")
@Data
public class AttractionHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attraction_id", nullable = false)
    private Long attractionId;

    @Column(name = "opening_time", nullable = false)
    private String openingTime;

    @Column(name = "closing_time", nullable = false)
    private String closingTime;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;
}
