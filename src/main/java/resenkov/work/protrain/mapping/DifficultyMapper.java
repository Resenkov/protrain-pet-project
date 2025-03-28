package resenkov.work.protrain.mapping;

import resenkov.work.protrain.dto.DifficultyDTO;
import resenkov.work.protrain.entity.DifficultyLevel;

public class DifficultyMapper {

    public static DifficultyDTO toDTO(DifficultyLevel entity) {
        DifficultyDTO dto = new DifficultyDTO();
        dto.setId(entity.getId());
        dto.setLevelName(entity.getLevelName());
        dto.setLevelValue(entity.getLevelValue());
        return dto;
    }

    public static DifficultyLevel toEntity(DifficultyDTO dto) {
        DifficultyLevel entity = new DifficultyLevel();
        entity.setId(dto.getId());
        entity.setLevelName(dto.getLevelName());
        entity.setLevelValue(dto.getLevelValue());
        return entity;
    }
}