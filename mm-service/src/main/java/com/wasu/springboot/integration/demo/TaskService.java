package com.wasu.springboot.integration.demo;

public interface TaskService {
    void formateDept();

    /**
     * 题目：有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
     */
    void calculate();
}
