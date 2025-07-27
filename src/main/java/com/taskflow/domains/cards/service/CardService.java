package com.taskflow.domains.cards.service;

import com.taskflow.domains.cards.Cards;
import com.taskflow.domains.cards.Status;
import com.taskflow.domains.cards.dto.CardDTO;
import com.taskflow.domains.cards.dto.CardEditDTO;
import com.taskflow.domains.cards.dto.CardResponseDTO;
import com.taskflow.domains.cards.dto.CardResponseFullDTO;
import com.taskflow.domains.cards.exception.FailedToCreateCardException;
import com.taskflow.domains.cards.exception.InvalidCardCreationParameters;
import com.taskflow.domains.cards.exception.NoCardFoundException;
import com.taskflow.domains.cards.repository.CardRepository;
import com.taskflow.domains.collumn.exception.NoCollumnFoundException;
import com.taskflow.domains.collumn.service.CollumnService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CollumnService collumnService;

    public CardService(CardRepository cardRepository, CollumnService collumnService) {
        this.cardRepository = cardRepository;
        this.collumnService = collumnService;
    }

    public CardResponseDTO newCard(UUID id, @Valid CardDTO cardDTO) {
        if(!this.collumnService.doesCollumnExist(id)) throw new NoCollumnFoundException();
        if(cardDTO == null) throw new InvalidCardCreationParameters("Object was null");
        Cards cards = new Cards(cardDTO);
        cards.setCollumn(this.collumnService.findByIdInternal(id));
        try {
            cards = this.cardRepository.save(cards);
        } catch (Exception e) {
            throw new FailedToCreateCardException(e.getMessage());
        }
        return new CardResponseDTO(cards);
    }

    public CardResponseDTO updateCard(UUID id, @Valid CardEditDTO cardDTO) {
        if (!existsById(id)) throw new NoCardFoundException("No card found with the selected Id");
        if (cardDTO == null) throw new InvalidCardCreationParameters("Object was null");
        System.out.println(">>> Descrição no DTO: " + cardDTO.getDescription());
        Cards card = this.findByIdInternal(id);

        if (cardDTO.getTitle() != null && !cardDTO.getTitle().isBlank()) {
            card.setTitle(cardDTO.getTitle());
        }

        if (cardDTO.getDescription() != null && !cardDTO.getDescription().isBlank()) {
            card.setDescription(cardDTO.getDescription());
        }

        if (cardDTO.getStartDate() != null) {
            card.setStartDate(cardDTO.getStartDate());
        }

        if (cardDTO.getEndDate() != null) {
            card.setEndDate(cardDTO.getEndDate());
        }

        if (cardDTO.getStatus() != null) {
            card.setStatus(cardDTO.getStatus());
        }
        System.out.println(">>> Antes do save: " + card.getDescription());
        try {
            card = this.cardRepository.save(card);
        } catch (Exception e) {
            throw new FailedToCreateCardException(e.getMessage());
        }

        return new CardResponseDTO(card);
    }

    public CardResponseDTO deleteCard(UUID id) {
        if(!this.existsById(id)) throw new NoCardFoundException("No card found with the selected Id");
        Cards card = this.findByIdInternal(id);
        this.cardRepository.delete(card);
        card.setStatus(Status.DELETED);
        return new CardResponseDTO(card);
    }

    public List<CardResponseFullDTO> findAll(UUID id) {
        System.out.println(">>> Antes do findAll: " + id);
        return this.cardRepository.findAllByCollumn_Id(id).stream().map(CardResponseFullDTO::new).collect(Collectors.toList());
    }

    public CardResponseFullDTO findById(UUID id) {
        Cards card = this.cardRepository.findById(id).orElse(null);
        if(card == null) throw new NoCardFoundException("No card found with the selected Id");
        return new CardResponseFullDTO(card);
    }

    public Cards findByIdInternal(UUID id) {
        Cards card = this.cardRepository.findById(id).orElse(null);
        if(card == null) throw new NoCardFoundException("No card found with the selected Id");
        return card;
    }

    public Boolean existsById(UUID id) {
        return this.cardRepository.existsById(id);
    }
}
