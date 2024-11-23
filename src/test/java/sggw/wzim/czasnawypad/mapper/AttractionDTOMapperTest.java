package sggw.wzim.czasnawypad.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AttractionDTOMapper Tests")
class AttractionDTOMapperTest {

    private static final String ATTRACTION_NAME = "someName";
    private static final String WEBSITE = "https://example.com";
    private static final String EMAIL = "test@email.com";
    private static final String PHONE = "+48999888777";
    private static final String LOCAL_NUMBER = null;
    private static final String BUILDING_NUMBER = "1";
    private static final String STREET = "ul. Warszawska";
    private static final String CITY = "Warsaw";
    private static final String POST_CODE = "00-001";
    private static final String DESCRIPTION = "Some description";
    private static final String PRICE_LEVEL = "HIGH";
    private static final String TYPE = "RESTAURANT";

    private AttractionDTOMapper underTest;

    @BeforeEach
    void init() {
        underTest = new AttractionDTOMapperImpl();
    }

    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @DisplayName("Given AttractionDTO input object, should map it and return Attraction object")
    @MethodSource
    void testMapFromDTO(AttractionDTO input, Attraction expected) {
        // when
        Attraction actual = underTest.fromDTO(input);

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testMapFromDTO() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(createDTO(), createAttraction())
        );
    }


    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @DisplayName("Given Attraction input object, should map it and return AttractionDTO object")
    @MethodSource
    void testMapFromEntity(Attraction input, AttractionDTO expected) {
        // when
        AttractionDTO actual = underTest.fromEntity(input);

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testMapFromEntity() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(createAttraction(), createDTO())
        );
    }

    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @DisplayName("Given List<AttractionDTO> input object, should map it and return List<Attraction> object")
    @MethodSource
    void testMapFromDTOList(List<AttractionDTO> input, List<Attraction> expected) {
        // when
        List<Attraction> actual = underTest.fromDTOList(input);

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testMapFromDTOList() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(List.of(createDTO()), List.of(createAttraction()))
        );
    }

    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @DisplayName("Given List<Attraction> input object, should map it and return List<AttractionDTO> object")
    @MethodSource
    void testMapFromList(List<Attraction> input, List<AttractionDTO> expected) {
        // when
        List<AttractionDTO> actual = underTest.fromList(input);

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> testMapFromList() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(List.of(createAttraction()), List.of(createDTO()))
        );
    }


    private static AttractionDTO createDTO() {
        return AttractionDTO.builder()
                .name(ATTRACTION_NAME)
                .type(TYPE)
                .priceLevel(PRICE_LEVEL)
                .description(DESCRIPTION)
                .postCode(POST_CODE)
                .city(CITY)
                .street(STREET)
                .buildingNumber(BUILDING_NUMBER)
                .localNumber(LOCAL_NUMBER)
                .localization(createPoint())
                .phone(PHONE)
                .email(EMAIL)
                .website(WEBSITE)
                .build();
    }

    private static Attraction createAttraction() {
        return Attraction.builder()
                .name(ATTRACTION_NAME)
                .type(TYPE)
                .priceLevel(PRICE_LEVEL)
                .description(DESCRIPTION)
                .postCode(POST_CODE)
                .city(CITY)
                .street(STREET)
                .buildingNumber(BUILDING_NUMBER)
                .localNumber(LOCAL_NUMBER)
                .localization(createPoint())
                .phone(PHONE)
                .email(EMAIL)
                .website(WEBSITE)
                .build();
    }

    private static Point createPoint() {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

        return geometryFactory.createPoint(new Coordinate());
    }


}