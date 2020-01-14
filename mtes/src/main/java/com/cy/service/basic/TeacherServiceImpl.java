package com.cy.service.basic;

import com.cy.common.basics.service.AbstractBasicsServiceImpl;
import com.cy.dao.basic.ITeacherDao;
import com.cy.entity.basic.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
 * @program: mtes
 * @description: 教师service
 * @author: Cy
 * @create: 2019-03-26
 **/
@Service
public class TeacherServiceImpl extends AbstractBasicsServiceImpl<Teacher, Integer> implements ITeacherService {

    private ITeacherDao teacherDao;

    @Autowired
    public void setTeacherDao(ITeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }


    /**
     * 查询该学生选则学期的课程信息列表
     *
     * @param stuId    学生Id
     * @param semester 学期Id
     * @return 查询结果
     * @throws Exception 异常
     */
    @Override
    public List<Map<String, Object>> queryCourseInfoLst(Integer stuId, Integer semester) throws Exception {

        return teacherDao.queryCourseInfoLst(stuId, semester);
    }

    /**
     * 教师查询该学期的评教结果
     *
     * @param semester 学期id
     * @param teaId 教师Id
     * @return 查询结果
     * @throws Exception 异常
     */
    @Override
    public List<Map<String, Object>> queryEvaResult(Integer semester,Integer teaId) throws Exception {

        return teacherDao.queryEvaResult(semester,teaId);
    }

}