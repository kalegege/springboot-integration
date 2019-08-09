package com.wasu.springboot.integration.demo.Impl;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.wasu.springboot.integration.demo.TaskService;
import com.wasu.springboot.integration.entity.Task.TaskDO;
import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskServiceImpl implements TaskService {

    /**
     * 获取指定页面
     */
    @Override
    public void getPage() {

    }

    @Override
    public void formateDept() {
        List<TaskDO> nodes = new ArrayList<>();
        List<Term> termList = StandardTokenizer.segment("商品和服务");
        System.out.println(termList);
        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
// 注意观察下面两个“希望”的词性、两个“晚霞”的词性
        System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels());
        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
    }

    /**
     * 题目：有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），
     * 凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
     */
    @Override
    public void calculate() {
        Random r = new Random();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < r.nextInt(1000); i++) {
            integerList.add(i + 1);
        }
        System.out.print("总数:" + integerList.size());
        int j = 0;
        int l = 1;
        while (integerList.size() != 1) {
            //执行校验逻辑
            if (l == 3) {
                integerList.remove(j);
                l = 1;
            } else {
                j++;
                l++;
            }
            if (j >= integerList.size()) {
                j = 0;
            }
        }
        System.out.print("剩下的是:" + integerList.get(0));
    }

    /**
     * 题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。
     */
    @Override
    public void calculate_1() {
        Random r = new Random();
        int[] ints = new int[r.nextInt(50)];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = r.nextInt(50);
        }
        for (int i = 0; i < ints.length - 1; i++) {
            System.out.print(ints[i] + ",");
        }
        System.out.println(ints[ints.length - 1]);
        int big = ints[0];
        int small = ints[0];
        int bigIndex = 0;
        int smallIndex = 0;
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] > big) {
                big = ints[i];
                bigIndex = i;
            }
            if (ints[i] < small) {
                small = ints[i];
                smallIndex = i;
            }
        }

        if (bigIndex != 0) {
            //交换0 和bigIndex
            int tmp = ints[0];
            ints[0] = ints[bigIndex];
            ints[bigIndex] = tmp;
        }
        if (smallIndex != ints.length - 1) {
            //交换 smallIndex 和 ints.length - 1
            int tmp = ints[smallIndex];
            ints[smallIndex] = ints[ints.length - 1];
            ints[ints.length - 1] = tmp;
        }
        for (int i = 0; i < ints.length - 1; i++) {
            System.out.print(ints[i] + ",");
        }
        System.out.println(ints[ints.length - 1]);

    }

    /**
     * 题目：有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
     */
    @Override
    public void calculate_2() {
        int count = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 1; k < 5; k++) {
                    if (i != j && j != k && i != k) {
                        count++;
                        System.out.println("数字就是:" + (i * 100 + j * 10 + k));
                    }
                }
            }
        }
        System.out.println("一共有:" + count + "个");

    }

    @Override
    public void receiveMail() {

    }

    /**
     * 计算123456789 = 100
     * 0 无符号 1 加号 2 减号
     *
     */
    @Override
    public void calculate_100() {
        int[] sign = new int[15];

        while (sign[9] == 0) {
            sign[0] = 1;
            sign[1] += 1;
            int sum = 0;
            int delta = 0;
            boolean isAdd = true;
            for (int j = 1; j < 9; j++) {
                sign[j + 1] += sign[j] / 3;
                sign[j] %=3;
            }
            for (int i = 1; i < 9; i++) {
                if (sign[i] == 0) {
                    delta = 10 * (i + delta);
                } else if (sign[i] == 1) {
                    if (isAdd) {
                        sum += delta;
                    } else {
                        sum -= delta;
                    }
                    delta = 10 * i;
                } else if (sign[i] == 2) {
                    if (isAdd) {
                        sum += delta;
                    } else {
                        sum -= delta;
                    }
                    sum -= i;
                    isAdd = false;
                }
            }

            if (sign[8] == 1) {
                sum += 9;
            } else if (sign[8] == 2) {
                sum -= 9;
            } else if (sign[8] == 0) {
                if (isAdd) {
                    sum += delta + 9;
                } else {
                    sum -= delta + 9;
                }
            }
            if (sum == 100) {
                for (int i = 0; i <= 9; i++) {
                    System.out.print(i);
                    if (sign[i] == 1) {
                        System.out.print("+");
                    } else if (sign[i] == 2) {
                        System.out.print("-");
                    }
                }
                System.out.print("9=100");
            }
        }
    }

    private static void calculate1() {
        System.out.print("请输入一个整数：");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.close();
        //定义数组变量标识某人是否还在圈内
        boolean[] isIn = new boolean[n];
        for (int i = 0; i < isIn.length; i++) {
            isIn[i] = true;
        }
        int inCount = n;//定义圈内人数
        int countNum = 0;//定义报数
        int index = 0;//定义索引
        while (inCount > 1) {
            //最后一人时退出循环
            if (isIn[index]) {//判断下一个人是否在圈内
                countNum++;//在的话报数
                if (countNum == 3) {//如果这个数等于3
                    isIn[index] = false;//把他定义为出圈
                    countNum = 0;//报数清零，下一个好从1开始
                    inCount--;//圈内人数减一
                }
            }
            index++;//下一人的位置索引值
            if (index == n) {//当索引到最后之后再从头开始
                index = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (isIn[i]) {//最后只有一个符合条件的
                System.out.println("留下的是：" + (i + 1));
            }
        }
    }

    public static void main(String[] args) throws Exception {

        TaskService taskService = new TaskServiceImpl();
        taskService.calculate_100();

//        ExecutorService exes = Executors.newFixedThreadPool(2);
//        exes.execute(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                }
//            }
//        });
//        exes.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("do b:");
//            }
//        });
//        int threadCount = ((ThreadPoolExecutor) exes).getActiveCount();
//        System.out.println("active:" + threadCount);
//        Thread.sleep(500);
//        System.out.println("active:" + threadCount);
//
//
//        Map<String, String> mapType = JSON.parseObject("{\"name\":\"zhangsan\",\"age\":4,\"address\":\"wangwu\",\"tel\":\"maliu\"}", Map.class);
//        System.out.println("这个是用JSON类,指定解析类型，来解析JSON字符串!!!");
//        for (Object obj : mapType.keySet()) {
//            System.out.println("key为：" + obj + "值为：" + mapType.get(obj).toString());
//        }

    }
}
