package sggw.wzim.czasnawypad.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.mapper.AttractionDTOMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("AttractionService Get All IsDeleted False Attractions test")
class AttractionServiceGetAllIsDeletedFalseTest {

    private AttractionRepository attractionRepository;

    private AttractionDTOMapper attractionDTOMapper;

    private AttractionService underTest;

    @BeforeEach
    void init() {
        this.attractionRepository = mock(AttractionRepository.class);
        this.attractionDTOMapper = mock(AttractionDTOMapper.class);
        this.underTest = new AttractionService(attractionRepository, attractionDTOMapper);
    }

    @Test
    @DisplayName("Given existing attractions in database should return them")
    void getAllAttractions() {
        // given
        List<Attraction> attractions = List.of(getAttraction(1, "test1"), getAttraction(2, "test2"));
        List<AttractionDTO> expected = List.of(getAttractionDTO("test1"), getAttractionDTO("test2"));
        when(attractionRepository.findAllByIsDeletedFalse()).thenReturn(attractions);
        when(attractionDTOMapper.fromList(attractions)).thenReturn(expected);

        // when
        List<AttractionDTO> actual = underTest.getAllAttractions();

        // then
        assertEquals(expected, actual);
    }

    private AttractionDTO getAttractionDTO(String name) {
        return AttractionDTO.builder()
                .name(name)
                .localization(createPoint())
                .build();
    }

    private Attraction getAttraction(Integer id, String name) {
        return Attraction.builder()
                .id(id)
                .name(name)
                .localization(createPoint())
                .isDeleted(false)
                .build();
    }

    private static Point createPoint() {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

        return geometryFactory.createPoint(new Coordinate());
    }

}
