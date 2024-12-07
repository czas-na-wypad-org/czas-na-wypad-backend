package sggw.wzim.czasnawypad.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "attraction_hour")
@Data
public class AttractionHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "attraction_id", referencedColumnName = "id")
    private Attraction attraction;

    @Column(name = "hour_from", nullable = false)
    private String hourFrom;

    @Column(name = "hour_to", nullable = false)
    private String hourTo;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

}
