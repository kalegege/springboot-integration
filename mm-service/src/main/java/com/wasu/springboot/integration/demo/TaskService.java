package com.wasu.springboot.integration.demo;

public interface TaskService {
    void formateDept();

    /**
     * 题目：有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
     */
    void calculate();

    /**
     * 题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。
     */
    void calculate_1();

    /**
     * 题目：有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
     */
    void calculate_2();

    /**
     * 接收邮件
     */
    void receiveMail();

    /**
     * 计算 123456789 = 100
     */
    void calculate_100();

    /**
     * 获取指定页面
     */
    void getPage();

    /**
     * 画图
     */
    void drawPicture();

    /**
     *  买票系统
     */
    void  buyTicket();
}
