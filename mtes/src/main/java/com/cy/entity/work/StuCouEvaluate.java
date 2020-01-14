package com.cy.entity.work;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 学生课程评教关联关系表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "stu_cou_evaluate")
public class StuCouEvaluate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生，课程，评价内容关联表
     */
    private Integer id;
    /**
     * 课程表id
     */
    private Integer courseId;
    /**
     * 内容表id
     */
    private String content;
    /**
     *
     */
    private Integer option;
    /**
     * 排序字段
     */
    private Integer showSort;
    /**
     * 学生id
     */
    private Integer stuId;
    /**
     * 教师id
     */
    private Integer teacherId;


    public StuCouEvaluate() {

    }

    public StuCouEvaluate(Integer id, Integer courseId, String content, Integer option, Integer showSort, Integer stuId, Integer teacherId) {
        this.id = id;
        this.courseId = courseId;
        this.content = content;
        this.option = option;
        this.showSort = showSort;
        this.stuId = stuId;
        this.teacherId = teacherId;
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

    @Column(name = "course_id", nullable = false)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Column(name = "content", nullable = false, length = 50)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "option", nullable = false)
    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }

    @Column(name = "show_sort", nullable = true)
    public Integer getShowSort() {
        return showSort;
    }

    public void setShowSort(Integer showSort) {
        this.showSort = showSort;
    }

    @Column(name = "stu_id", nullable = false)
    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    @Column(name = "teacher_id", nullable = false)
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "StuCouEvaluate [id = " + id + ",courseId = " + courseId + ",content = " + content + ",option = " + option + ",showSort = " + showSort + ",stuId = " + stuId + ",teacherId = " + teacherId + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StuCouEvaluate)) {
            return false;
        }
        StuCouEvaluate that = (StuCouEvaluate) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getCourseId(), that.getCourseId());
        isEqual = isEqual && Objects.equals(getContent(), that.getContent());
        isEqual = isEqual && Objects.equals(getOption(), that.getOption());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        isEqual = isEqual && Objects.equals(getStuId(), that.getStuId());
        isEqual = isEqual && Objects.equals(getTeacherId(), that.getTeacherId());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseId(), getContent(), getOption(), getShowSort(), getStuId(), getTeacherId());
    }

}
