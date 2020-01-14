package com.cy.common;

import java.util.Objects;

/**
 * 自定义分页控件类
 * @author Cy
 * @date 2019/03/16
 */
public class AspnetPager {

    /**
     * 默认的记录数
     */
    public final static int DEFAULT_PAGE_SIZE = 20;
    /*
     * 当前页码
     */
    private int currentPage = 1;

    /*
     * 当前页开始下标
     */
    private int startIndex;
    /*
     * 当前页结束下标
     */
    private int endIndex;

    /*
     * 每页记录数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;
    /*
     * 记录总数
     */
    private int totalCount;

    /*
     * 总页码
     */
    private int totalPage;

    public AspnetPager() {
        if(currentPage<=0){
            this.currentPage=0;
            this.setStartIndex(0);
            this.setEndIndex(0);
        }else{
            this.setStartIndex((this.getCurrentPage() - 1) * this.getPageSize());
            this.setEndIndex(this.getCurrentPage() * this.getPageSize());
        }
    }

    public AspnetPager(int currentPage, int pageSize) {
        if(currentPage<0){
            currentPage=0;
        }
        if(pageSize<0){
            pageSize=DEFAULT_PAGE_SIZE;
        }
        this.setCurrentPage(currentPage);
        this.setPageSize(pageSize);
        if(currentPage<=0){
            this.setStartIndex(0);
            this.setEndIndex(0);
        }else{
            this.setStartIndex((this.getCurrentPage() - 1) * this.getPageSize());
            this.setEndIndex(this.getCurrentPage() * this.getPageSize());
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AspnetPager)) return false;
        AspnetPager that = (AspnetPager) o;
        return getCurrentPage() == that.getCurrentPage() &&
                getStartIndex() == that.getStartIndex() &&
                getEndIndex() == that.getEndIndex() &&
                getPageSize() == that.getPageSize() &&
                getTotalCount() == that.getTotalCount() &&
                getTotalPage() == that.getTotalPage();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCurrentPage(), getStartIndex(), getEndIndex(), getPageSize(), getTotalCount(), getTotalPage());
    }
}
