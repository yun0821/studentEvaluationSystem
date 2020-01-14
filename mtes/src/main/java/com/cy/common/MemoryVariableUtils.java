package com.cy.common;

import com.cy.entity.basic.UserDetail;

/*
 * @program: mtes
 * @description: 获取当前用户信息类
 * @author: Cy
 * @create: 2019-03-26
 **/
public class MemoryVariableUtils {

    /**
     * 当前用户信息
     */
    private static ThreadLocal<UserDetail> currentUserDetail = new ThreadLocal<>();

    /**
     * 删除当前线程的用户
     *
     * @author Cy
     */
    public static void removeCurrentUserDetail() {
        currentUserDetail.remove();
    }

    /**
     * 获取当前线程的用户
     *
     * @return UserDetail 返回当前用户信息
     * @author Cy
     */
    public static UserDetail getCurrentUserDetail() {
        return currentUserDetail.get();
    }

    /**
     * 设置当前线程的用户
     *
     * @author Cy
     */
    public static void setCurrentUserDetail(UserDetail userDetail) {
        currentUserDetail.set(userDetail);
    }
}
