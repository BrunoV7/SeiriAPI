package com.taskflow.domains.cards.controller;

import com.taskflow.domains.cards.dto.CardDTO;
import com.taskflow.domains.cards.dto.CardEditDTO;
import com.taskflow.domains.cards.dto.CardResponseDTO;
import com.taskflow.domains.cards.dto.CardResponseFullDTO;
import com.taskflow.domains.cards.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<CardResponseDTO> newCard(@PathVariable UUID id, @RequestBody @Valid CardDTO cardDTO) {
        CardResponseDTO response = this.cardService.newCard(id, cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CardResponseDTO> updateCard(@PathVariable UUID id, @RequestBody @Valid CardEditDTO cardDTO) {
        CardResponseDTO response = this.cardService.updateCard(id, cardDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CardResponseDTO> deleteCard(@PathVariable UUID id) {
        CardResponseDTO response = this.cardService.deleteCard(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/all/{id}")
    public ResponseEntity<List<CardResponseFullDTO>> findAll(@PathVariable UUID id) {
        List<CardResponseFullDTO> response = this.cardService.findAll(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CardResponseFullDTO> findById(@PathVariable UUID id) {
        CardResponseFullDTO response = this.cardService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
