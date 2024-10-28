package com.example.javainit.board.controller;

import com.example.javainit.board.dto.BoardDto;
import com.example.javainit.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/boardForm")
    public String createBard() {
        return "board/boardForm";
    }

    @PostMapping("/submint")
    public String saveBoard(@ModelAttribute BoardDto boardDto) {
        boardService.save(boardDto);
        System.out.println("게시판 작성 완료");
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String listBoard(Model model) {
        List<BoardDto> boardList = boardService.boardList();
        model.addAttribute("boardList", boardList);

        return "board/list";
    }


}
