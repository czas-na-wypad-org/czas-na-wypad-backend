package sggw.wzim.czasnawypad.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name = "attraction_hour")
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttractionHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "attraction_id", nullable = false)
    private Integer attractionId;

    @Column(name = "hour_from", nullable = false)
    private LocalTime hourFrom;

    @Column(name = "hour_to", nullable = false)
    private LocalTime hourTo;

    @Column(name = "day_of_week", nullable = false, length = 20)
    private String dayOfWeek;
}