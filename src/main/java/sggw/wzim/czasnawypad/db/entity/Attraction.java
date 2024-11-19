package sggw.wzim.czasnawypad.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(min = 1, max = 200)
    private String name;
    private String type;
    @Column(name = "price_level")
    private String priceLevel;
    private String description;
    @Column(name = "post_code")
    private String postCode;
    private String city;
    private String street;
    @Column(name = "building_number")
    private String buildingNumber;
    @Column(name = "local_number")
    private String localNumber;
    @Column(name = "loc_x")
    private Double longitude;
    @Column(name = "loc_y")
    private Double latitude;
    private String phone;
    private String email;
    private String website;
    private String photo;
    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
