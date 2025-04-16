package resenkov.work.protrain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resenkov.work.protrain.dto.DifficultyDTO;
import resenkov.work.protrain.service.DifficultyService;

import java.util.List;

@RestController
@RequestMapping("/difficulty")
public class DifficultyController {

    @Autowired
    private DifficultyService difficultyLevelService;

    @GetMapping("/all")
    public ResponseEntity<List<DifficultyDTO>> getAllDifficultyLevels() {
        List<DifficultyDTO> levels = difficultyLevelService.getAllDifficultyLevels();
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<DifficultyDTO> getDifficultyLevelById(@PathVariable Long id) {
        DifficultyDTO level = difficultyLevelService.getDifficultyLevelById(id);
        return new ResponseEntity<>(level, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DifficultyDTO> createDifficultyLevel(@RequestBody DifficultyDTO dto) {
        DifficultyDTO createdLevel = difficultyLevelService.createDifficultyLevel(dto);
        return new ResponseEntity<>(createdLevel, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DifficultyDTO> updateDifficultyLevel(
            @PathVariable Long id, @RequestBody DifficultyDTO dto) {
        DifficultyDTO updatedLevel = difficultyLevelService.updateDifficultyLevel(id, dto);
        return new ResponseEntity<>(updatedLevel, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDifficultyLevel(@PathVariable Long id) {
        difficultyLevelService.deleteDifficultyLevel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}