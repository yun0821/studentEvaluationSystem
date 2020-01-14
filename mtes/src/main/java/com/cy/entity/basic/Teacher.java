package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 教师信息表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师表
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 教师编号
     */
    private String number;
    /**
     * 所属学院
     */
    private String belongCollege;
    /**
     * 类型(用于登录时判断)
     */
    private Byte type;
    /**
     * 显示顺序
     */
    private Integer showSort;


    public Teacher() {

    }

    public Teacher(Integer id, String name, String number, String belongCollege, Byte type, Integer showSort) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.belongCollege = belongCollege;
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

    @Column(name = "number", nullable = false, length = 50)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "belong_college", nullable = false, length = 255)
    public String getBelongCollege() {
        return belongCollege;
    }

    public void setBelongCollege(String belongCollege) {
        this.belongCollege = belongCollege;
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
        return "Teacher [id = " + id + ",name = " + name + ",number = " + number + ",belongCollege = " + belongCollege + ",type = " + type + ",showSort = " + showSort + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Teacher)) {
            return false;
        }
        Teacher that = (Teacher) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getName(), that.getName());
        isEqual = isEqual && Objects.equals(getNumber(), that.getNumber());
        isEqual = isEqual && Objects.equals(getBelongCollege(), that.getBelongCollege());
        isEqual = isEqual && Objects.equals(getType(), that.getType());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getNumber(), getBelongCollege(), getType(), getShowSort());
    }

}
