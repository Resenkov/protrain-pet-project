package resenkov.work.protrain.controller;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resenkov.work.protrain.entity.DifficultyLevel;
import resenkov.work.protrain.service.DifficultyService;

import java.util.List;

@RestController
@RequestMapping("/difficulty")
public class DifficultyController {

    private final DifficultyService difficultyService;
    public DifficultyController(DifficultyService difficultyService) {
        this.difficultyService = difficultyService;
    }

    @PostMapping("/all")
    public List<DifficultyLevel> getAllDifficultyLevels() {
        return difficultyService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDifficultyLevel(@RequestBody DifficultyLevel difficultyLevel) {
        try {
            if (difficultyLevel.getLevelName() == null || difficultyLevel.getLevelName().isEmpty()) {
                return new ResponseEntity<>("Level name cannot be empty", HttpStatus.NOT_ACCEPTABLE);
            }
            if (difficultyLevel.getLevelValue() <= 0) {
                return new ResponseEntity<>("Level value must be greater than 0", HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(difficultyService.addDifficulty(difficultyLevel));
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Invalid data: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<DifficultyLevel> updateDifficultyLevel(@RequestBody DifficultyLevel difficultyLevel) {
        if(difficultyLevel.getLevelName() == null || difficultyLevel.getLevelName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(difficultyService.updateDifficulty(difficultyLevel));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDifficultyLevel(@PathVariable Long id) {
        difficultyService.deleteDifficulty(id);
    }

}
