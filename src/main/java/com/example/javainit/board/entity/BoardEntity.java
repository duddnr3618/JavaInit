package com.example.javainit.board.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "b_title")
    private String bTitle;

    @Column(name = "b_writer")
    private String bWriter;

    @Column(name = "b_content")
    private String bContent;

    @Column(name = "date")
    private LocalDate date;


}
