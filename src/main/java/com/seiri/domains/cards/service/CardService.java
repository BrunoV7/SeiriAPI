package com.seiri.domains.cards.service;

import com.seiri.domains.board.Board;
import com.seiri.domains.board.service.BoardService;
import com.seiri.domains.cards.Cards;
import com.seiri.domains.cards.Status;
import com.seiri.domains.cards.dto.CardCreateDTO;
import com.seiri.domains.cards.dto.CardEditDTO;
import com.seiri.domains.cards.dto.CardResponseDTO;
import com.seiri.domains.cards.dto.CardResponseFullDTO;
import com.seiri.domains.cards.exception.FailedToCreateCardException;
import com.seiri.domains.cards.exception.InvalidCardCreationParameters;
import com.seiri.domains.cards.exception.NoCardFoundException;
import com.seiri.domains.cards.repository.CardRepository;
import com.seiri.domains.cards.repository.StatusRepository;
import com.seiri.domains.collumn.service.CollumnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final CollumnService collumnService;
    private final StatusRepository statusRepository;
    private final BoardService boardService;

    public CardService(CardRepository cardRepository,
                       CollumnService collumnService,
                       StatusRepository statusRepository,
                       BoardService boardService) {
        this.cardRepository = cardRepository;
        this.collumnService = collumnService;
        this.statusRepository = statusRepository;
        this.boardService = boardService;
    }

    // ---------------- CREATE ----------------
    public CardResponseDTO newCard(UUID id, CardCreateDTO dto) {
        if (dto == null) throw new InvalidCardCreationParameters("CardCreateDTO is null");
        if (!collumnService.doesCollumnExist(dto.getCollumnId()))
            throw new NoCardFoundException("Column not found");

        Cards card = new Cards();
        card.setTitle(dto.getTitle());
        card.setDescription(dto.getDescription());
        card.setIssueKey("CARD_" + UUID.randomUUID().toString().substring(0, 5).toUpperCase());
        card.setPosition(dto.getPosition());
        card.setPriorityLevel(dto.getPriorityLevel() != null ? dto.getPriorityLevel() : 0);
        card.setArchived(dto.getArchived() != null ? dto.getArchived() : false);
        card.setStartDate(dto.getStartDate());
        card.setEndDate(dto.getEndDate());
        card.setCollumn(collumnService.findByIdInternal(dto.getCollumnId()));

        if (dto.getStatusId() != null) {
            card.setStatus(statusRepository.findById(dto.getStatusId()).orElse(null));
        }

        try {
            card = cardRepository.save(card);
        } catch (Exception e) {
            throw new FailedToCreateCardException(e.getMessage());
        }

        return new CardResponseDTO(card);
    }

    // ---------------- UPDATE ----------------
    @Transactional
    public CardResponseFullDTO updateCard(UUID cardId, CardEditDTO dto) {
        if (dto == null) throw new InvalidCardCreationParameters("CardEditDTO is null");

        Cards card = findByIdInternal(cardId);
        card.updateFromDTO(dto);

        try {
            card.setUpdatedAt(null);
            card = cardRepository.save(card);
            cardRepository.flush();

            System.out.println(card.getUpdatedAt().toString());
        } catch (Exception e) {
            throw new FailedToCreateCardException(e.getMessage())   ;
        }

        return new CardResponseFullDTO(card);
    }

    // ---------------- DELETE ----------------
    public CardResponseDTO deleteCard(UUID cardId) {
        Cards card = findByIdInternal(cardId);
        cardRepository.delete(card);
        return new CardResponseDTO(card);
    }

    // ---------------- READ ----------------
    public CardResponseFullDTO findById(UUID cardId) {
        Cards card = findByIdInternal(cardId);
        return new CardResponseFullDTO(card);
    }

    public List<CardResponseFullDTO> findAllByColumn(UUID columnId) {
        return cardRepository.findAllByCollumn_Id(columnId)
                .stream()
                .map(CardResponseFullDTO::new)
                .collect(Collectors.toList());
    }

    // ---------------- INTERNAL HELPERS ----------------
    public Cards findByIdInternal(UUID cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new NoCardFoundException("No card found with id: " + cardId));
    }

    public boolean existsById(UUID cardId) {
        return cardRepository.existsById(cardId);
    }

    // ---------------- STATUS MANAGEMENT ----------------
    public Status createStatus(Status status, UUID boardId) {
        if (status == null || status.getTitle() == null)
            throw new InvalidCardCreationParameters("Status title is null");

        Board board = boardService.getBoardByIdInternal(boardId);
        if (board == null) throw new InvalidCardCreationParameters("Board not found");

        status.setBoard(board);
        return statusRepository.save(status);
    }

    public Status updateStatus(Status status, UUID boardId) {
        if (status == null || status.getTitle() == null)
            throw new InvalidCardCreationParameters("Status title is null");

        Board board = boardService.getBoardByIdInternal(boardId);
        if (board == null) throw new InvalidCardCreationParameters("Board not found");

        status.setBoard(board);
        return statusRepository.save(status);
    }

    public Status getStatus(UUID statusId) {
        return statusRepository.findById(statusId).orElse(null);
    }

    public String deleteStatus(UUID statusId) {
        Status status = getStatus(statusId);
        if (status == null) throw new NoCardFoundException("Status not found");

        List<Cards> linkedCards = cardRepository.findAllByStatus(status);
        if (!linkedCards.isEmpty()) throw new NoCardFoundException("Cannot delete status with linked cards");

        statusRepository.delete(status);
        return "Status deleted!";
    }

    public List<Status> getAllStatus(UUID boardId) {
        Board board = boardService.getBoardByIdInternal(boardId);
        if (board == null) throw new InvalidCardCreationParameters("Board not found");

        return statusRepository.findAllByBoard(board);
    }

    public List<Cards> findAllByStatus(Status status) {
        if (status == null) throw new InvalidCardCreationParameters("Status is null");
        return cardRepository.findAllByStatus(status);
    }
}
