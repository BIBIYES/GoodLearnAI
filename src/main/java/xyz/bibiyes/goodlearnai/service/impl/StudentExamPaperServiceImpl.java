package xyz.bibiyes.goodlearnai.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import xyz.bibiyes.goodlearnai.dto.StudentJoinedExamPaperDTO;
import xyz.bibiyes.goodlearnai.entity.StudentExamPaper;
import xyz.bibiyes.goodlearnai.exception.CustomException;
import xyz.bibiyes.goodlearnai.mapper.StudentExamPaperMapper;
import xyz.bibiyes.goodlearnai.service.StudentExamPaperService;
import xyz.bibiyes.goodlearnai.utils.Result;
import xyz.bibiyes.goodlearnai.vo.AllExamPaperUserStatusVO;
import xyz.bibiyes.goodlearnai.vo.ExamPaperUserStatusVO;

import java.util.List;

@Service
public class StudentExamPaperServiceImpl implements StudentExamPaperService {

    @Autowired
    private StudentExamPaperMapper studentExamPaperMapper;

    @Override
    public boolean joinExamPaper(StudentExamPaper studentExamPaper) {
        try {
            return studentExamPaperMapper.insert(studentExamPaper) > 0;
        } catch (DuplicateKeyException e) {
            return false; // 捕获重复插入异常
        }
    }


    @Override
    public Result getJoinedExamPapersByStudentId(Long studentId) {
        try {
            List<StudentJoinedExamPaperDTO> examPapers = studentExamPaperMapper.getJoinedExamPapersByStudentId(studentId);
            if (examPapers.isEmpty()) {
                return Result.success("暂无加入的试卷", examPapers);
            } else {
                return Result.success("成功获取加入的试卷", examPapers);
            }
        } catch (Exception e) {
            throw new CustomException("试卷id不存在");
        }
    }

    /**
     * 通过试卷id来获取当前试卷下所有完成情况
     *
     * @param examPaperId 试卷id
     * @return 当前试卷学生完成情况
     */
    @Override
    public Result getStudentExamPaperByExamPaperId(Long examPaperId) {
        try {
            List<ExamPaperUserStatusVO> examPaperUserStatusVOList = studentExamPaperMapper.getStudentExamPaperByExamPaperId(examPaperId);
            if (examPaperUserStatusVOList.isEmpty()) {
                throw new CustomException("无学生加入此试卷");
            }
            return Result.success("获取试卷用户状态成功", examPaperUserStatusVOList);
        } catch (CustomException e) {
            throw new CustomException("当前试卷无学生加入试卷");
        }


    }

    // 获取所有试卷的完成情况以及用户名字
    @Override
    public Result getStudentExamPapers() {

       List<AllExamPaperUserStatusVO> allExamPaperUserStatusVOList = studentExamPaperMapper.getStudentExamPapers();
       return Result.success("获取所有试卷用户状态成功", allExamPaperUserStatusVOList);
    }

}
