package resenkov.work.protrain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WorkoutDTO {
    private Long id;
    private LocalDateTime workoutDate;
    private String notes;
}
