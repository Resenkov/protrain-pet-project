package resenkov.work.protrain.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resenkov.work.protrain.dto.TypeDTO;
import resenkov.work.protrain.entity.WorkoutType;
import resenkov.work.protrain.mapping.TypeMapper;
import resenkov.work.protrain.repository.TypeRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class TypeService {


    TypeRepository typeRepository;
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<TypeDTO> getAllTypes() {
        return typeRepository.findAll().stream()
                .map(TypeMapper::toDto)
                .collect(Collectors.toList());
    }
    public TypeDTO getTypeById(Long id) {
        WorkoutType workoutType = typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not found with id: " + id));
        return TypeMapper.toDto(workoutType);
    }

    public TypeDTO createType(TypeDTO dto) {
        WorkoutType workoutType = TypeMapper.toEntity(dto);
        WorkoutType savedWorkoutType = typeRepository.save(workoutType);
        return TypeMapper.toDto(savedWorkoutType);
    }

    public TypeDTO updateType(TypeDTO dto) {
        WorkoutType workoutType = TypeMapper.toEntity(dto);
        WorkoutType savedWorkoutType = typeRepository.save(workoutType);
        return TypeMapper.toDto(savedWorkoutType);
    }

    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
