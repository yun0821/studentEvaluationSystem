package com.cy.entity.basic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 数据字典表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "sys_dictionarie")
public class SysDictionarie implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典表
     */
    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * 身份标识
     */
    private String identity;
    /**
     * 值
     */
    private String identityValue;
    /**
     * 排序字段
     */
    private Integer showSort;


    public SysDictionarie() {

    }

    public SysDictionarie(Integer id, String code, String identity, String identityValue, Integer showSort) {
        this.id = id;
        this.code = code;
        this.identity = identity;
        this.identityValue = identityValue;
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

    @Column(name = "code", nullable = false, length = 50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "identity", nullable = false, length = 50)
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Column(name = "identity_value", nullable = false, length = 50)
    public String getIdentityValue() {
        return identityValue;
    }

    public void setIdentityValue(String identityValue) {
        this.identityValue = identityValue;
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
        return "SysDictionarie [id = " + id + ",code = " + code + ",identity = " + identity + ",identityValue = " + identityValue + ",showSort = " + showSort + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysDictionarie)) {
            return false;
        }
        SysDictionarie that = (SysDictionarie) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getCode(), that.getCode());
        isEqual = isEqual && Objects.equals(getIdentity(), that.getIdentity());
        isEqual = isEqual && Objects.equals(getIdentityValue(), that.getIdentityValue());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getIdentity(), getIdentityValue(), getShowSort());
    }

}
