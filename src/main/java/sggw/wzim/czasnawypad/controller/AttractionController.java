package sggw.wzim.czasnawypad.controller;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.model.ErrorResponseDTO;
import sggw.wzim.czasnawypad.service.AttractionService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @ApiResponses(
            value = {
                    @ApiResponse(description = "Returns list of attractions filtered by either: maxDistance, priceLevel, type", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AttractionDTO.class)))),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = "{\"statusCode\": 401, \"message\": \"Unauthorized\"}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = "{\"statusCode\": 403, \"message\": \"Forbidden\"}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = "{\"statusCode\": 500, \"message\": \"Internal Server Error\"}"
                                    )
                            )
                    )
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AttractionDTO>> getAllAttractionsByUserFilters(
            @RequestParam(name = "latitude") BigDecimal latitude,
            @RequestParam(name = "longitude") BigDecimal longitude,
            @RequestParam(name = "maxDistance") BigDecimal maxDistance,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "priceLevel", required = false) String priceLevel,
            @RequestParam(name = "minRating", required = false) BigDecimal minRating) {
        log.debug("GET getAllAttractionsByUserFilters called");
        List<AttractionDTO> attractionsByDistanceFromCustomer = attractionService
                .getAllAttractionsByUserFilters(latitude, longitude, maxDistance, type, priceLevel, minRating);
        return new ResponseEntity<>(attractionsByDistanceFromCustomer, HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(description = "Returns list of attractions filtered by either: maxDistance, " +
                            "priceLevel, type", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AttractionDTO.class))),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = "{\"statusCode\": 401, \"message\": \"Unauthorized\"}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = "{\"statusCode\": 403, \"message\": \"Forbidden\"}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = "{\"statusCode\": 404, \"message\": \"Not Found\"}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = "{\"statusCode\": 500, \"message\": \"Internal Server Error\"}"
                                    )
                            )
                    )
            }
    )
    @GetMapping(value = "/{attraction-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttractionDTO> getAttractionById(@PathVariable("attraction-id") Integer attractionId) {
        log.debug("GET getAttractionById called");
        return ResponseEntity.ok(attractionService.getAttractionById(attractionId));
    }


}
