package com.cy.common.basics;

/**
 * 查询条件的匹配方式，其中LESS(<),LESSEQUAL(<=),GREATER(>),GREATEREQUAL(>=)
 * @author Cy
 * @date 2019/03/01
 */
public enum MATCH {
    LESS, LESSEQUAL, GREATER, GREATEREQUAL, IN, BETWEEN, LIKE, LEFTLIKE, RIGHTLIKE, ORDER, NOTEQUALS, NULL
}
