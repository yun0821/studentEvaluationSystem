package com.cy.common;

import com.cy.common.basics.MATCH;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量静态类
 *
 * @author Cy
 * @date 2019/03/16
 */
public class Constant {


    /**
     * 查询条件匹配类型
     * 存储MACTH对应的操作符
     */
    public static final Map<MATCH, String> HSQL_MATCH_OPERATOR_MAP = new HashMap<>();

    static {
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.LESS, " < ? ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.LESSEQUAL, " <= ? ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.GREATER, " > ? ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.GREATEREQUAL, " >= ? ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.IN, " IN( ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.BETWEEN, " BETWEEN ? AND ? ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.LIKE, " LIKE CONCAT('%',?,'%') ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.LEFTLIKE, " LIKE CONCAT('%',?) ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.RIGHTLIKE, " LIKE CONCAT(?,'%') ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.ORDER, " ORDER BY ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.NOTEQUALS, " != ? ");
        HSQL_MATCH_OPERATOR_MAP.put(MATCH.NULL, " = ? ");
    }

    /*************************************************HTTP返回消息**************************************/
    public static final String ANSWER_QUERY_DATA_SUCCESS = "查询成功";
    public static final String ANSWER_QUERY_DATA_FAILURE = "查询不成功";
    public static final String ANSWER_INSERT_SUCCESS = "插入数据成功";
    public static final String ANSWER_INSERT_FAILURE = "插入数据不成功";
    public static final String ANSWER_INSERT_FAILURE_DATA_EXISTS = "插入不成功,数据已经存在";
    public static final String ANSWER_UPDATE_SUCCESS = "编辑数据成功";
    public static final String ANSWER_UPDATE_FAILURE = "编辑数据不成功";
    public static final String ANSWER_UPDATE_FAILURE_DATA_EXISTS = "编辑不成功,数据已经存在";
    public static final String ANSWER_DATA_NOT_FOUND = "没有发现要处理的数据或数据已经不存在";
    public static final String ANSWER_DELETE_SUCCESS = "删除数据成功";
    public static final String ANSWER_DELETE_FAILURE = "删除数据不成功";




    /***************************查询参数MAP的KEY*************************************/
    public static final String QUERY_PARAM_MAP_FIELDS_KEY = "fields";
    public static final String QUERY_PARAM_MAP_VALUES_KEY = "values";

}
