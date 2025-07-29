package com.seiri.domains.board.controller;

import com.seiri.domains.board.dto.*;
import com.seiri.domains.board.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/new")
    public ResponseEntity<BoardResponseDTO> newBoard(@RequestBody @Valid BoardDTO boardDTO) {
        BoardResponseDTO response = boardService.createBoard(boardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BoardResponseDTO> editBoard(@PathVariable UUID id, @RequestBody @Valid BoardEditDTO boardDTO) {
        BoardResponseDTO response = boardService.editBoard(id, boardDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BoardDeletedDTO> deleteBoard(@PathVariable UUID id) {
        BoardDeletedDTO response = new BoardDeletedDTO(boardService.deleteBoard(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<BoardResponseDTO> findBoardById(@PathVariable UUID id) {
        BoardResponseDTO response = boardService.getBoardById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<BoardResponseDTO>> findAllBoards() {
        List<BoardResponseDTO> response = boardService.getBoards();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find/full/{id}")
    public ResponseEntity<BoardResponseFullDTO> findBoardFull(@PathVariable UUID id) {
        BoardResponseFullDTO response = boardService.findBoardFull(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
