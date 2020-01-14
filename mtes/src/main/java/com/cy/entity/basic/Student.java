package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 学生资料表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学号
     */
    private String stuId;
    /**
     * 班级
     */
    private String className;
    /**
     * 专业
     */
    private String major;
    /**
     * 评教状态
     */
    private Integer state;
    /**
     * 类型(用于登录时判断)
     */
    private Byte type;
    /**
     * 显示顺序
     */
    private Integer showSort;


    public Student() {

    }

    public Student(Integer id, String name, String stuId, String className, String major, Integer state, Byte type, Integer showSort) {
        this.id = id;
        this.name = name;
        this.stuId = stuId;
        this.className = className;
        this.major = major;
        this.state = state;
        this.type = type;
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

    @Column(name = "stu_id", nullable = false, length = 50)
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Column(name = "class_name", nullable = false, length = 255)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(name = "major", nullable = false, length = 255)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Column(name = "state", nullable = false)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Column(name = "type", nullable = false)
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
        return "Student [id = " + id + ",name = " + name + ",stuId = " + stuId + ",className = " + className + ",major = " + major + ",state = " + state + ",type = " + type + ",showSort = " + showSort + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student that = (Student) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getName(), that.getName());
        isEqual = isEqual && Objects.equals(getStuId(), that.getStuId());
        isEqual = isEqual && Objects.equals(getClassName(), that.getClassName());
        isEqual = isEqual && Objects.equals(getMajor(), that.getMajor());
        isEqual = isEqual && Objects.equals(getState(), that.getState());
        isEqual = isEqual && Objects.equals(getType(), that.getType());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStuId(), getClassName(), getMajor(), getState(), getType(), getShowSort());
    }

}
