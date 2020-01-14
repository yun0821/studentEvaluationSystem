package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 角色资料表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "role_detail")
public class RoleDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 所属学院
     */
    private String belongCollege;
    /**
     * 标记
     */
    private String remark;
    /**
     * 排序字段
     */
    private Integer showSort;


    public RoleDetail() {

    }

    public RoleDetail(Integer id, String name, String belongCollege, String remark, Integer showSort) {
        this.id = id;
        this.name = name;
        this.belongCollege = belongCollege;
        this.remark = remark;
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

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "belong_college", nullable = true, length = 255)
    public String getBelongCollege() {
        return belongCollege;
    }

    public void setBelongCollege(String belongCollege) {
        this.belongCollege = belongCollege;
    }

    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "RoleDetail [id = " + id + ",name = " + name + ",belongCollege = " + belongCollege + ",remark = " + remark + ",showSort = " + showSort + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleDetail)) {
            return false;
        }
        RoleDetail that = (RoleDetail) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getName(), that.getName());
        isEqual = isEqual && Objects.equals(getBelongCollege(), that.getBelongCollege());
        isEqual = isEqual && Objects.equals(getRemark(), that.getRemark());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBelongCollege(), getRemark(), getShowSort());
    }

}
