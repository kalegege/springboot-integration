package com.wasu.springboot.integration.entity.report;

import com.wasu.springboot.integration.base.BaseEntity;

import java.util.Date;
import java.util.List;

public class OutSideReportEntity extends BaseEntity{
    /**
     * 报告类型
     */
    private String reportType;

    /**
     * 报告类型名称
     */
    private String reportTypeName;

    /**
     * 证券编码
     */
    private String reportCode;

    /**
     * 证券名称
     */
    private String reportName;

    /**
     * 评级
     */
    private String grade;

    /**
     * 评级ID
     */
    private Long wbReportGradeId;

    /**
     * 报告标题
     */
    private String reportTitle;

    /**
     * 报告摘要页
     */
    private String reportDesc;

    /**
     * 报告关键字
     */
    private String reportKeyword;

    /**
     * 关联文件ID
     */
    private Long relaReportId;

    /**
     * 上传人ID
     */
    private Long importUser;

    /**
     * 上传人用户名
     */
    private String userName;

    /**
     * 机构
     */
    private String mechanism;

    /**
     * 作者
     */
    private String author;

    /**
     * 行业编码
     */
    private String industry;

    /**
     * 行业名称
     */
    private String indeustryName;

    /**
     * 是否正序排列 true-正序 false-倒序
     */
    private boolean isPositive;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 排序字段
     */
    private String order;

    /**
     * 报告本地文件名
     */
    private String realReportName;

    /**
     * 报告下载URL
     */
    private String filePath;

    /**
     * 报告来源 1系统上传 2邮箱获取 3数据库
     */
    private Integer reportSource;

    /**
     * 报告链接
     */
    private String reportUrl;

    /**
     * 原始行业
     */
    private String originalIndustry;

    /**
     * 外部报告ID合集字符串，中间用逗号分隔
     */
    private List<Long> outsideReportIdList;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getWbReportGradeId() {
        return wbReportGradeId;
    }

    public void setWbReportGradeId(Long wbReportGradeId) {
        this.wbReportGradeId = wbReportGradeId;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getReportKeyword() {
        return reportKeyword;
    }

    public void setReportKeyword(String reportKeyword) {
        this.reportKeyword = reportKeyword;
    }

    public Long getRelaReportId() {
        return relaReportId;
    }

    public void setRelaReportId(Long relaReportId) {
        this.relaReportId = relaReportId;
    }

    public Long getImportUser() {
        return importUser;
    }

    public void setImportUser(Long importUser) {
        this.importUser = importUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndeustryName() {
        return indeustryName;
    }

    public void setIndeustryName(String indeustryName) {
        this.indeustryName = indeustryName;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getRealReportName() {
        return realReportName;
    }

    public void setRealReportName(String realReportName) {
        this.realReportName = realReportName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getReportSource() {
        return reportSource;
    }

    public void setReportSource(Integer reportSource) {
        this.reportSource = reportSource;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getOriginalIndustry() {
        return originalIndustry;
    }

    public void setOriginalIndustry(String originalIndustry) {
        this.originalIndustry = originalIndustry;
    }

    public List<Long> getOutsideReportIdList() {
        return outsideReportIdList;
    }

    public void setOutsideReportIdList(List<Long> outsideReportIdList) {
        this.outsideReportIdList = outsideReportIdList;
    }
}
