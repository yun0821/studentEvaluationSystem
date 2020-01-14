package com.cy.controller;

import com.cy.common.basics.SearchCondition;
import com.cy.common.AnswerMessage;
import com.cy.common.Constant;
import com.cy.common.MemoryVariableUtils;
import com.cy.common.basics.MATCH;
import com.cy.common.utils.CommonTools;
import com.cy.entity.basic.Teacher;
import com.cy.entity.basic.UserDetail;
import com.cy.service.basic.ISemesterService;
import com.cy.service.basic.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @program: mtes
 * @description: 评教结果
 * @author: Cy
 * @create: 2019-03-30
 **/
@Path("/result")
@Component
public class EvaluateResultController {

    private ITeacherService teacherService;

    private ISemesterService semesterService;

    @Autowired
    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Autowired
    public void setSemesterService(ISemesterService semesterService) {
        this.semesterService = semesterService;
    }

    /**
     * 教师查询该学期的评教结果
     *
     * @param semester 学期id
     * @return 查询结果
     */
    @GET
    @Path("stuResult")
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage queryResultByTeacher(@QueryParam("semester") Integer semester) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            UserDetail user = MemoryVariableUtils.getCurrentUserDetail();
            String stuName = user.getName();
            List<SearchCondition> scLst = new ArrayList<>();
            scLst.add(new SearchCondition("name", MATCH.NULL, stuName));
            List<Teacher> teacherList = teacherService.queryObject4RDBByConds("", scLst);
            if (teacherList == null || teacherList.size() < 1) {
                answerMessage.setAnswerMsg("系统未查询到该教师");
                answerMessage.setStatus(false);
                return answerMessage;
            }
            Integer teaId = teacherList.get(0).getId();
            List<Map<String, Object>> resultLst = teacherService.queryEvaResult(semester, teaId);
            answerMessage.setRecords(resultLst);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
            return answerMessage;

        } catch (Exception e) {
            CommonTools.logError("教师查询结果出错", e);
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_FAILURE);
            return answerMessage;
        }
    }


    /**
     * 查询学期列表,用于下拉框
     *
     * @return 结果
     */
    @GET
    @Path("semesterLst")
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage querySemesterList() {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            String col = "id AS id, name AS name";
            List<Map<String, Object>> result = semesterService.queryAllObjectMap4RDB(col);
            answerMessage.setStatus(true);
            answerMessage.setRecords(result);
            answerMessage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("查询学期列表出错", e);
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_FAILURE);
            return answerMessage;
        }
    }

}
