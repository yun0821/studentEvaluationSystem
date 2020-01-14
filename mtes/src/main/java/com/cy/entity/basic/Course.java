package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 课程表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程表
     */
    private Integer id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程编号
     */
    private Integer courseNum;
    /**
     * 学期 对应学期表中id
     */
    private Integer semester;
    /**
     * 显示顺序
     */
    private Integer showSort;


    public Course() {

    }

    public Course(Integer id, String name, Integer courseNum, Integer semester, Integer showSort) {
        this.id = id;
        this.name = name;
        this.courseNum = courseNum;
        this.semester = semester;
        this.showSort = showSort;
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

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "course_num", nullable = false)
    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    @Column(name = "semester", nullable = false)
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    @Column(name = "show_sort", nullable = true)
    public Integer getShowSort() {
        return showSort;
    }

    public void setShowSort(Integer showSort) {
        this.showSort = showSort;
    }

    @Override
    public String toString() {
        return "Course [id = " + id + ",name = " + name + ",courseNum = " + courseNum + ",semester = " + semester + ",showSort = " + showSort + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        Course that = (Course) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getName(), that.getName());
        isEqual = isEqual && Objects.equals(getCourseNum(), that.getCourseNum());
        isEqual = isEqual && Objects.equals(getSemester(), that.getSemester());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCourseNum(), getSemester(), getShowSort());
    }

}
