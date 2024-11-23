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
import org.locationtech.jts.geom.Point;

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

    @Size(min = 1, max = 100)
    private String type;

    @Column(name = "price_level")
    @Size(min = 1, max = 50)
    private String priceLevel;

    @Size(min = 1, max = 5000)
    private String description;

    @Column(name = "post_code")
    @Size(min = 1, max = 6)
    private String postCode;

    @Size(min = 1, max = 100)
    private String city;

    @Size(min = 1, max = 200)
    private String street;

    @Column(name = "building_number")
    @Size(min = 1, max = 20)
    private String buildingNumber;

    @Column(name = "local_number")
    @Size(min = 1, max = 20)
    private String localNumber;

    @NotNull
    @Column(name = "localization", columnDefinition = "POINT NOT NULL SRID 4326")
    private Point localization;

    @Size(min = 1, max = 20)
    private String phone;

    @Size(min = 1, max = 320)
    private String email;

    @Size(min = 1, max = 500)
    private String website;

    @Size(min = 1, max = 500)
    private String photo;

    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
