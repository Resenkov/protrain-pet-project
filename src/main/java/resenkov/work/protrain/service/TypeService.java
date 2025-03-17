package resenkov.work.protrain.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resenkov.work.protrain.entity.WorkoutType;
import resenkov.work.protrain.repository.TypeRepository;

import java.util.List;


@Service
@Transactional
public class TypeService {
    TypeRepository typeRepository;
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    public WorkoutType createWorkoutType(WorkoutType workoutType) {
        return typeRepository.save(workoutType);
    }

    public List<WorkoutType> getAllWorkoutTypes() {
        return typeRepository.findAll();
    }

    public WorkoutType getWorkoutTypeById(Long id) {
        return typeRepository.findById(id).get();
    }

    public void deleteWorkoutType(Long id) {
        typeRepository.deleteById(id);
    }

    public WorkoutType updateWorkoutType(WorkoutType workoutType) {
        return typeRepository.save(workoutType);
    }
}
