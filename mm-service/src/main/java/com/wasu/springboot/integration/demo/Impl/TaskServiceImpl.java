package com.wasu.springboot.integration.demo.Impl;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.wasu.springboot.integration.demo.TaskService;
import com.wasu.springboot.integration.entity.Task.TaskDO;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TaskServiceImpl implements TaskService {

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
        System.out.print("总数:"+integerList.size());
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
        System.out.print("剩下的是:"+integerList.get(0));
    }

    /**
     * 题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。
     */
    @Override
    public void calculate_1() {
        Random r=new Random();
        int[] ints=new int[r.nextInt(50)];
        for(int i=0 ;i < ints.length;i++){
            ints[i]=r.nextInt(50);
        }
        for (int i = 0; i < ints.length -1;i++)
        {
            System.out.print(ints[i] + ",");
        }
        System.out.println(ints[ints.length -1]);
        int big=ints[0];
        int small=ints[0];
        int bigIndex=0;
        int smallIndex=0;
        for(int i=1;i<ints.length;i++){
            if(ints[i] > big){
                big=ints[i];
                bigIndex=i;
            }
            if(ints[i] < small){
                small=ints[i];
                smallIndex=i;
            }
        }

        if(bigIndex != 0){
            //交换0 和bigIndex
            int tmp = ints[0];
            ints[0] = ints[bigIndex];
            ints[bigIndex]=tmp;
        }
        if(smallIndex != ints.length - 1){
            //交换 smallIndex 和 ints.length - 1
            int tmp = ints[smallIndex];
            ints[smallIndex] = ints[ints.length - 1];
            ints[ints.length - 1]=tmp;
        }
        for (int i = 0; i < ints.length - 1;i++)
        {
            System.out.print(ints[i] + ",");
        }
        System.out.println(ints[ints.length -1]);

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

    public static void main(String[] args) {
        TaskService t = new TaskServiceImpl();
//        t.formateDept();
        t.calculate_1();
//        calculate1();
    }
}