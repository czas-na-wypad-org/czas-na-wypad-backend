package sggw.wzim.czasnawypad.db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AttractionDTO {

    private String name;
    private String type;
    private String priceLevel;
    private String description;
    private String postCode;
    private String city;
    private String street;
    private String buildingNumber;
    private String localNumber;
    private Double longitude;
    private Double latitude;
    private String phone;
    private String email;
    private String website;

}
