package xyz.bibiyes.goodlearnai.controller;

import org.springframework.web.bind.annotation.*;
import xyz.bibiyes.goodlearnai.entity.Chapter;
import xyz.bibiyes.goodlearnai.service.ChapterService;
import xyz.bibiyes.goodlearnai.utils.Result;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goodlearnai")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @PostMapping("/chapter")
    public Result createChapter(@RequestBody Chapter chapter) {
        Boolean flag = chapterService.createChapter(chapter);
        if (flag) {
            return Result.success("Chapter", "章节创建成功");
        } else {
            return Result.success("Chapter", "章节创建失败");
        }


    }

    @GetMapping("/chapter")
    public Result getChapterById(@RequestParam Long id) {
        Chapter chapter = chapterService.getChapterById(id);
        return Result.success("Chapter", "章节查询成功", chapter);
    }

    @PutMapping("/chapter")
    public Result updateChapter(@RequestBody Chapter chapter) {
        Boolean flag = chapterService.updateChapter(chapter);
        if (flag) {
            return Result.success("Chapter", "章节更新成功");
        } else {
            return Result.error("Chapter", "章节更新失败");
        }
    }

    @DeleteMapping("/chapter")
    public Result deleteChapter(@RequestBody Chapter chapter) {
        Long id = chapter.getId();
        Boolean flag = chapterService.deleteChapter(id);
        if (flag) {
            return Result.success("Chapter", "章节删除成功");
        } else {
            return Result.error("Chapter", "章节删除失败");
        }

    }
}
