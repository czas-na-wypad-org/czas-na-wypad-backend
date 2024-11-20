package sggw.wzim.czasnawypad.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "attraction_ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttractionRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attraction_id", nullable = false)
    private Attraction attraction;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Byte rating; // 1-5 scale, validate at the service layer

    @Column(length = 5000)
    private String notes;

    @Column(nullable = false)
    private LocalDate date;
}
