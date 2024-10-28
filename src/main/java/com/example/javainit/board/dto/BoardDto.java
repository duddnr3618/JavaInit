package com.example.javainit.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String bTitle;   // CamelCase로 수정
    private String bWriter;
    private String bContent;
    private LocalDate date;
}
