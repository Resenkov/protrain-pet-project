package resenkov.work.protrain.mapping;

import resenkov.work.protrain.dto.WorkoutDTO;
import resenkov.work.protrain.entity.Workout;

public class WorkoutMapper {
    public static WorkoutDTO toDto(Workout workout) {
        WorkoutDTO dto = new WorkoutDTO();
        dto.setId(workout.getId());
        dto.setWorkoutDate(workout.getWorkoutDate());
        dto.setNotes(workout.getNotes());
        return dto;
    }

    public static Workout toEntity(WorkoutDTO dto) {
        Workout workout = new Workout();
        workout.setId(dto.getId());
        workout.setWorkoutDate(dto.getWorkoutDate());
        workout.setNotes(dto.getNotes());
        return workout;
    }
}