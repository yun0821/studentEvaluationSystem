package com.cy.authentication;

import com.cy.dao.basic.IAuthorizationInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 所有权限
 *
 * @author Cy
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    private IAuthorizationInfoDao authorizationInfoDao;

    @Autowired
    public void setAuthorizationInfoDao(IAuthorizationInfoDao authorizationInfoDao) {
        this.authorizationInfoDao = authorizationInfoDao;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> co = new ArrayList<>();
        co.add(new SecurityConfig("null"));
        return co;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
