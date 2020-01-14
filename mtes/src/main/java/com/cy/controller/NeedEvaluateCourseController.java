package com.cy.controller;

import com.cy.common.basics.SearchCondition;
import com.cy.service.basic.IStudentService;
import com.cy.common.AnswerMessage;
import com.cy.common.Constant;
import com.cy.common.MemoryVariableUtils;
import com.cy.common.basics.MATCH;
import com.cy.common.utils.CommonTools;
import com.cy.entity.basic.Student;
import com.cy.entity.basic.UserDetail;
import com.cy.service.basic.ITeacherService;
import com.cy.service.work.IEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @program: mtes
 * @description: 需要评价的课程列表（学生登录跳转）
 * @author: Cy
 * @create: 2019-03-26
 **/
@Path("/chooseCourse")
@Component
public class NeedEvaluateCourseController {

    private ITeacherService teacherService;

    private IEvaluateService evaluateService;

    private IStudentService studentService;

    @Autowired
    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Autowired
    public void setEvaluateService(IEvaluateService evaluateService) {
        this.evaluateService = evaluateService;
    }

    @Autowired
    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 查询该学生选则学期的课程信息列表
     *
     * @return 查询结果
     */
    @Path("courseInfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage queryCourseToEvaluate(@QueryParam("semester") Integer semester) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            UserDetail uesr = MemoryVariableUtils.getCurrentUserDetail();
            Integer stuId = uesr.getId();
            if (null == stuId) {
                answerMessage.setStatus(false);
                answerMessage.setAnswerMsg("系统获取当前用户失败");
                return answerMessage;
            }
            List<Map<String, Object>> courseInfoLst = teacherService.queryCourseInfoLst(stuId, semester);
            if (courseInfoLst == null || courseInfoLst.size() < 1) {
                answerMessage.setStatus(false);
                answerMessage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_FAILURE);
                return answerMessage;
            }
            answerMessage.setStatus(true);
            answerMessage.setRecords(courseInfoLst);
            answerMessage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("查询课程信息列表出错", e);
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_FAILURE);
            return answerMessage;
        }
    }

    @POST
    @Path("saveResult")
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("unchecked")
    public AnswerMessage saveEvaluate(Map<String, Object> mp) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            UserDetail user = MemoryVariableUtils.getCurrentUserDetail();
            String stuName = user.getName();
            List<SearchCondition> scLst = new ArrayList<>();
            scLst.add(new SearchCondition("name", MATCH.NULL, stuName));
            List<Student> studentList = studentService.queryObject4RDBByConds("", scLst);
            if (studentList == null || studentList.size() < 1) {
                answerMessage.setAnswerMsg("系统未查询到该学生");
                answerMessage.setStatus(false);
                return answerMessage;
            }
            Integer stuId = studentList.get(0).getId();
            // 内容评价选项结果列表
            List<Integer> levelLst = (List<Integer>) mp.get("level");
            // 评价内容Id
            List<Integer> contentIdLst = (List<Integer>) mp.get("contentIds");
            Integer teacherId = (Integer) mp.get("teacherId");
            Integer courseId = (Integer) mp.get("courseId");
            if (levelLst == null || levelLst.size() < 1 || contentIdLst == null || contentIdLst.size() < 1) {
                answerMessage.setStatus(false);
                answerMessage.setAnswerMsg("评教出错");
                return answerMessage;
            }
            if (levelLst.size() != contentIdLst.size()) {
                answerMessage.setStatus(false);
                answerMessage.setAnswerMsg("有未评价的选项内容");
                return answerMessage;
            }
            evaluateService.saveEvaluateLst(contentIdLst, levelLst, teacherId, courseId, stuId);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg("评价成功");
            return answerMessage;

        } catch (Exception e) {
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg("评价失败");
            return answerMessage;
        }
    }
}
