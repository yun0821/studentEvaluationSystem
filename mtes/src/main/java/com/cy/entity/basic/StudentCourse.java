package com.cy.entity.basic;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 学生课程表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "student_course")
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生 -课程关系表
     */
    private Integer id;
    /**
     * 学生Id
     */
    private Integer studentId;
    /**
     * 课程Id
     */
    private Integer courseId;
    /**
     * 评价状态
     */
    private Integer isEvaluate;


    public StudentCourse() {

    }

    public StudentCourse(Integer id, Integer studentId, Integer courseId, Integer isEvaluate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.isEvaluate = isEvaluate;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "student_id", nullable = false)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Column(name = "course_id", nullable = false)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Column(name = "is_evaluate", nullable = false)
    public Integer getIsEvaluate() {
        return isEvaluate;
    }

    public void setIsEvaluate(Integer isEvaluate) {
        this.isEvaluate = isEvaluate;
    }

    @Override
    public String toString() {
        return "StudentCourse [id = " + id + ",studentId = " + studentId + ",courseId = " + courseId + ",isEvaluate = " + isEvaluate + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentCourse)) {
            return false;
        }
        StudentCourse that = (StudentCourse) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getStudentId(), that.getStudentId());
        isEqual = isEqual && Objects.equals(getCourseId(), that.getCourseId());
        isEqual = isEqual && Objects.equals(getIsEvaluate(), that.getIsEvaluate());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudentId(), getCourseId(), getIsEvaluate());
    }

}
