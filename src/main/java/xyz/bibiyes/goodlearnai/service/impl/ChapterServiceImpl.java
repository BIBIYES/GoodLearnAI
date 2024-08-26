package xyz.bibiyes.goodlearnai.service.impl;

import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.entity.Chapter;
import xyz.bibiyes.goodlearnai.mapper.ChapterMapper;
import xyz.bibiyes.goodlearnai.service.ChapterService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Resource
    ChapterMapper chapterMapper;

    @Override
    public Boolean createChapter(Chapter chapter) {
        return chapterMapper.insert(chapter) > 0;
    }

    @Override
    public Chapter getChapterById(Long id) {
        return chapterMapper.selectById(id);
    }

    @Override
    public Boolean updateChapter(Chapter chapter) {
        return chapterMapper.updateById(chapter) > 0;
    }

    @Override
    public Boolean deleteChapter(Long id) {
        return chapterMapper.deleteById(id) > 0;
    }

    @Override
    public List<Chapter> getAllChapters() {
        return chapterMapper.selectList(null);
    }
}
