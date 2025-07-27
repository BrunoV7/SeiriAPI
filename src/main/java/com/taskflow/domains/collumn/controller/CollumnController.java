package com.taskflow.domains.collumn.controller;

import com.taskflow.domains.collumn.dto.CollumnDTO;
import com.taskflow.domains.collumn.dto.CollumnDeleteDTO;
import com.taskflow.domains.collumn.dto.CollumnResponseDTO;
import com.taskflow.domains.collumn.service.CollumnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collumn")
public class CollumnController {

    private final CollumnService collumnService;

    public CollumnController(CollumnService collumnService) {
        this.collumnService = collumnService;
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<CollumnResponseDTO> newCollumn(@PathVariable UUID id, @RequestBody CollumnDTO collumn) {
        CollumnResponseDTO response = this.collumnService.newCollumn(id, collumn);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CollumnResponseDTO> updateCollumn(@PathVariable UUID id, @RequestBody CollumnDTO collumn) {
        CollumnResponseDTO response = this.collumnService.updateCollumn(id, collumn);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CollumnResponseDTO>> findAll() {
        List <CollumnResponseDTO> response = this.collumnService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CollumnResponseDTO> findById(@PathVariable UUID id) {
        CollumnResponseDTO response = this.collumnService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CollumnDeleteDTO> delete(@PathVariable UUID id) {
        CollumnDeleteDTO response = this.collumnService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
