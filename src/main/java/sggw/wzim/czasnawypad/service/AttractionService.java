package sggw.wzim.czasnawypad.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.AttractionRepository;
import sggw.wzim.czasnawypad.db.entity.Attraction;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;

    public List<Attraction> getAllAttractions() {
        return attractionRepository.findAll();
    }

}
