package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;

import java.util.List;
import java.util.Map;


@Mapper
public interface StudentExamPaperMapper extends BaseMapper<StudentExamPaper> {
    // 查询学生是否已加入某个试卷
    @Select("SELECT COUNT(*) FROM student_exam_paper WHERE student_id = #{studentId} AND exam_paper_id = #{examPaperId}")
    int isStudentJoinedExam(@Param("studentId") Long studentId, @Param("examPaperId") Long examPaperId);

    // 获取学生自己加入所有试卷
    @Select("SELECT student_exam_paper.*, exam_paper.*, `user`.`name` " +
            "FROM student_exam_paper " +
            "JOIN exam_paper ON student_exam_paper.exam_paper_id = exam_paper.exam_paper_id " +
            "JOIN `user` ON exam_paper.teacher_id = `user`.id " +
            "WHERE student_exam_paper.student_id = #{studentId}")
    List<Map<String, Object>> getJoinedExamPapersByStudentId(@Param("studentId") Long studentId);

}
