package com.cy.dao.basic;

import com.cy.common.basics.dao.ManagerBasicsDaoImpl;
import com.cy.entity.basic.StudentCourse;
import org.springframework.stereotype.Repository;

/*
 * @program: mtes
 * @description: 学生课程数据库交互层
 * @author: Cy
 * @create: 2019-03-28
 **/
@Repository
public class IStudentCourseDaoImpl extends ManagerBasicsDaoImpl<StudentCourse,Integer> implements IStudentCourseDao {
}
