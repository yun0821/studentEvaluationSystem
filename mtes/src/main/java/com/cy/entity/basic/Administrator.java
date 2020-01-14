package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 管理员资料表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "administrator")
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员表
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 类型(用于登录时判断)
     */
    private Byte type;
    /**
     * 显示顺序
     */
    private Integer showSort;
    /**
     * 标记
     */
    private String remark;


    public Administrator() {

    }

    public Administrator(Integer id, String name, String account, String password, Byte type, Integer showSort, String remark) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.type = type;
        this.showSort = showSort;
        this.remark = remark;
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

    @Column(name = "account", nullable = false, length = 50)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Administrator [id = " + id + ",name = " + name + ",account = " + account + ",password = " + password + ",type = " + type + ",showSort = " + showSort + ",remark = " + remark + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Administrator)) {
            return false;
        }
        Administrator that = (Administrator) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getName(), that.getName());
        isEqual = isEqual && Objects.equals(getAccount(), that.getAccount());
        isEqual = isEqual && Objects.equals(getPassword(), that.getPassword());
        isEqual = isEqual && Objects.equals(getType(), that.getType());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        isEqual = isEqual && Objects.equals(getRemark(), that.getRemark());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAccount(), getPassword(), getType(), getShowSort(), getRemark());
    }

}
