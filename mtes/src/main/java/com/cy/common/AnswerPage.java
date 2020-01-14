package com.cy.common;

import java.util.List;
import java.util.Objects;

/**
 * 分页查询时返回给前端的信息类
 * @author Cy
 * @date 2019/03/16
 */
public class AnswerPage<Obj> {

    private Boolean status;           //返回状态，成功是true，失败是否
    private String answerMsg;         //返回信息
    private List<Obj> records;        //返回的记录
    private AspnetPager aspnetPager;  //返回的分页信息

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAnswerMsg() {
        return answerMsg;
    }

    public void setAnswerMsg(String answerMsg) {
        this.answerMsg = answerMsg;
    }

    public List<Obj> getRecords() {
        return records;
    }

    public void setRecords(List<Obj> records) {
        this.records = records;
    }

    public AspnetPager getAspnetPager() {
        return aspnetPager;
    }

    public void setAspnetPager(AspnetPager aspnetPager) {
        this.aspnetPager = aspnetPager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerPage)) return false;
        AnswerPage<?> that = (AnswerPage<?>) o;
        return Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getAnswerMsg(), that.getAnswerMsg()) &&
                Objects.equals(getRecords(), that.getRecords()) &&
                Objects.equals(getAspnetPager(), that.getAspnetPager());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getStatus(), getAnswerMsg(), getRecords(), getAspnetPager());
    }
}
