package resenkov.work.protrain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class WorkoutDTO {
    private Long id;
    private LocalDateTime workoutDate;
    private String notes;
    private Long workoutTypeId;
    private Long difficultyLevelId;
    private List<ExerciseDTO> exercises;

    @Getter
    @Setter
    public static class ExerciseDTO{
        private Long exerciseId;
        private Integer sets;
        private Integer reps;
        private BigDecimal weightKg;
    }
}
