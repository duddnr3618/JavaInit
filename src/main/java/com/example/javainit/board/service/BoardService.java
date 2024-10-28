package com.example.javainit.board.service;

import com.example.javainit.board.dto.BoardDto;
import com.example.javainit.board.entity.BoardEntity;
import com.example.javainit.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto) {

        BoardEntity boardEntity = toBoardEntity(boardDto);
        System.out.println("게시글 작성"+ boardEntity);
        boardRepository.save(boardEntity);
    }

    public BoardEntity toBoardEntity(BoardDto boardDto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDto.getId());
        boardEntity.setBTitle(boardDto.getBTitle());
        boardEntity.setBWriter(boardDto.getBWriter());
        boardEntity.setBContent(boardDto.getBContent());
        boardEntity.setDate(LocalDate.now());
        return boardEntity;

    }

    public List<BoardDto> boardList() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        return boardEntities.stream()
                .map(this::toBoardEntity) // toBoardEntity 메서드 사용
                .collect(Collectors.toList()); // 리스트로 변환하여 반환
    }

    public BoardDto toBoardEntity(BoardEntity boardEntity) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(boardEntity.getId());
        boardDto.setBTitle(boardEntity.getBTitle());
        boardDto.setBWriter(boardEntity.getBWriter());
        boardDto.setBContent(boardEntity.getBContent());
        boardDto.setDate(boardEntity.getDate());
        return boardDto;
    }
}
