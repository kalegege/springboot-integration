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
}
