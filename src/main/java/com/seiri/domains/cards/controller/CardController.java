package com.seiri.domains.cards.controller;

import com.seiri.domains.board.Board;
import com.seiri.domains.cards.Cards;
import com.seiri.domains.cards.Status;
import com.seiri.domains.cards.dto.*;
import com.seiri.domains.cards.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.smartcardio.Card;
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
    public ResponseEntity<CardResponseDTO> newCard(@PathVariable UUID id, @RequestBody @Valid CardCreateDTO cardDTO) {
        CardResponseDTO response = this.cardService.newCard(id, cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CardResponseFullDTO> updateCard(@PathVariable UUID id, @RequestBody @Valid CardEditDTO cardDTO) {
        CardResponseFullDTO response = this.cardService.updateCard(id, cardDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CardResponseDTO> deleteCard(@PathVariable UUID id) {
        CardResponseDTO response = this.cardService.deleteCard(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @GetMapping("/find/all/{id}")
//    public ResponseEntity<List<CardResponseFullDTO>> findAll(@PathVariable UUID id) {
//        List<CardResponseFullDTO> response = this.cardService.findById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CardResponseFullDTO> findById(@PathVariable UUID id) {
        CardResponseFullDTO response = this.cardService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/status/find/{id}")
    public ResponseEntity<List<Status>> findAllStatus(@PathVariable UUID id) {
        List<Status> response = this.cardService.getAllStatus(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/status/create/{id}")
    public ResponseEntity<Status> createNewStatus(@PathVariable UUID id, @RequestBody Status status) {
        Status response = this.cardService.createStatus(status, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/status/update/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable UUID id, @RequestBody Status status) {
        Status response = this.cardService.updateStatus(status, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/status/delete/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cardService.deleteStatus(id));
    }

}
