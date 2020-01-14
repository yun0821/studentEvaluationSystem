package com.cy.controller;

import com.cy.common.AnswerPage;
import com.cy.common.AspnetPager;
import com.cy.common.basics.SearchCondition;
import com.cy.common.AnswerMessage;
import com.cy.common.Constant;
import com.cy.common.basics.MATCH;
import com.cy.common.utils.CommonTools;
import com.cy.entity.work.Content;
import com.cy.service.work.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/*
 * @program: mtes
 * @description: 评教内容控制层
 * @author: Cy
 * @create: 2019-03-26
 **/
@Path("content")
@Component
public class ContentController {


    private IContentService contentService;

    @Autowired
    public void setContentService(IContentService contentService) {
        this.contentService = contentService;
    }

    /**
     * 查询评教内容列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param formdata    查询条件
     * @return 查询结果
     */
    @Path("/page")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerPage<Content> queryAllContentByPage(@DefaultValue("1") @QueryParam("currentPage") Integer currentPage,
                                                     @DefaultValue("10") @QueryParam("pageSize") Integer pageSize,
                                                     @QueryParam("formdata") String formdata) {
        AspnetPager aspnetPager = new AspnetPager(currentPage, pageSize);
        AnswerPage<Content> answerPage = new AnswerPage<>();
        try {
            String col = " id AS id, name AS name,weight AS weight, showSort AS showSort ";
            if (formdata == null) {
                answerPage = contentService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            List<SearchCondition> scLst = CommonTools.formDataToSearchCondition(formdata);
            if (scLst == null || scLst.size() < 1) {

                answerPage = contentService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            answerPage = contentService.queryObject4RDBByConds(col, scLst, aspnetPager);
            answerPage.setStatus(true);
            answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
            return answerPage;
        } catch (Exception e) {
            CommonTools.logError("查询评教内容列表出错", e);
            answerPage.setStatus(false);
            answerPage.setAnswerMsg("查询评教内容列表出错");
            return answerPage;
        }

    }

    /**
     * 编辑评教内容
     *
     * @param content 评教内容
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage editContent(Content content) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            contentService.updateObject4RDB(content);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("编辑评教内容出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

    /**
     * 新增评教内容
     *
     * @param content 评教内容
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage saveContent(Content content) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            String[] fields = {"name"};
            Object[] values = {content.getName()};
            List<Content> conLst = contentService.queryObject4RDBByFields("", fields, MATCH.NULL, values);
            if (conLst != null && conLst.size() > 0) {
                for (Content s : conLst) {
                    if (s.getId().equals(content.getId())) {
                        answerMessage.setStatus(false);
                        answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_FAILURE_DATA_EXISTS);
                        return answerMessage;
                    }
                }
            }
            contentService.saveObject4RDB(content);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError(Constant.ANSWER_DELETE_FAILURE, e);
            answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_FAILURE_DATA_EXISTS);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

    /**
     * 删除评教内容管理
     *
     * @param id 评教内容Id
     * @return 结果
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage deleteContent(@QueryParam("id") Integer id) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            Content content = contentService.queryObject4RDBById(id);
            if (content != null) {
                contentService.deleteObject4RDB(content);
                answerMessage.setStatus(true);
                answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_SUCCESS);
                return answerMessage;
            }
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg(Constant.ANSWER_DATA_NOT_FOUND);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("删除评教内容时出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }
}
