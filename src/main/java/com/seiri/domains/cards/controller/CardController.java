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
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/v1/new/{id}")
    public ResponseEntity<CardResponseDTO> newCard(@PathVariable UUID id, @RequestBody @Valid CardCreateDTO cardDTO) {
        CardResponseDTO response = this.cardService.newCard(id, cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/v1/update/{id}")
    public ResponseEntity<CardResponseFullDTO> updateCard(@PathVariable UUID id, @RequestBody @Valid CardEditDTO cardDTO) {
        CardResponseFullDTO response = this.cardService.updateCard(id, cardDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/v1/update/{cardId}/status/{statusId}")
    public ResponseEntity<CardResponseFullDTO> updatedCardStatus(@PathVariable UUID cardId, @PathVariable UUID statusId) {
        CardResponseFullDTO response = this.cardService.updateCardStatus(cardId, statusId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<CardResponseDTO> deleteCard(@PathVariable UUID id) {
        CardResponseDTO response = this.cardService.deleteCard(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @GetMapping("/find/all/{id}")
//    public ResponseEntity<List<CardResponseFullDTO>> findAll(@PathVariable UUID id) {
//        List<CardResponseFullDTO> response = this.cardService.findById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @GetMapping("/v1/find/{id}")
    public ResponseEntity<CardResponseFullDTO> findById(@PathVariable UUID id) {
        CardResponseFullDTO response = this.cardService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/v1/status/find/{id}")
    public ResponseEntity<List<Status>> findAllStatus(@PathVariable UUID id) {
        List<Status> response = this.cardService.getAllStatus(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/v1/status/create/{id}")
    public ResponseEntity<Status> createNewStatus(@PathVariable UUID id, @RequestBody Status status) {
        Status response = this.cardService.createStatus(status, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/v1/status/update/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable UUID id, @RequestBody Status status) {
        Status response = this.cardService.updateStatus(status, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/v1/status/delete/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cardService.deleteStatus(id));
    }

}
