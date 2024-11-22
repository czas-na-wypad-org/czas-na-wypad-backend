package sggw.wzim.czasnawypad.db.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AttractionDTO {

    @NotNull
    @Size(min = 1, max = 200)
    @Schema(description = "Name of the attraction",
            example = "Memorial Monument of the Fallen during WW2")
    private String name;

    @Size(min = 1, max = 100)
    @Schema(description = "Type of the attraction",
            examples = {"restaurant", "monument"},
            example = "monument")
    private String type;

    @Size(min = 1, max = 50)
    @Schema(description = "Expected price level of the attraction",
            examples = {"low", "medium", "high"},
            example = "low")
    private String priceLevel;

    @Size(min = 1, max = 5000)
    @Schema(description = "Description of the attraction",
            example = "This is a memorial")
    private String description;

    @Size(min = 1, max = 6)
    @Schema(description = "Post code from the address of the attraction")
    private String postCode;

    @Size(min = 1, max = 100)
    @Schema(description = "City's name from the address of the attraction")
    private String city;

    @Size(min = 1, max = 200)
    @Schema(description = "Street's name from the address of the attraction")
    private String street;

    @Size(min = 1, max = 20)
    @Schema(description = "Building's number from the address of the attraction")
    private String buildingNumber;

    @Size(min = 1, max = 20)
    @Schema(description = "Appartment's number from the address of the attraction")
    private String localNumber;

    @NotNull
    private Point localization;

    @Size(min = 1, max = 20)
    @Schema(description = "Phone number associated with company that operates and maintains the attraction")
    private String phone;

    @Size(min = 1, max = 320)
    @Schema(description = "Email associated with company that operates and maintains the attraction")
    private String email;

    @Size(min = 1, max = 500)
    @Schema(description = "Website that is associated with the attraction")
    private String website;

}
