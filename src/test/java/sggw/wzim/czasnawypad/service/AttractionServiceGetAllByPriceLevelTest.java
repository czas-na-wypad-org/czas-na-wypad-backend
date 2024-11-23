package sggw.wzim.czasnawypad.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.mapper.AttractionDTOMapper;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Attraction Service Get All By Price Level Test")
class AttractionServiceGetAllByPriceLevelTest {

    private AttractionRepository attractionRepository;

    private AttractionDTOMapper attractionDTOMapper;

    private AttractionService underTest;

    @BeforeEach
    void init() {
        this.attractionRepository = mock(AttractionRepository.class);
        this.attractionDTOMapper = mock(AttractionDTOMapper.class);
        this.underTest = new AttractionService(attractionRepository, attractionDTOMapper);
    }

    @ParameterizedTest
    @MethodSource
    void getAllAttractionsByPriceLevel(List<Attraction> attractions, List<AttractionDTO> expected) {
        // given
        String priceLevel = "monument";
        when(attractionRepository.findAllByIsDeletedFalseAndPriceLevel(priceLevel)).thenReturn(attractions);
        when(attractionDTOMapper.fromList(attractions)).thenReturn(expected);

        // when
        List<AttractionDTO> actual = underTest.getAllAttractionsByPriceLevel(priceLevel);

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getAllAttractionsByPriceLevel() {
        return Stream.of(
                Arguments.of(
                        List.of(), List.of()
                ),
                Arguments.of(
                        List.of(getAttraction(1, "test1", "monument"), getAttraction(2, "test2", "monument")),
                        List.of(getAttractionDTO("test1", "monument"), getAttractionDTO("test2", "monument"))
                )
        );
    }

    private static AttractionDTO getAttractionDTO(String name, String priceLevel) {
        return AttractionDTO.builder()
                .name(name)
                .localization(createPoint())
                .priceLevel(priceLevel)
                .build();
    }

    private static Attraction getAttraction(Integer id, String name, String priceLevel) {
        return Attraction.builder()
                .id(id)
                .name(name)
                .localization(createPoint())
                .priceLevel(priceLevel)
                .isDeleted(false)
                .build();
    }

    private static Point createPoint() {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

        return geometryFactory.createPoint(new Coordinate());
    }

}
