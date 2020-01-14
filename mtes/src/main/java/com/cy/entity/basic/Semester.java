package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * 学期资料表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "semester")
public class Semester implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学期表
     */
    private Integer id;
    /**
     * 学期名称
     */
    private String name;
    /**
     * 学期开始时间
     */
    private Date beginTime;
    /**
     * 学期结束时间
     */
    private Date endTime;



    public Semester() {

    }

    public Semester(Integer id, String name, Date beginTime, Date endTime) {
        this.id = id;
        this.name = name;
        this.beginTime = beginTime;
        this.endTime = endTime;
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

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "begin_time", nullable = false)
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Column(name = "end_time", nullable = false)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Semester [id = " + id + ",name = " + name + ",beginTime = " + beginTime + ",entTime = " + endTime + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Semester)) {
            return false;
        }
        Semester that = (Semester) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getName(), that.getName());
        isEqual = isEqual && Objects.equals(getBeginTime(), that.getBeginTime());
        isEqual = isEqual && Objects.equals(getEndTime(), that.getEndTime());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBeginTime(), getEndTime());
    }

}
