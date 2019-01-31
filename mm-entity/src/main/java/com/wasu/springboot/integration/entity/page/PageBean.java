package com.wasu.springboot.integration.entity.page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int currentPage;
    private int numPerPage;

    private int totalCount;
    private List<? extends Object> recordList;

    private int pageCount;
    private int beginPageIndex;
    private int endPageIndex;

    private Map<String, Object> countResultMap;

    public PageBean() {
    }

    public PageBean(int currentPage, int numPerPage, int totalCount, List<? extends Object> recordList) {
        this.currentPage = currentPage;
        this.numPerPage = numPerPage;
        this.totalCount = totalCount;
        this.recordList = recordList;

        pageCount = (totalCount + numPerPage - 1) / numPerPage;

        if (pageCount <= 11) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        } else {
            beginPageIndex = currentPage - 5;
            endPageIndex = currentPage + 5;
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 11;
            }
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 11 + 1;
            }
        }
    }

    public PageBean(int currentPage, int numPerPage, int totalCount, List<? extends Object> recordList, Map<String, Object> countResultMap) {
        this.currentPage = currentPage;
        this.numPerPage = numPerPage;
        this.totalCount = totalCount;
        this.recordList = recordList;
        this.countResultMap = countResultMap;

        pageCount = (totalCount + numPerPage - 1) / numPerPage;

        if (pageCount <= 11) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        } else {
            beginPageIndex = currentPage - 5;
            endPageIndex = currentPage + 5;
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 11;
            }
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 11 + 1;
            }
        }
    }

    /**
     * 是否还有下一页
     *
     * @return
     */
    public boolean isHasNext() {
        return (currentPage + 1 <= getPageCount());
    }

    /**
     * 是否还有上一页
     *
     * @return
     */
    public boolean isHasPre() {
        return (currentPage - 1 >= 1);
    }

    public int getNextPage() {
        if (isHasNext()) {
            return currentPage + 1;
        } else {
            return currentPage;
        }
    }

    public int getPrePage() {
        if (isHasPre()) {
            return currentPage - 1;
        } else {
            return currentPage;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<? extends Object> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<? extends Object> recordList) {
        this.recordList = recordList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public Map<String, Object> getCountResultMap() {
        return countResultMap;
    }

    public void setCountResultMap(Map<String, Object> countResultMap) {
        this.countResultMap = countResultMap;
    }
}
