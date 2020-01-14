package com.cy.entity.work;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * 登录日志表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "sys_login")
public class SysLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录日志
     */
    private Integer id;
    /**
     * 登录账号
     */
    private String account;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 登录人员姓名
     */
    private String userName;
    /**
     * ip地址
     */
    private String ipAdress;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 登录类型
     */
    private String type;


    public SysLogin() {

    }

    public SysLogin(Integer id, String account, Date loginTime, String userName, String ipAdress, String browser, String type) {
        this.id = id;
        this.account = account;
        this.loginTime = loginTime;
        this.userName = userName;
        this.ipAdress = ipAdress;
        this.browser = browser;
        this.type = type;
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

    @Column(name = "account", nullable = false, length = 255)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(name = "login_time", nullable = false)
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "user_name", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "ip_adress", nullable = false, length = 255)
    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    @Column(name = "browser", nullable = false, length = 255)
    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SysLogin [id = " + id + ",account = " + account + ",loginTime = " + loginTime + ",userName = " + userName + ",ipAdress = " + ipAdress + ",browser = " + browser + ",type = " + type + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysLogin)) {
            return false;
        }
        SysLogin that = (SysLogin) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getAccount(), that.getAccount());
        isEqual = isEqual && Objects.equals(getLoginTime(), that.getLoginTime());
        isEqual = isEqual && Objects.equals(getUserName(), that.getUserName());
        isEqual = isEqual && Objects.equals(getIpAdress(), that.getIpAdress());
        isEqual = isEqual && Objects.equals(getBrowser(), that.getBrowser());
        isEqual = isEqual && Objects.equals(getType(), that.getType());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccount(), getLoginTime(), getUserName(), getIpAdress(), getBrowser(), getType());
    }

}
