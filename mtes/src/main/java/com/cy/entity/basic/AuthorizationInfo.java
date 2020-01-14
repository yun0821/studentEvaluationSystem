package com.cy.entity.basic;

import java.util.Objects;

/*
 * @program: mtes
 * @description: 授权
 * @author: Cy
 * @create: 2019-03-31
 **/
public class AuthorizationInfo {


    private Integer id;              //主键,资源ID
    private Integer userId;          //用户ID
    private String  userAccount;     //用户账号
    private String  userName;        //用户姓名
    private Integer roleId;          //角色ID
    //    private String  roleName;      //角色名称
    private Integer moduleId;        //模块ID
    private Integer moduleHandleId;  //模块功能ID
    private String resourceName;     //资源名称
    private String url;              // 资源访问URL
    private String method;           // 访问方式，GET、POST、PUT、DELETE


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getModuleHandleId() {
        return moduleHandleId;
    }

    public void setModuleHandleId(Integer moduleHandleId) {
        this.moduleHandleId = moduleHandleId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "AuthorizationInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", userAccount='" + userAccount + '\'' +
                ", userName='" + userName + '\'' +
                ", roleId=" + roleId +
                ", moduleId=" + moduleId +
                ", moduleHandleId=" + moduleHandleId +
                ", resourceName='" + resourceName + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorizationInfo)) return false;
        AuthorizationInfo that = (AuthorizationInfo) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getUserAccount(), that.getUserAccount()) &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getRoleId(), that.getRoleId()) &&
                Objects.equals(getModuleId(), that.getModuleId()) &&
                Objects.equals(getModuleHandleId(), that.getModuleHandleId()) &&
                Objects.equals(getResourceName(), that.getResourceName()) &&
                Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getMethod(), that.getMethod());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUserId(), getUserAccount(), getUserName(), getRoleId(), getModuleId(), getModuleHandleId(), getResourceName(), getUrl(), getMethod());
    }
}

