package com.cy.controller;

import com.cy.common.AnswerPage;
import com.cy.common.AspnetPager;
import com.cy.common.basics.SearchCondition;
import com.cy.common.AnswerMessage;
import com.cy.common.Constant;
import com.cy.common.basics.MATCH;
import com.cy.common.utils.CommonTools;
import com.cy.entity.basic.Teacher;
import com.cy.service.basic.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/*
 * @program: mtes
 * @description: 教师页面
 * @author: Cy
 * @create: 2019-03-30
 **/
@Path("/teacher")
@Component
public class TeacherController {


    private ITeacherService teacherService;

    @Autowired
    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 查询教师列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param formdata    查询条件
     * @return 查询结果
     */
    @Path("/page")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerPage<Teacher> queryAllTeacherByPage(@DefaultValue("1") @QueryParam("currentPage") Integer currentPage,
                                                     @DefaultValue("10") @QueryParam("pageSize") Integer pageSize,
                                                     @QueryParam("formdata") String formdata) {
        AspnetPager aspnetPager = new AspnetPager(currentPage, pageSize);
        AnswerPage<Teacher> answerPage = new AnswerPage<>();
        try {
            String col = " id AS id, name AS name,number AS number,belongCollege AS belongCollege," +
                    "type AS type,showSort AS showSort ";
            if (formdata == null) {
                answerPage = teacherService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            List<SearchCondition> scLst = CommonTools.formDataToSearchCondition(formdata);
            if (scLst == null || scLst.size() < 1) {

                answerPage = teacherService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            answerPage = teacherService.queryObject4RDBByConds(col, scLst, aspnetPager);
            answerPage.setStatus(true);
            answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
            return answerPage;
        } catch (Exception e) {
            CommonTools.logError("查询教师列表出错", e);
            answerPage.setStatus(false);
            answerPage.setAnswerMsg("查询教师列表出错");
            return answerPage;
        }

    }

    /**
     * 编辑教师信息
     *
     * @param teacher 学生
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage editTeacher(Teacher teacher) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            teacherService.updateObject4RDB(teacher);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("编辑教师信息出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

    /**
     * 新增教师信息
     *
     * @param teacher 学生
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage saveTeacher(Teacher teacher) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            String[] fields = {"name", "number"};
            Object[] values = {teacher.getName(), teacher.getNumber()};
            List<Teacher> teaLst = teacherService.queryObject4RDBByFields("", fields, MATCH.NULL, values);
            if (teaLst != null && teaLst.size() > 0) {
                for (Teacher t : teaLst) {
                    if (t.getId().equals(teacher.getId())) {
                        answerMessage.setStatus(false);
                        answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_FAILURE_DATA_EXISTS);
                        return answerMessage;
                    }
                }
            }
            teacherService.saveObject4RDB(teacher);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("新增教师信息出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_FAILURE_DATA_EXISTS);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

    /**
     * 删除教师资料管理
     *
     * @param id 学生Id
     * @return 结果
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage deleteTeacher(@QueryParam("id") Integer id) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            Teacher teacher = teacherService.queryObject4RDBById(id);
            if (teacher != null) {
                teacherService.deleteObject4RDB(teacher);
                answerMessage.setStatus(true);
                answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_SUCCESS);
                return answerMessage;
            }
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg(Constant.ANSWER_DATA_NOT_FOUND);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("删除教师资料时出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

}
