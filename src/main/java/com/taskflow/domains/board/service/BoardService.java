package com.taskflow.domains.board.service;

import com.taskflow.domains.board.Board;
import com.taskflow.domains.board.dto.BoardDTO;
import com.taskflow.domains.board.dto.BoardDeletedDTO;
import com.taskflow.domains.board.dto.BoardEditDTO;
import com.taskflow.domains.board.dto.BoardResponseDTO;
import com.taskflow.domains.board.exception.BoardNotFoundException;
import com.taskflow.domains.board.exception.FailedToCreateBoardException;
import com.taskflow.domains.board.repository.BoardRepository;
import com.taskflow.domains.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;

    public BoardService(BoardRepository boardRepository, UserService userService) {
        this.boardRepository = boardRepository;
        this.userService = userService;
    }

    public BoardResponseDTO createBoard(BoardDTO boardDTO) {
        if(boardDTO == null) return null;
        Board board = new Board();
        try{
            board = boardRepository.save(new Board(boardDTO.getTitle(), boardDTO.getDescription(), userService.getCurrentUser()));
        } catch (Exception e){
            throw new FailedToCreateBoardException();
        }
        return new BoardResponseDTO(board);
    }

    public BoardResponseDTO editBoard(UUID id, BoardEditDTO boardDTO) {
        if(boardDTO == null) return null;
        Board board = boardRepository.getBoardById(id);
        if(board == null) return null;

        //Fazendo verificações para saber o que vai ser modificado
        if(!boardDTO.getTitle().isEmpty()){
            board.setTitle(boardDTO.getTitle());
        }
        if(!boardDTO.getDescription().isEmpty()){
            board.setDescription(boardDTO.getDescription());
        }
        board = boardRepository.save(board);
        return new BoardResponseDTO(board);
    }

    public String deleteBoard(UUID id) {
        if(boardRepository.getBoardById(id) == null) return null;
        Board board = boardRepository.getBoardById(id);
        if(board == null) return null;
        boardRepository.delete(board);
        return new BoardDeletedDTO(id.toString()).toString();
    }

    public BoardResponseDTO getBoardById(UUID id) {
        if(boardRepository.getBoardById(id) == null) return null;
        Board board = boardRepository.getBoardById(id);
        if(board == null) throw new BoardNotFoundException();
        return new BoardResponseDTO(board);
    }

    public List<BoardResponseDTO> getBoards() {
        List<Board> boards = boardRepository.findAll();
        List<BoardResponseDTO> boardResponseDTOS = new ArrayList<>();
        for(Board board : boards){
            boardResponseDTOS.add(new BoardResponseDTO(board));
        }
        return boardResponseDTOS;
    }

    public Boolean isBoardPresent(UUID id) {
        return boardRepository.getBoardById(id) != null;
    }

    public Board getBoardByIdInternal(UUID id){
        return boardRepository.getBoardById(id);
    }

}
