package com.cy.entity.work;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * 评价内容表
 *
 * @author Cy
 * @date 2019-03-22
 */
@Entity
@Table(name = "content")
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评教内容表
     */
    private Integer id;
    /**
     * 评教内容
     */
    private String name;
    /**
     * 该项权重
     */
    private Double weight;
    /**
     * 显示顺序
     */
    private Integer showSort;


    public Content() {

    }

    public Content(Integer id, String name, Double weight, Integer showSort) {
        this.id = id;
        this.name = name;
        this.weight = weight;
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

    @Column(name = "weight", nullable = false)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
        return "Content [id = " + id + ",name = " + name + ",weight = " + weight + ",showSort = " + showSort + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Content)) {
            return false;
        }
        Content that = (Content) o;
        boolean isEqual = true;
        isEqual = Objects.equals(getId(), that.getId());
        isEqual = isEqual && Objects.equals(getName(), that.getName());
        isEqual = isEqual && Objects.equals(getWeight(), that.getWeight());
        isEqual = isEqual && Objects.equals(getShowSort(), that.getShowSort());
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getWeight(), getShowSort());
    }

}
