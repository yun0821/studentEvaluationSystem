package com.cy.dao.basic;

import com.cy.common.basics.dao.ManagerBasicsDaoImpl;
import com.cy.entity.basic.Semester;
import org.springframework.stereotype.Repository;

/*
 * @program: mtes
 * @description: 学期数据库交互层
 * @author: Cy
 * @create: 2019-03-30
 **/
@Repository
public class SemesterDaoImpl extends ManagerBasicsDaoImpl<Semester, Integer> implements ISemesterDao {
}
