package com.cy.dao.basic;

import com.cy.common.basics.dao.ManagerBasicsDaoImpl;
import com.cy.entity.basic.UserDetail;
import org.springframework.stereotype.Repository;

/*
 * @program: mtes
 * @description: 用户信息数据库交互层
 * @author: Cy
 * @create: 2019-03-28
 **/
@Repository
public class UserDetailDaoImpl extends ManagerBasicsDaoImpl<UserDetail, Integer> implements IUserDetailDao {
}
