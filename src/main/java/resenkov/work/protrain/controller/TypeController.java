package resenkov.work.protrain.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import resenkov.work.protrain.dto.TypeDTO;
import resenkov.work.protrain.service.TypeService;

import java.util.List;


@RestController
@RequestMapping("/type")
public class TypeController {

    TypeService typeService;
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/add")
    public ResponseEntity<TypeDTO> addType(@RequestBody TypeDTO type) {
        TypeDTO newType = typeService.createType(type);
        return new ResponseEntity<>(newType, HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    public ResponseEntity<TypeDTO> getById(@PathVariable Long id) {
        TypeDTO typeDTO = typeService.getTypeById(id);
        return new ResponseEntity<>(typeDTO, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<TypeDTO>> getAllType() {
        List<TypeDTO> types = typeService.getAllTypes();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<TypeDTO> updateType(@RequestBody TypeDTO type) {
        TypeDTO typee = typeService.updateType(type);
        return new ResponseEntity<>(typee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TypeDTO> deleteType(@PathVariable Long id){
        typeService.deleteType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
