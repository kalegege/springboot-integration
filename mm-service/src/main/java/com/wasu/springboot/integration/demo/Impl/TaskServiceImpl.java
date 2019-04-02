package com.wasu.springboot.integration.demo.Impl;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.wasu.springboot.integration.demo.TaskService;
import com.wasu.springboot.integration.entity.Task.TaskDO;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    @Override
    public void formateDept() {
        List<TaskDO> nodes=new ArrayList<>();
        List<Term> termList = StandardTokenizer.segment("商品和服务");
        System.out.println(termList);
        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
// 注意观察下面两个“希望”的词性、两个“晚霞”的词性
        System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels());
        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
    }

    public static void main(String[] args){
        TaskService t=new TaskServiceImpl();
        t.formateDept();
    }
}
