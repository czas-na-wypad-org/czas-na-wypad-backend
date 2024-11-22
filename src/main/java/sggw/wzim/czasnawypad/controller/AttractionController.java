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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.dto.AttractionWithDistance;
import sggw.wzim.czasnawypad.model.ErrorResponseDTO;
import sggw.wzim.czasnawypad.service.AttractionService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @ApiResponses(
            value = {
                    @ApiResponse(description = "Returns List of all active attractions", responseCode = "200",
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
    @GetMapping(value = "/attractions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AttractionDTO>> getAllAttractions() {
        log.debug("@GET getAllAttractions called");
        List<AttractionDTO> attractions = attractionService.getAllAttractions();
        log.debug("Returning all attractions");
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(description = "Returns List of all active attractions by type", responseCode = "200",
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
    @GetMapping(value = "/attractions/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AttractionDTO>> getAllAttractionsByType(@PathVariable String type) {
        log.debug("@GET getAllAttractionsByType called");
        List<AttractionDTO> attractionsByType = attractionService.getAllAttractionsByType(type);
        log.debug("Returning all attractions by type");
        return new ResponseEntity<>(attractionsByType, HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(description = "Returns List of all active attractions filtered by price level", responseCode = "200",
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
    @GetMapping(value = "/attractions/price-level/{priceLevel}")
    public ResponseEntity<List<AttractionDTO>> getAllAttractionsByPriceLevel(@PathVariable String priceLevel) {
        log.debug("GET getAllAttractionsByPriceLevel called");
        List<AttractionDTO> attractionsByPriceLevel = attractionService.getAllAttractionsByPriceLevel(priceLevel);
        log.debug("Returning all attractions by price level");
        return new ResponseEntity<>(attractionsByPriceLevel, HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(description = "Returns List of all active attractions by longitude and latitude and max distance", responseCode = "200",
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
    @GetMapping(value = "/attractions/location")
    public ResponseEntity<List<AttractionDTO>> getAllAttractionsByDistanceFromCustomer(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude,
            @RequestParam(name = "maxDistance") double maxDistance) {
        log.debug("GET getAllAttractionsByDistanceFromCustomer called");
        if (maxDistance < 0.0) {
            throw new IllegalArgumentException("maxDistance must be greater than 0");
        }
        List<AttractionDTO> attractionsByDistanceFromCustomer = attractionService
                .getAllAttractionsByDistanceFromCustomer(latitude, longitude, maxDistance);
        return new ResponseEntity<>(attractionsByDistanceFromCustomer, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<List<AttractionWithDistance>> test(@RequestParam("lat") double lat,
            @RequestParam("lng") double lng) {
        return new ResponseEntity<>(attractionService.getAttractionsWithDistance(lat, lng), HttpStatus.OK);
    }


}
