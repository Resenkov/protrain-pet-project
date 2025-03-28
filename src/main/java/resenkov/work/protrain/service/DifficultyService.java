package resenkov.work.protrain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resenkov.work.protrain.dto.DifficultyDTO;
import resenkov.work.protrain.entity.DifficultyLevel;
import resenkov.work.protrain.mapping.DifficultyMapper;
import resenkov.work.protrain.repository.DifficultyRepository;

import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional
public class DifficultyService {

    private final DifficultyRepository difficultyLevelRepository;

    public DifficultyService(DifficultyRepository difficultyLevelRepository) {
        this.difficultyLevelRepository = difficultyLevelRepository;
    }

    public List<DifficultyDTO> getAllDifficultyLevels() {
        return difficultyLevelRepository.findAll().stream()
                .map(DifficultyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DifficultyDTO getDifficultyLevelById(Long id) {
        DifficultyLevel entity = difficultyLevelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DifficultyLevel not found with id: " + id));
        return DifficultyMapper.toDTO(entity);
    }

    public DifficultyDTO createDifficultyLevel(DifficultyDTO dto) {
        DifficultyLevel entity = DifficultyMapper.toEntity(dto);
        DifficultyLevel savedEntity = difficultyLevelRepository.save(entity);
        return DifficultyMapper.toDTO(savedEntity);
    }

    public DifficultyDTO updateDifficultyLevel(Long id, DifficultyDTO dto) {
        DifficultyLevel existingEntity = difficultyLevelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DifficultyLevel not found with id: " + id));

        existingEntity.setLevelName(dto.getLevelName());
        existingEntity.setLevelValue(dto.getLevelValue());

        DifficultyLevel updatedEntity = difficultyLevelRepository.save(existingEntity);
        return DifficultyMapper.toDTO(updatedEntity);
    }

    public void deleteDifficultyLevel(Long id) {
        difficultyLevelRepository.deleteById(id);
    }
}
