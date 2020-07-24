package com.github.lihang941.tool.common.bean.page;

/**
 * 偏移量分页
 *
 * @author : lihang1329@gmail.com
 */
public class PageOffsetBean {
    private int offSet;
    private int size;

    public PageOffsetBean() {
        this(PageConstants.PAGE_DEFAULT_OFFSET, PageConstants.PAGE_DEFAULT_SIZE);
    }

    public PageOffsetBean(int offSet, int size) {
        this.offSet = offSet;
        this.size = size > PageConstants.PAGE_MAX_SIZE || size <= 0 ? PageConstants.PAGE_DEFAULT_SIZE : size;
    }

    public int getOffSet() {
        return offSet;
    }

    public PageOffsetBean setOffSet(int offSet) {
        this.offSet = offSet;
        return this;
    }

    public int getSize() {
        return size;
    }

    public PageOffsetBean setSize(int size) {
        this.size = size;
        return this;
    }
}
