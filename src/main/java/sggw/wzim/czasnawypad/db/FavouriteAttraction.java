package sggw.wzim.czasnawypad.db;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attraction_favourite")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavouriteAttraction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "attraction_id", nullable = false)
    private Attraction attraction;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
    private User user;

}	