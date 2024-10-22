package com.example.javainit.util.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/util")
public class UtilController {

    @GetMapping("/pdfDownload")
    public String pdfDownload() {
        return "util/pdfDownload";
    }
}
