package com.wasu.springboot.integration.entity.page;

import java.io.Serializable;

public class PageParam implements Serializable {

    private static final long serialVersionUID=1L;
    private int pageNum;
    private int numPerPage;

    public PageParam(int pageNum, int numPerPage) {
        super();
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }
}
