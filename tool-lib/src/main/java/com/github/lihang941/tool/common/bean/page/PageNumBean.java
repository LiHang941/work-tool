package com.github.lihang941.tool.common.bean.page;

/**
 * @author : lihang1329@gmail.com
 */
public class PageNumBean {
    private int pageNum;
    private int pageSize;

    public PageNumBean(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize > PageConstants.PAGE_MAX_SIZE || pageSize <= 0 ? PageConstants.PAGE_DEFAULT_SIZE : pageSize;
    }

    public PageNumBean() {
        this(PageConstants.PAGE_DEFAULT_NUM, PageConstants.PAGE_DEFAULT_SIZE);
    }

    public int getPageNum() {
        return pageNum;
    }

    public PageNumBean setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageNumBean setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
