package resenkov.work.protrain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class WorkoutDetailsDTO {
    private Long id;
    private LocalDateTime workoutDate;
    private String notes;
    private List<ExerciseDTO> exercises;
    private String workoutTypeName;
    private String difficultyLevelName;


    @Getter
    @Setter
    public static class ExerciseDTO {
        private String exerciseName;
        private Integer sets;
        private Integer reps;
        private BigDecimal weightKg;
        private String muscleDivision;

        public ExerciseDTO(String exerciseName, Integer sets, Integer reps, BigDecimal weightKg, String muscleDivision) {
            this.exerciseName = exerciseName;
            this.sets = sets;
            this.reps = reps;
            this.weightKg = weightKg;
            this.muscleDivision = muscleDivision;
        }
    }
}
