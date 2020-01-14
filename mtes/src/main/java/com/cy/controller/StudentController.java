package com.cy.controller;


import com.cy.common.AnswerPage;
import com.cy.common.AspnetPager;
import com.cy.common.basics.SearchCondition;
import com.cy.service.basic.IStudentService;
import com.cy.common.AnswerMessage;
import com.cy.common.Constant;
import com.cy.common.basics.MATCH;
import com.cy.common.utils.CommonTools;
import com.cy.entity.basic.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/student")
@Component
public class StudentController {

    private IStudentService studentService;

    @Autowired
    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 查询学生列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param formdata    查询条件
     * @return 查询结果
     */
    @Path("/page")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerPage<Student> queryAllStudentByPage(@DefaultValue("1") @QueryParam("currentPage") Integer currentPage,
                                                     @DefaultValue("10") @QueryParam("pageSize") Integer pageSize,
                                                     @QueryParam("formdata") String formdata) {
        AspnetPager aspnetPager = new AspnetPager(currentPage, pageSize);
        AnswerPage<Student> answerPage = new AnswerPage<>();
        try {
            String col = " id AS id, name AS name,stuId AS stuId,className AS className,major AS major," +
                    "state AS state, type AS type,showSort AS showSort ";
            if (formdata == null) {
                answerPage = studentService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            List<SearchCondition> scLst = CommonTools.formDataToSearchCondition(formdata);
            if (scLst == null || scLst.size() < 1) {

                answerPage = studentService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            answerPage = studentService.queryObject4RDBByConds(col, scLst, aspnetPager);
            answerPage.setStatus(true);
            answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
            return answerPage;
        } catch (Exception e) {
            CommonTools.logError("查询学生列表出错", e);
            answerPage.setStatus(false);
            answerPage.setAnswerMsg("查询学生列表出错");
            return answerPage;
        }

    }

    /**
     * 编辑学生信息
     *
     * @param student 学生
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage editStudent(Student student) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            studentService.updateObject4RDB(student);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("编辑学生信息出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

    /**
     * 新增学生信息
     *
     * @param student 学生
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage saveStudent(Student student) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            String[] fields = {"name", "stuId"};
            Object[] values = {student.getName(), student.getStuId()};
            List<Student> stuLst = studentService.queryObject4RDBByFields("", fields, MATCH.NULL, values);
            if (stuLst != null && stuLst.size() > 0) {
                for (Student s : stuLst) {
                    if (s.getId().equals(student.getId())) {
                        answerMessage.setStatus(false);
                        answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_FAILURE_DATA_EXISTS);
                        return answerMessage;
                    }
                }
            }
            studentService.saveObject4RDB(student);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("新增学生信息出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_FAILURE_DATA_EXISTS);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

    /**
     * 删除学生资料管理
     *
     * @param id 学生Id
     * @return 结果
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage deleteStudent(@QueryParam("id") Integer id) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            Student student = studentService.queryObject4RDBById(id);
            if (student != null) {
                studentService.deleteObject4RDB(student);
                answerMessage.setStatus(true);
                answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_SUCCESS);
                return answerMessage;
            }
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg(Constant.ANSWER_DATA_NOT_FOUND);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("删除学生资料时出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }
}
