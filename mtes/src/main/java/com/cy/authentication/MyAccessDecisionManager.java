package com.cy.authentication;

import com.cy.common.MemoryVariableUtils;
import com.cy.common.basics.MATCH;

import com.cy.dao.basic.IUserDetailDao;
import com.cy.entity.basic.UserDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 权限判断
 *
 * @author Cy
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    private IUserDetailDao userDetailDao;

    private static final Logger logger = LogManager.getLogger(MyAccessDecisionManager.class);

    @Autowired
    public void setUserDetailDao(IUserDetailDao userDetailDao) {
        this.userDetailDao = userDetailDao;
    }

    /**
     * decide 方法是判定是否拥有权限的决策方法，
     * authentication 是释CustomUserService中循环添加到 MyGrantedAuthority 对象中的权限信息集合，也就是用户所拥有的权限
     * object 包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
     *
     * @param authentication   是释CustomUserService中循环添加到 MyGrantedAuthority 对象中的权限信息集合，也就是用户所拥有的权限
     * @param object           包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * @param configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
     * @throws AccessDeniedException,InsufficientAuthenticationException 异常
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        System.out.println("-------MyAccessDecisionManager------");
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String loginUrlStr = "/rest/logLogin/login";

        String uri = request.getRequestURI().trim();
        if (loginUrlStr.equals(uri)) {
            return;
        }
        UserDetail user = new UserDetail();

        //首页重定向
        Cookie[] cookies = request.getCookies();
        String loginStr = "login";
        String indexHtmlStr = "index.html";
        String jsessionidStr = "JSESSIONID";
        if (cookies == null) {
            if (uri.contains(loginStr) || uri.contains(indexHtmlStr)) {
                return;
            } else {
                throw new AccessDeniedException("权限不足!");
            }
        } else if (cookies.length == 1 && jsessionidStr.equals(cookies[0].getName())) {
            throw new AccessDeniedException("权限不足!");
        }
        String account = "";
        for (Cookie cookie : cookies) {
            if ("account".equals(cookie.getName())) {
                account = cookie.getValue();
            }
        }

        List<UserDetail> userLst;
        try {
            userLst = userDetailDao.queryObject4RDBByField("", "account",
                    MATCH.NULL, account, null);
        } catch (Exception e) {
            logger.error("查询用户错误。", e);
            e.printStackTrace();
            throw new AccessDeniedException("cookie信息异常!");
        }

            user = userLst.get(0);

        System.err.println("------以上注释正式使用时需要修改-------");
        MemoryVariableUtils.setCurrentUserDetail(user);
        String url, method;
        AntPathRequestMatcher matcher;
        String roleAnonymousStr = "ROLE_ANONYMOUS";
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (ga instanceof MyGrantedAuthority) {
                MyGrantedAuthority urlGrantedAuthority = (MyGrantedAuthority) ga;
                url = urlGrantedAuthority.getPermissionUrl();
                method = urlGrantedAuthority.getMethod();
                matcher = new AntPathRequestMatcher(url);
                if (matcher.matches(request)) {
                    //当权限表权限的method为ALL时表示拥有此路径的所有请求方式权利。
                    if (method.equals(request.getMethod())) {
                        return;
                    }
                }
            } else if (ga.getAuthority().equals(roleAnonymousStr)) {
                //未登录只允许访问 login 页面
                matcher = new AntPathRequestMatcher("/login");
                if (matcher.matches(request)) {
                    return;
                }
                matcher = new AntPathRequestMatcher("/index.html");
                if (matcher.matches(request)) {
                    return;
                }
            }
        }
//        return ;
        //throw new AccessDeniedException("权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
