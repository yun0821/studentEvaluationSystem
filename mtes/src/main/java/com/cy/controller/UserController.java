package com.cy.controller;

import com.cy.common.*;
import com.cy.common.basics.MATCH;
import com.cy.common.basics.SearchCondition;
import com.cy.common.utils.CommonTools;
import com.cy.entity.basic.UserDetail;
import com.cy.service.basic.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/*
 * @program: mtes
 * @description: 用户资料管理
 * @author: Cy
 * @create: 2019-03-29
 **/
@Path("/user")
@Component
public class UserController {

    private IUserDetailService userDetailService;

    @Autowired
    public void setUserDetailService(IUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    /**
     * 查询用户列表
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param formdata    查询条件
     * @return 查询结果
     */
    @Path("/page")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerPage<UserDetail> queryAllUserDetailByPage(@DefaultValue("1") @QueryParam("currentPage") Integer
                                                                       currentPage,
                                                           @DefaultValue("10") @QueryParam("pageSize") Integer pageSize,
                                                           @QueryParam("formdata") String formdata) {
        AspnetPager aspnetPager = new AspnetPager(currentPage, pageSize);
        AnswerPage<UserDetail> answerPage = new AnswerPage<>();
        try {
            String col = " id AS id, name AS name,account AS account,password AS password,type AS type," +
                    "belongCollege AS belongCollege, email AS email,phone AS phone,showSort AS showSort,deadTime AS " +
                    "deadTime, " +
                    " status AS status";
            if (formdata == null) {
                answerPage = userDetailService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            List<SearchCondition> scLst = CommonTools.formDataToSearchCondition(formdata);
            if (scLst == null || scLst.size() < 1) {

                answerPage = userDetailService.queryAllObject4RDB(col, aspnetPager);
                answerPage.setStatus(true);
                answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
                return answerPage;
            }
            answerPage = userDetailService.queryObject4RDBByConds(col, scLst, aspnetPager);
            answerPage.setStatus(true);
            answerPage.setAnswerMsg(Constant.ANSWER_QUERY_DATA_SUCCESS);
            return answerPage;
        } catch (Exception e) {
            CommonTools.logError("查询用户列表出错", e);
            answerPage.setStatus(false);
            answerPage.setAnswerMsg("查询用户列表出错");
            return answerPage;
        }

    }

    /**
     * 修改密码
     *
     * @param mp 前台传过来的值
     * @return 结果
     */
    @POST
    @Path("/resetPwd")
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage resetUserPwd(Map<String, Object> mp) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            UserDetail user = MemoryVariableUtils.getCurrentUserDetail();
            String newPwd = mp.get("newPwd").toString();
            String oldPwd = mp.get("oldPwd").toString();
            String repeatNewPwd = mp.get("repeadNewPwd").toString();
            if (!repeatNewPwd.equals(newPwd)) {
                answerMessage.setStatus(false);
                answerMessage.setAnswerMsg("两次输入的新密码不一致");
                return answerMessage;
            }
            if (user.getPassword().equals(oldPwd)) {
                user.setPassword(newPwd);
                userDetailService.updateObject4RDB(user);
                answerMessage.setStatus(true);
                answerMessage.setAnswerMsg("修改成功");
                return answerMessage;
            } else {
                answerMessage.setStatus(false);
                answerMessage.setAnswerMsg("旧密码不正确");
                return answerMessage;
            }
        } catch (Exception e) {
            CommonTools.logError("密码修改出错", e);
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg("密码修改出错");
            return answerMessage;
        }
    }

    /**
     * 编辑学生信息
     *
     * @param userDetail 用户
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage editUserDetail(UserDetail userDetail) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            userDetailService.updateObject4RDB(userDetail);
            answerMessage.setStatus(true);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_SUCCESS);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("编辑用户信息出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_UPDATE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

    /**
     * 新增用户信息
     *
     * @param userDetail 用户
     * @return 结果
     * @author Cy
     * @date 2019/03/22
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage saveUserDetail(UserDetail userDetail) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            String[] fields = {"name", "phone"};
            Object[] values = {userDetail.getName(), userDetail.getPhone()};
            List<UserDetail> userLst = userDetailService.queryObject4RDBByFields("", fields, MATCH.NULL, values);
            if (userLst != null && userLst.size() > 0) {
                for (UserDetail s : userLst) {
                    if (s.getId().equals(userDetail.getId())) {
                        answerMessage.setStatus(false);
                        answerMessage.setAnswerMsg(Constant.ANSWER_INSERT_FAILURE_DATA_EXISTS);
                        return answerMessage;
                    }
                }
            }
            userDetailService.saveObject4RDB(userDetail);
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
     * 删除用户资料管理
     *
     * @param id 用户Id
     * @return 结果
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage deleteUserDetail(@QueryParam("id") Integer id) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            UserDetail userDetail = userDetailService.queryObject4RDBById(id);
            if (userDetail != null) {
                userDetailService.deleteObject4RDB(userDetail);
                answerMessage.setStatus(true);
                answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_SUCCESS);
                return answerMessage;
            }
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg(Constant.ANSWER_DATA_NOT_FOUND);
            return answerMessage;
        } catch (Exception e) {
            CommonTools.logError("删除用户资料时出错", e);
            answerMessage.setAnswerMsg(Constant.ANSWER_DELETE_FAILURE);
            answerMessage.setStatus(false);
            return answerMessage;
        }
    }

}
