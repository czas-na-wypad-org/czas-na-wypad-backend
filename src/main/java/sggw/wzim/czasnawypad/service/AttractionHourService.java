package sggw.wzim.czasnawypad.service;

import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.dto.AttractionHourDTO;
import sggw.wzim.czasnawypad.model.AttractionHour;
import sggw.wzim.czasnawypad.repository.AttractionHourRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttractionHourService {

    private final AttractionHourRepository repository;

    public AttractionHourService(AttractionHourRepository repository) {
        this.repository = repository;
    }

    public List<AttractionHourDTO> getHoursByAttractionId(Long attractionId) {
        return repository.findByAttractionId(attractionId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public AttractionHourDTO createAttractionHour(AttractionHourDTO dto) {
        AttractionHour attractionHour = mapToEntity(dto);
        return mapToDTO(repository.save(attractionHour));
    }

    public AttractionHourDTO updateAttractionHour(Long id, AttractionHourDTO dto) {
        AttractionHour attractionHour = repository.findById(id).orElseThrow();
        attractionHour.setOpeningTime(dto.getOpeningTime());
        attractionHour.setClosingTime(dto.getClosingTime());
        attractionHour.setDayOfWeek(dto.getDayOfWeek());
        return mapToDTO(repository.save(attractionHour));
    }

    public void deleteAttractionHour(Long id) {
        repository.deleteById(id);
    }

    private AttractionHour mapToEntity(AttractionHourDTO dto) {
        AttractionHour entity = new AttractionHour();
        entity.setId(dto.getId());
        entity.setAttractionId(dto.getAttractionId());
        entity.setOpeningTime(dto.getOpeningTime());
        entity.setClosingTime(dto.getClosingTime());
        entity.setDayOfWeek(dto.getDayOfWeek());
        return entity;
    }

    private AttractionHourDTO mapToDTO(AttractionHour entity) {
        AttractionHourDTO dto = new AttractionHourDTO();
        dto.setId(entity.getId());
        dto.setAttractionId(entity.getAttractionId());
        dto.setOpeningTime(entity.getOpeningTime());
        dto.setClosingTime(entity.getClosingTime());
        dto.setDayOfWeek(entity.getDayOfWeek());
        return dto;
    }
}
