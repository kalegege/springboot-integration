package com.wasu.springboot.integration.task;

import com.wasu.springboot.integration.entity.system.BaseDataRes;
import com.wasu.springboot.integration.job.api.JobContext;
import com.wasu.springboot.integration.job.api.SimpleJob;
import com.wasu.springboot.integration.remoting.file.FileRemoting;
import com.wasu.springboot.integration.utils.HttpClientUtils;
import com.wasu.springboot.integration.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Configurable
@EnableScheduling
public class CommonJsTask implements SimpleJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonJsTask.class);

    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    private static final String LOGIN_URL="http://192.168.1.153:18082/treasure/user/login";
    @Autowired
    private FileRemoting fileRemoting;

    private static final String createJsPath = "";

    @Override
    public void execute(JobContext jobContext) throws InterruptedException {
        LOGGER.info("start---------------");
//        System.out.println(fileRemoting.test().getName());
        ;
//        Thread.sleep(1);
//        List<String> targetList = Arrays.asList("1", "3", "2");
//        System.out.println(fileRemoting.pushMessage(targetList));
        Map<String,Object> params=new HashMap<>();
        params.put("loginMobile","songjunjing");
        params.put("password","123456");
        Map<String,String> header=new HashMap<>();
        header.put("Content-Type","application/json;charset=UTF-8");
        header.put("dataType","json");
//        String postForJson = HttpClientUtils.postForJson(LOGIN_URL,params,"utf-8", header);
//        BaseDataRes baseDataRes = JSONUtils.parseObject(postForJson, BaseDataRes.class);
        for (int j = 0; j < 10; j++) {
            Thread tmp = new Thread() {
                @Override
                public void run() {
                    Map<String, String> param = new HashMap<>();
                    param.put("token", "7def18c9084f4a1d9cc67b5a9dda7753");
                    int i = 1000;
                    while (i-- > 0) {
                        String result = HttpClientUtils.postForJson("http://192.168.1.153:18082/treasure/supplier/ability/list", null, "utf-8", param);
                        System.out.println(Thread.currentThread().getName() + "第" + i + "次返回值：---" + result);
                    }
                }
            };
            tmp.setName("线程" + j);
            service.execute(tmp);
        }


        LOGGER.info("end-----------------");
    }

    @Override
    public String getJobname() {
        return "commonJsTask";
    }
}
