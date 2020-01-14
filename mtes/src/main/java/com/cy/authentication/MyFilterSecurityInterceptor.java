package com.cy.authentication;

import com.cy.common.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 权限拦截器
 *
 * @author Cy
 */
@Service
public class MyFilterSecurityInterceptor extends FilterSecurityInterceptor {

    private FilterInvocationSecurityMetadataSource securityMetadataSource;


    @Autowired
    @Override
    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        // *********************************************对不需要进行权限认证的资源进行放行 start*******************************************************/
        String uri = ((HttpServletRequest) request).getRequestURI().trim();
        String passFilePath = PropertiesUtil.readProperty("/application.Properties", "authentication.passfilepath");
        String[] passFilePathArr = passFilePath.split(",");
        for (String passPath : passFilePathArr) {
            if (!passPath.isEmpty() && uri.contains(passPath)) {
                chain.doFilter(request, response);
                return;
            }
        }
        String passFileType = PropertiesUtil.readProperty("/application.Properties", "authentication.passfiletype");
        String[] passFileTypeArr = passFileType.split(",");
        for (String passType : passFileTypeArr) {
            if (!passType.isEmpty() && uri.endsWith(passType)) {
                chain.doFilter(request, response);
                return;
            }
        }

        //过滤url
//        String passFileUrl = PropertiesUtil.readProperty("/application.Properties", "authentication.passfileurl");
//        String[] passFileUrlArr = passFileUrl.split(",");
//        for (String passUrl : passFileUrlArr) {
//            if (!passUrl.isEmpty() && uri.endsWith(passUrl)) {
//                chain.doFilter(request, response);
//                return;
//            }
//        }
        // *********************************************对不需要进行权限认证的资源进行放行 end*******************************************************/
        super.invoke(fi);
    }

//    public void invoke(FilterInvocation fi) throws IOException, ServletException {
//
//        //fi里面有一个被拦截的url,里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
//        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
//        InterceptorStatusToken token = super.beforeInvocation(fi);
//        try {
//
//            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());//执行下一个拦截器
//        } finally {
//            super.finallyInvocation(token);
////            super.afterInvocation(token, null);
//        }
//        super.afterInvocation(token, (Object)null);
//    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}
