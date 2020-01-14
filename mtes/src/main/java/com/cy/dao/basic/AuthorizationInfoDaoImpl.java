package com.cy.dao.basic;

import com.cy.common.basics.dao.ManagerBasicsDaoImpl;
import com.cy.entity.basic.AuthorizationInfo;
import org.springframework.stereotype.Repository;

/*
 * @program: mtes
 * @description: 授权
 * @author: Cy
 * @create: 2019-03-31
 **/
@Repository
public class AuthorizationInfoDaoImpl extends ManagerBasicsDaoImpl<AuthorizationInfo, Integer> implements IAuthorizationInfoDao {
}
