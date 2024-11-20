package sggw.wzim.czasnawypad.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionDTO;
import sggw.wzim.czasnawypad.db.entity.Attraction;
import sggw.wzim.czasnawypad.mapper.AttractionDTOMapper;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final AttractionDTOMapper attractionDTOMapper;

    public List<AttractionDTO> getAllAttractions() {
        log.debug("getAllAttractions() called");
        List<Attraction> attractions = attractionRepository.findAll();
        return attractionDTOMapper.fromList(attractions);
    }

}
