package com.cy.controller;

import com.cy.common.AnswerPage;
import com.cy.common.AspnetPager;
import com.cy.common.basics.SearchCondition;
import com.cy.common.AnswerMessage;
import com.cy.common.Constant;
import com.cy.common.basics.MATCH;
import com.cy.common.utils.CommonTools;
import com.cy.entity.basic.Semester;
import com.cy.service.basic.ISemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/*
 * @program: mtes
 * @description: 学期
 * @author: Cy
 * @create: 2019-03-30
 **/
@Path("semester")
@Component
public class SemesterController {


    private ISemesterService semesterService;

    @Autowired
    public void setSemesterService(ISemesterService semesterService) {
        this.semesterService = semesterService;
    }

    /**
     * 查询学期列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param formdata    查询条件
     * @return 查询结果
     */
    @Path("/page")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerPage<Semester> queryAllSemesterByPage(@DefaultValue("1") @QueryParam("currentPage") Integer currentPage,
                                                       @DefaultValue("10") @QueryParam("pageSize") Integer pageSize,
                                                       @QueryParam("formdata") String formdata) {
        AspnetPager aspnetPager = new AspnetPager(currentPage, pageSize);
        AnswerPage<Semester> answerPage = new AnswerPage<>();
        try {
            String col = " id AS id, name AS name,beginTime AS beginTime,endTime AS endTime ";
            if (formdata == null) {
                answerPage = semesterService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            List<SearchCondition> scLst = CommonTools.formDataToSearchCondition(formdata);
            if (scLst == null || scLst.size() < 1) {
                answerPage = semesterService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            answerPage = semesterService.queryObject4RDBByConds(col, scLst, aspnetPager);
            answerPage.setStatus(true);
            answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
            return answerPage;
        } catch (Exception e) {
            CommonTools.logError("查询学期列表出错", e);
            answerPage.setStatus(false);
            answerPage.setAnswerMsg("查询学期列表出错");
            return answerPage;
        }

    }

    /**
     * 编辑学期信息
     *
     * @param semester 学期
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage editStudent(Semester semester) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            semesterService.updateObject4RDB(semester);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("编辑学期信息出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

    /**
     * 新增学期信息
     *
     * @param semester 学期
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage saveSemester(Semester semester) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            List<Semester> semLst = semesterService.queryObject4RDBByField("", "name", MATCH.NULL, semester.getName());
            if (semLst != null && semLst.size() > 0) {
                for (Semester s : semLst) {
                    if (s.getId().equals(semester.getId())) {
                        answerMessage.setStatus(false);
                        answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_FAILURE_DATA_EXISTS);
                        return answerMessage;
                    }
                }
            }
            semesterService.saveObject4RDB(semester);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("新增学期出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_FAILURE_DATA_EXISTS);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

    /**
     * 删除学期资料管理
     *
     * @param id 学期Id
     * @return 结果
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage deleteSemester(@QueryParam("id") Integer id) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            Semester semester = semesterService.queryObject4RDBById(id);
            if (semester != null) {
                semesterService.deleteObject4RDB(semester);
                answerMessage.setStatus(true);
                answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_SUCCESS);
                return answerMessage;
            }
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg(Constant.ANSWER_DATA_NOT_FOUND);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("删除学期资料时出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

}
