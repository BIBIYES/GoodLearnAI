package xyz.bibiyes.goodlearnai.service;


import xyz.bibiyes.goodlearnai.entity.Chapter;

import java.util.List;

public interface ChapterService {
    Boolean createChapter(Chapter chapter);
    Chapter getChapterById(Long id);
    Boolean updateChapter(Chapter chapter);
    Boolean deleteChapter(Long id);

}
