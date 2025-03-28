package resenkov.work.protrain.mapping;

import resenkov.work.protrain.dto.TypeDTO;
import resenkov.work.protrain.entity.WorkoutType;

public class TypeMapper {

    public static TypeDTO toDto(WorkoutType type) {
        TypeDTO dto = new TypeDTO();
        dto.setId(type.getId());
        dto.setTypeName(type.getTypeName());
        return dto;
    }

    public static WorkoutType toEntity(TypeDTO dto) {
        WorkoutType entity = new WorkoutType();
        entity.setId(dto.getId());
        entity.setTypeName(dto.getTypeName());
        return entity;
    }
}
