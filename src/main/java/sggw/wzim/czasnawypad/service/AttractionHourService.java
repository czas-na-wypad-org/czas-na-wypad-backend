package sggw.wzim.czasnawypad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.AttractionHourRepository;
import sggw.wzim.czasnawypad.db.dto.AttractionHourDTO;
import sggw.wzim.czasnawypad.db.entity.AttractionHour;
import sggw.wzim.czasnawypad.mapper.AttractionHourMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionHourService {

    private final AttractionHourRepository attractionHourRepository;
    private final AttractionHourMapper attractionHourMappper;

    public List<AttractionHourDTO> getHoursByAttractionId(Integer attractionId) {
        List<AttractionHour> attractionHours = attractionHourRepository.findByAttractionId(attractionId);
        return attractionHourMappper.fromEntityList(attractionHours);
    }

}
