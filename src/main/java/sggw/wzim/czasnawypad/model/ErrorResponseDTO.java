package sggw.wzim.czasnawypad.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Error response")
public class ErrorResponseDTO {

    @Schema(description = "HttpStatus status code of the error", example = "401")
    private Integer statusCode;

    @Schema(description = "Message of the error", example = "Unauthorized")
    private String message;

}
