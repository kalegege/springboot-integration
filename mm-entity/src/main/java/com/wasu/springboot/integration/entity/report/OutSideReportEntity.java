package com.wasu.springboot.integration.entity.report;

import com.wasu.springboot.integration.base.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
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
    private String industryName;

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
}
