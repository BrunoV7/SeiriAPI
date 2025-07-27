package com.taskflow.domains.collumn.service;

import com.taskflow.domains.board.exception.BoardNotFoundException;
import com.taskflow.domains.board.service.BoardService;
import com.taskflow.domains.collumn.Collumn;
import com.taskflow.domains.collumn.dto.CollumnDTO;
import com.taskflow.domains.collumn.dto.CollumnDeleteDTO;
import com.taskflow.domains.collumn.dto.CollumnResponseDTO;
import com.taskflow.domains.collumn.exception.FailedToCreateCollumnException;
import com.taskflow.domains.collumn.exception.NoCollumnFoundException;
import com.taskflow.domains.collumn.repository.CollumnRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CollumnService {

    private final BoardService boardService;
    private final CollumnRepository collumnRepository;

    public CollumnService(BoardService boardService, CollumnRepository collumnRepository) {
        this.boardService = boardService;
        this.collumnRepository = collumnRepository;
    }

    public CollumnResponseDTO newCollumn(UUID id, CollumnDTO collumn) {
        if(!boardService.isBoardPresent(id)) throw new BoardNotFoundException("No board found for id " + id);
        if(collumn == null) throw new InvalidParameterException();
        Collumn newCollumn = new Collumn(
                collumn.getName(),
                boardService.getBoardByIdInternal(id)
        );
        try{
            newCollumn = collumnRepository.save(newCollumn);
        }catch(Exception e){
            throw new FailedToCreateCollumnException();
        }
        return new CollumnResponseDTO(newCollumn);
    }

    public CollumnResponseDTO updateCollumn(UUID id, CollumnDTO collumn) {
        if(collumn == null) throw new InvalidParameterException();
        Collumn newCollumn = this.findByIdInternal(id);
        if(newCollumn == null) throw new NoCollumnFoundException("No collumn found for id " + id);
        newCollumn.setName(collumn.getName());
        try {
            newCollumn = collumnRepository.save(newCollumn);
        }catch(Exception e){
            throw new FailedToCreateCollumnException("Failed to update collumn");
        }
        return new CollumnResponseDTO(newCollumn);
    }

    public List<CollumnResponseDTO> findAll(UUID id) {
        return collumnRepository.findAllByBoard_Id(id).stream().map(CollumnResponseDTO::new).collect(Collectors.toList());
    }

    public CollumnResponseDTO findById(UUID id) {
        return new CollumnResponseDTO(findByIdInternal(id));
    }

    public Collumn findByIdInternal(UUID id) {
        return this.collumnRepository.findById(id).orElse(null);
    }

    public Boolean doesCollumnExist(UUID id) {
        return this.collumnRepository.existsById(id);
    }

    public CollumnDeleteDTO deleteById(UUID id) {
        if(!this.doesCollumnExist(id)) throw new NoCollumnFoundException("No collumn found for id " + id);
        if(id == null) throw new InvalidParameterException();
        Collumn collumn = this.findByIdInternal(id);
        this.collumnRepository.delete(collumn);
        return new CollumnDeleteDTO(id);
    }
}
