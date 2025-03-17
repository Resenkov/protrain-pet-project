package resenkov.work.protrain.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resenkov.work.protrain.entity.WorkoutType;
import resenkov.work.protrain.service.TypeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/type")
public class TypeController {

    TypeService typeService;
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/add")
    public ResponseEntity<WorkoutType> addType(@RequestBody WorkoutType type) {
        if(type.getTypeName() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(typeService.createWorkoutType(type));
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkoutType>> getAllType() {
        return ResponseEntity.ok(typeService.getAllWorkoutTypes());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteType(@RequestParam Long id) {
        typeService.deleteWorkoutType(id);
    }

    @PostMapping("/find")
    public ResponseEntity<WorkoutType> findType(@RequestBody Long id) {

        WorkoutType workoutType = null;

        try{
            typeService.getWorkoutTypeById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(workoutType);
    }

    @PostMapping("/update")
    public ResponseEntity<WorkoutType> updateType(@RequestBody WorkoutType workoutType) {
        return ResponseEntity.ok(typeService.updateWorkoutType(workoutType));
    }

}
