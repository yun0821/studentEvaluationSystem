package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 用户角色表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色表
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 角色Id
     */
    private Integer roleId;
    /**
     * 排序字段
     */
    private Integer showSort;


    public UserRole() {

    }

    public UserRole(Integer id, Integer userId, Integer roleId, Integer showSort) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
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

    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "role_id", nullable = false)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
        return "UserRole [id = " + id + ",userId = " + userId + ",roleId = " + roleId + ",showSort = " + showSort + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRole)) {
            return false;
        }
        UserRole that = (UserRole) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getUserId(), that.getUserId());
        isEqual = isEqual && Objects.equals(getRoleId(), that.getRoleId());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getRoleId(), getShowSort());
    }

}
