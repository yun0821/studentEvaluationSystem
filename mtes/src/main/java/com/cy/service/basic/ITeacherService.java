package com.cy.service.basic;

import com.cy.common.basics.service.IBasicsService;
import com.cy.entity.basic.Teacher;

import java.util.List;
import java.util.Map;

public interface ITeacherService extends IBasicsService<Teacher, Integer> {


    /**
     * 查询该学生选则学期的课程信息列表
     *
     * @param stuId    学生Id
     * @param semester 学期Id
     * @return 查询结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> queryCourseInfoLst(Integer stuId, Integer semester) throws Exception;

    /**
     * 教师查询该学期的评教结果
     *
     * @param semester 学期id
     * @param teaId 教师Id
     * @return 查询结果
     * @throws Exception 异常
     */
    List<Map<String, Object>> queryEvaResult(Integer semester,Integer teaId) throws Exception;

}


