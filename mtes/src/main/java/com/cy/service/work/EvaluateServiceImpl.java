package com.cy.service.work;

import com.cy.common.basics.SearchCondition;
import com.cy.common.basics.service.AbstractBasicsServiceImpl;
import com.cy.common.basics.MATCH;
import com.cy.dao.basic.IStudentCourseDao;
import com.cy.dao.work.IEvaluateDao;
import com.cy.entity.basic.StudentCourse;
import com.cy.entity.work.Evaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @program: mtes
 * @description: 评教业务层
 * @author: Cy
 * @create: 2019-03-28
 **/
@Service
public class EvaluateServiceImpl extends AbstractBasicsServiceImpl<Evaluate, Integer> implements IEvaluateService {

    private IEvaluateDao evaluateDao;

    private IStudentCourseDao studentCourseDao;

    @Autowired
    public void setEvaluateDao(IEvaluateDao evaluateDao) {
        this.evaluateDao = evaluateDao;
    }

    @Autowired
    public void setStudentCourseDao(IStudentCourseDao studentCourseDao) {
        this.studentCourseDao = studentCourseDao;
    }


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
    public void saveEvaluateLst(List<Integer> contentIds, List<Integer> levels,
                                Integer teacherId, Integer courseId, Integer studentId) throws Exception {
        Map<Integer, Integer> scoreMp = new HashMap<>();
        scoreMp.put(1, 100);
        scoreMp.put(2, 90);
        scoreMp.put(3, 80);
        scoreMp.put(4, 70);
        scoreMp.put(5, 60);
        List<SearchCondition> scLst = new ArrayList<>();
        scLst.add(new SearchCondition("courseId", MATCH.NULL, courseId));
        scLst.add(new SearchCondition("teacherId", MATCH.NULL, teacherId));
        List<Evaluate> evaLst = evaluateDao.queryObject4RDBByConds("", scLst, null);
        List<SearchCondition> scLst1 = new ArrayList<>();
        scLst1.add(new SearchCondition("studentId", MATCH.NULL, studentId));
        scLst1.add(new SearchCondition("courseId", MATCH.NULL, courseId));
        List<StudentCourse> studentCourseList = studentCourseDao.queryObject4RDBByConds("", scLst1, null);
        StudentCourse sc = studentCourseList.get(0);
        if (evaLst != null && evaLst.size() > 1) {
            for (int i = 0; i < contentIds.size(); i++) {
                Evaluate evaluate = evaLst.get(i);
                evaluate.setScore(scoreMp.get(levels.get(i)).toString());
                evaluateDao.updateObject4RDB(evaluate);
                sc.setIsEvaluate(1);
            }
            studentCourseDao.updateObject4RDB(sc);
        } else {
            for (int i = 0; i < contentIds.size(); i++) {
                Evaluate evaluate = new Evaluate();
                evaluate.setContentId(contentIds.get(i));
                evaluate.setCourseId(courseId);
                evaluate.setTeacherId(teacherId);
                evaluate.setScore(scoreMp.get(levels.get(i)).toString());
                evaluateDao.saveObject4RDB(evaluate);
                sc.setIsEvaluate(1);
            }
            studentCourseDao.updateObject4RDB(sc);
        }
    }
}
