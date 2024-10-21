package xyz.bibiyes.goodlearnai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;
import xyz.bibiyes.goodlearnai.service.StudentExamPaperService;
import xyz.bibiyes.goodlearnai.utils.Result;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/studentExamPapers")
@CrossOrigin
public class StudentExamPaperController {

    @Autowired
    private StudentExamPaperService studentExamPaperService;
}
