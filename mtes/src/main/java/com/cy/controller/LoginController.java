package com.cy.controller;

import com.cy.common.basics.SearchCondition;
import com.cy.service.basic.IUserDetailService;
import com.cy.common.AnswerMessage;
import com.cy.common.MemoryVariableUtils;
import com.cy.common.basics.MATCH;
import com.cy.entity.basic.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/*
 * @program: mtes
 * @description: 登陆
 * @author: Cy
 * @create: 2019-03-28
 **/
@Path("/logLogin")
@Component
public class LoginController {

    private IUserDetailService userDetailService;

    @Autowired
    public void setUserDetailService(IUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    /**
     * 用户登陆
     *
     * @return 登录结果
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerMessage login(@FormParam("account") String account,
                               @FormParam("password") String password,
                               @Context HttpServletRequest request) {
        AnswerMessage answerMessage = new AnswerMessage();
        try {
            List<SearchCondition> scLst = new ArrayList<>();
            scLst.add(new SearchCondition("account", MATCH.NULL, account));
            List<UserDetail> userLst = userDetailService.queryObject4RDBByConds("", scLst);
            UserDetail user = userLst.get(0);
            MemoryVariableUtils.setCurrentUserDetail(user);

            if (user.getPassword().equals(password)) {  System.out.println(MemoryVariableUtils.getCurrentUserDetail());
                answerMessage.setStatus(true);
                answerMessage.setRecords(user);
                answerMessage.setAnswerMsg("登录成功");
                return answerMessage;
            } else {
                answerMessage.setStatus(false);
                answerMessage.setAnswerMsg("密码不正确");
                return answerMessage;
            }
        } catch (Exception e) {
            answerMessage.setStatus(false);
            answerMessage.setAnswerMsg("登录失败");
            return answerMessage;
        }
    }
}
