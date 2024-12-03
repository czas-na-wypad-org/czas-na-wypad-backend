package sggw.wzim.czasnawypad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sggw.wzim.czasnawypad.db.dto.AttractionHourDTO;
import sggw.wzim.czasnawypad.model.AttractionHour;
import sggw.wzim.czasnawypad.repository.AttractionHourRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttractionHourService {

    @Autowired
    private AttractionHourRepository repository;

    public List<AttractionHourDTO> getByAttractionId(Integer attractionId) {
        return repository.findByAttractionId(attractionId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AttractionHourDTO saveOrUpdate(AttractionHourDTO dto) {
        AttractionHour entity = toEntity(dto);
        entity = repository.save(entity);
        return toDTO(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private AttractionHourDTO toDTO(AttractionHour entity) {
        return AttractionHourDTO.builder()
                .id(entity.getId())
                .attractionId(entity.getAttractionId())
                .hourFrom(entity.getHourFrom())
                .hourTo(entity.getHourTo())
                .dayOfWeek(entity.getDayOfWeek())
                .build();
    }

    private AttractionHour toEntity(AttractionHourDTO dto) {
        return AttractionHour.builder()
                .id(dto.getId())
                .attractionId(dto.getAttractionId())
                .hourFrom(dto.getHourFrom())
                .hourTo(dto.getHourTo())
                .dayOfWeek(dto.getDayOfWeek())
                .build();
    }
}