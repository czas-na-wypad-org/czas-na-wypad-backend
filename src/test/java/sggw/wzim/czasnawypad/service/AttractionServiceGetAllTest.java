package sggw.wzim.czasnawypad.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.entity.Attraction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("AttractionService Get All Attractions test")
class AttractionServiceGetAllTest {

    private AttractionRepository attractionRepository;

    private AttractionService underTest;

    @BeforeEach
    void init() {
        this.attractionRepository = mock(AttractionRepository.class);
        this.underTest = new AttractionService(attractionRepository);
    }

    @Test
    @DisplayName("""
            Given existing attractions
            should return them
            """)
    void getAllAttractions() {
        // given
        List<Attraction> expected = List.of(getAttraction(1, "test1"), getAttraction(2, "test2"));
        when(attractionRepository.findAll()).thenReturn(expected);

        // when
        List<Attraction> actual = underTest.getAllAttractions();

        // then
        assertEquals(expected, actual);
    }

    private Attraction getAttraction(Integer id, String name) {
        return Attraction.builder()
                .id(id)
                .name(name)
                .longitude(25.45)
                .latitude(45.25)
                .isDeleted(false)
                .build();
    }

}
