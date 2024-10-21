package xyz.bibiyes.goodlearnai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import xyz.bibiyes.goodlearnai.dto.StudentJoinedExamPaperDTO;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;

import java.util.List;



@Mapper
public interface StudentExamPaperMapper extends BaseMapper<StudentExamPaper> {
    // 获取学生自己加入所有试卷
    @Select("SELECT " +
            "ep.exam_paper_id AS examPaperId, " +
            "sep.join_date AS joinDate, " +
            "u.username AS username, " +
            "ep.exam_paper_created_date AS examPaperCreatedDate, " +
            "ep.exam_paper_name AS examPaperName, " +
            "sep.status AS status " +
            "FROM student_exam_paper sep " +
            "JOIN exam_paper ep ON sep.exam_paper_id = ep.exam_paper_id " +
            "JOIN user u ON ep.user_id = u.user_id " +
            "WHERE sep.user_id = #{userId} " +
            "ORDER BY sep.join_date DESC")
    List<StudentJoinedExamPaperDTO> getJoinedExamPapersByStudentId(@Param("userId") Long userId);

    @Update("UPDATE student_exam_paper " +
            "SET status = '已完成' " +
            "WHERE user_id = #{userId} AND exam_paper_id = #{examPaperId}")
    int updateStatus(@Param("userId") Long userId, @Param("examPaperId") Long examPaperId);

}
