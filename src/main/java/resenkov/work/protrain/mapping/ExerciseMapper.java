package resenkov.work.protrain.mapping;


import resenkov.work.protrain.dto.ExerciseDTO;
import resenkov.work.protrain.entity.Exercise;

public class ExerciseMapper {

    public static ExerciseDTO toDTO(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(exercise.getId());
        dto.setExerciseName(exercise.getExerciseName());
        dto.setMuscleDivision(exercise.getMuscleDivision());
        return dto;
    }

    public static Exercise toEntity(ExerciseDTO dto) {
        Exercise exercise = new Exercise();
        exercise.setId(dto.getId());
        exercise.setExerciseName(dto.getExerciseName());
        exercise.setMuscleDivision(dto.getMuscleDivision());
        return exercise;
    }

}
