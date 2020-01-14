package com.cy.dao.basic;

import com.cy.common.basics.dao.ManagerBasicsDaoImpl;
import com.cy.entity.basic.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @program: mtes
 * @description: 教师类数据库交互层
 * @author: Cy
 * @create: 2019-03-26
 **/
@Repository
public class TeacherDaoImpl extends ManagerBasicsDaoImpl<Teacher, Integer> implements ITeacherDao {

    /**
     * 查询该学生选则学期的课程信息列表
     *
     * @param stuId    学生Id
     * @param semester 学期Id
     * @return 查询结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> queryCourseInfoLst(Integer stuId, Integer semester) throws Exception {

        StringBuilder sql = new StringBuilder();
        List<Object> paramLst = new ArrayList<>();
        sql.append(" SELECT c.id AS courseId, c.course_num AS courseNum, c.name AS courseName,t.id AS teacherId ");
        sql.append(" , t.name AS teacherName,  c.type AS courseType,sc.is_evaluate AS isEvaluate, ");
        sql.append("   AVG(e.score ) AS avgScore ");
        sql.append(" FROM teacher AS t ");
        sql.append(" INNER JOIN teacher_course AS tc ON(tc.teacher_id = t.id) ");
        sql.append(" INNER JOIN course AS c ON(tc.course_id = c.id) ");
        sql.append(" INNER JOIN student_course AS sc  ON(c.id = sc.course_id ) ");
        sql.append(" INNER JOIN student AS s ON(sc.student_id = s.id) ");
        sql.append(" INNER JOIN semester AS sem ON(c.semester = sem.id) ");
        sql.append(" INNER JOIN evaluate AS e ON(c.id=e.course_id) ");
        sql.append(" WHERE s.id = ? ");
        paramLst.add(stuId);
        sql.append(" AND sem.id = ? ");
        paramLst.add(semester);
        sql.append(" GROUP BY c.id ");
        sql.append(" ORDER BY c.show_sort ");
        Object[] args = new Object[paramLst.size()];
        for (int i = 0; i < paramLst.size(); i++) {
            args[i] = paramLst.get(i);
        }
        return super.queryMap4RDBSQL(sql.toString(), args, null);
    }


    /**
     * 教师查询该学期的评教结果
     *
     * @param semester 学期id
     * @param teaId 教师Id
     * @return 查询结果
     * @throws Exception 异常
     */
    public List<Map<String, Object>> queryEvaResult(Integer semester,Integer teaId) throws Exception {

        StringBuilder sql = new StringBuilder();
        List<Object> paramLst = new ArrayList<>();
        sql.append(" SELECT cou.name AS couName, cou.course_num AS couNum,sem.name AS semester, AVG(eva.score ) AS score ");
        sql.append(" FROM evaluate AS eva ");
        sql.append(" INNER JOIN teacher AS tea ON(eva.teacher_id = tea.id) ");
        sql.append(" INNER JOIN course AS cou ON(eva.course_id = cou.id) ");
        sql.append(" INNER JOIN semester AS sem ON(cou.semester = sem.id) ");
        sql.append(" WHERE tea.id = ? ");
        paramLst.add(teaId);
        sql.append(" AND sem.id = ? ");
        paramLst.add(semester);
        sql.append(" GROUP BY cou.id ");
        sql.append(" ORDER BY cou.show_sort ");
        Object[] args = new Object[paramLst.size()];
        for (int i = 0; i < paramLst.size(); i++) {
            args[i] = paramLst.get(i);
        }
        return super.queryMap4RDBSQL(sql.toString(), args, null);

    }
}