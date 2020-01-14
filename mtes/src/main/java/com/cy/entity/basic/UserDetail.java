package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * 用户信息表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "user_detail")
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息表
     */
    private Integer id;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户账户
     */
    private String account;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户类型
     */
    private String type;
    /**
     * 所属学院
     */
    private String belongCollege;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 显示顺序
     */
    private Integer showSort;
    /**
     * 账号失效时间
     */
    private Date deadTime;
    /**
     * 账号是否禁用:1:正常 0:禁用
     */
    private Integer status;


    public UserDetail() {

    }

    public UserDetail(Integer id, String name, String account, String password, String type, String belongCollege, String email, String phone, Integer showSort, Date deadTime, Integer status) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.type = type;
        this.belongCollege = belongCollege;
        this.email = email;
        this.phone = phone;
        this.showSort = showSort;
        this.deadTime = deadTime;
        this.status = status;
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

    @Column(name = "account", nullable = false, length = 255)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "belong_college", nullable = false, length = 255)
    public String getBelongCollege() {
        return belongCollege;
    }

    public void setBelongCollege(String belongCollege) {
        this.belongCollege = belongCollege;
    }

    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone", nullable = true, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "show_sort", nullable = true)
    public Integer getShowSort() {
        return showSort;
    }

    public void setShowSort(Integer showSort) {
        this.showSort = showSort;
    }

    @Column(name = "dead_time", nullable = false)
    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDetail [id = " + id + ",name = " + name + ",account = " + account + ",password = " + password + ",type = " + type + ",belongCollege = " + belongCollege + ",email = " + email + ",phone = " + phone + ",showSort = " + showSort + ",deadTime = " + deadTime + ",status = " + status + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDetail)) {
            return false;
        }
        UserDetail that = (UserDetail) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getName(), that.getName());
        isEqual = isEqual && Objects.equals(getAccount(), that.getAccount());
        isEqual = isEqual && Objects.equals(getPassword(), that.getPassword());
        isEqual = isEqual && Objects.equals(getType(), that.getType());
        isEqual = isEqual && Objects.equals(getBelongCollege(), that.getBelongCollege());
        isEqual = isEqual && Objects.equals(getEmail(), that.getEmail());
        isEqual = isEqual && Objects.equals(getPhone(), that.getPhone());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        isEqual = isEqual && Objects.equals(getDeadTime(), that.getDeadTime());
        isEqual = isEqual && Objects.equals(getStatus(), that.getStatus());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAccount(), getPassword(), getType(), getBelongCollege(), getEmail(), getPhone(), getShowSort(), getDeadTime(), getStatus());
    }

}
