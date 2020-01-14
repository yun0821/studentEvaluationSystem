package com.cy.authentication;

import com.cy.common.basics.MATCH;
import com.cy.dao.basic.IAuthorizationInfoDao;
import com.cy.dao.basic.IUserDetailDao;
import com.cy.entity.basic.AuthorizationInfo;
import com.cy.entity.basic.UserDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(CustomUserServiceImpl.class);

    private IUserDetailDao userDetailDao;

    private IAuthorizationInfoDao authorizationInfoDao;



    @Autowired
    public void setUserDetailDao(IUserDetailDao userDetailDao) {
        this.userDetailDao = userDetailDao;
    }

    @Autowired
    public void setAuthorizationInfoDao(IAuthorizationInfoDao authorizationInfoDao) {
        this.authorizationInfoDao = authorizationInfoDao;
    }


    CustomUserServiceImpl() {

    }

    @SuppressWarnings("unchecked")
    @Override
    public UserDetails loadUserByUsername(String account) {
        try {
            List<UserDetail> userLst = userDetailDao.queryObject4RDBByField("", "account",
                    MATCH.NULL, account, null);
            if (userLst == null || userLst.size() < 1) {
                throw new UsernameNotFoundException("账号或密码不正确");
            }
            UserDetail user = userLst.get(0);
            List<AuthorizationInfo> authorizationInfoLst;
            String key = "authorizationInfo_" + user.getId();
            Object userObj = null;
            boolean isConn = true;
            return new User(user.getAccount(), user.getPassword(), null);
        } catch (Exception e) {
            logger.error("重写loadUserByUsername 方法获得 userdetails 类型用户失败。", e);
            e.printStackTrace();
            throw new UsernameNotFoundException("account: " + account + " do not exist!");
        }
    }
}
