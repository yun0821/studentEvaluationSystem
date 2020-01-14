package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 教师课程表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "teacher_course")
public class TeacherCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师-课程关系表
     */
    private Integer id;
    /**
     * 教师Id
     */
    private Integer teacherId;
    /**
     * 课程Id
     */
    private Integer courseId;


    public TeacherCourse() {

    }

    public TeacherCourse(Integer id, Integer teacherId, Integer courseId) {
        this.id = id;
        this.teacherId = teacherId;
        this.courseId = courseId;
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

    @Column(name = "teacher_id", nullable = false)
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Column(name = "course_id", nullable = false)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "TeacherCourse [id = " + id + ",teacherId = " + teacherId + ",courseId = " + courseId + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeacherCourse)) {
            return false;
        }
        TeacherCourse that = (TeacherCourse) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getTeacherId(), that.getTeacherId());
        isEqual = isEqual && Objects.equals(getCourseId(), that.getCourseId());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTeacherId(), getCourseId());
    }

}
