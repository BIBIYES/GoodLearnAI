package xyz.bibiyes.goodlearnai.controller;


import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.Subject;
import xyz.bibiyes.goodlearnai.service.SubjectService;
import xyz.bibiyes.goodlearnai.utils.Result;

import javax.annotation.Resource;


@RestController
@RequestMapping("/goodlearnai")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @GetMapping("/subject")
    public Result getAllSubjects() {

        return Result.success("Subject", "返回科目成功", subjectService.list());
    }

    @PostMapping("/subject")
    public Result addSubject(String name) {
        return Result.success("Subject", "添加科目成功", subjectService.save(name));
    }

    @PutMapping("/subject")
    public Result updateSubject(@RequestBody Subject subject) {
        return Result.success("Subject", "修改科目成功", subjectService.updateById(subject));
    }

    @DeleteMapping("/subject")
    public boolean deleteSubject(@RequestParam(defaultValue = "id") Long id) {
        return subjectService.removeById(id);
    }
}
