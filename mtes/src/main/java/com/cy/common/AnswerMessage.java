package com.cy.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 操作时返回给前端的信息类
 *
 * @author Cy
 * @date 2019/03/16
 */
@Scope("prototype")
@Component
public class AnswerMessage {

    /**
     * 返回状态，成功true，失败false
     */
    private Boolean status;
    /**
     * 返回的功能标题，主要用于分析页面
     */
    private String title;
    /**
     * 返回消息
     */
    private String answerMsg;
    /**
     * 返回数据
     */
    private Object records;


    /**
     * 获取消息对象
     *
     * @param title     来源
     * @param status    成功true, 失败false
     * @param answerMsg 提示语句
     * @param records   成功的集合数据
     * @return 消息对象
     * @author Cy
     * @date 2019/03/16
     */
    public static AnswerMessage getAnswerMessage(String title, Boolean status, String answerMsg, Object records) {
        if (status) {
            return getSuccAnswerMessage(title, answerMsg, records);
        }
        return getFailAnswerMessage(title, answerMsg);
    }

    /**
     * 失败消息对象
     *
     * @param title
     * @param answerMsg
     * @return AnswerMessage
     * @author Cy
     * @date 2019/03/16
     */
    public static AnswerMessage getFailAnswerMessage(String title, String answerMsg) {
        return new AnswerMessage(title, answerMsg, false, null);
    }

    public static AnswerMessage getFailAnswerMessage(String answerMsg) {
        return getFailAnswerMessage("", answerMsg);
    }


    /**
     * 成功消息对象
     *
     * @param title
     * @param answerMsg
     * @param records
     * @return
     * @author Cy
     * @date 2019/03/16
     */
    public static AnswerMessage getSuccAnswerMessage(String title, String answerMsg, Object records) {
        return new AnswerMessage(title, answerMsg, true, records);
    }

    public static AnswerMessage getSuccAnswerMessage(String answerMsg, Object records) {
        return getSuccAnswerMessage("", answerMsg, records);
    }

    public static AnswerMessage getSuccAnswerMessage(String answerMsg) {
        return getSuccAnswerMessage(answerMsg, null);
    }


    public AnswerMessage() {
    }

    /**
     * 有参的构造函数
     *
     * @param titile
     * @param answerMsg
     * @param status
     * @param records
     * @author Cy
     * @date 2019/03/16
     */
    AnswerMessage(String titile, String answerMsg, Boolean status, Object records) {
        this.title = titile;
        this.answerMsg = answerMsg;
        this.status = status;
        this.records = records;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswerMsg() {
        return answerMsg;
    }

    public void setAnswerMsg(String answerMsg) {
        this.answerMsg = answerMsg;
    }

    public Object getRecords() {
        return records;
    }

    public void setRecords(Object records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "AnswerMessage{" +
                "status=" + status +
                ", answerMsg='" + answerMsg + '\'' +
                ", records=" + records +
                '}';
    }
}
