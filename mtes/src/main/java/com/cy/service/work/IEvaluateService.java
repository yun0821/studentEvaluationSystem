package com.cy.service.work;

import com.cy.common.basics.service.IBasicsService;
import com.cy.entity.work.Evaluate;

import java.util.List;

public interface IEvaluateService extends IBasicsService<Evaluate, Integer> {


    /**
     * 批量保存评教结果
     *
     * @param contentIds 内容列表
     * @param levels     结果列表
     * @param teacherId  任课教师Id
     * @param courseId   课程Id
     * @param studentId  学生Id
     * @throws Exception 异常
     */
    void saveEvaluateLst(List<Integer> contentIds, List<Integer> levels, Integer teacherId,
                         Integer courseId, Integer studentId) throws Exception;
}
