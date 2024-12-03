package sggw.wzim.czasnawypad.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attraction_favourite")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavouriteAttraction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "attraction_id", nullable = false)
	private Attraction attraction;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}	
