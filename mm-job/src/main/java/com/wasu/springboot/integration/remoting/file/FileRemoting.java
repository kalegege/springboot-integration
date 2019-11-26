package com.wasu.springboot.integration.remoting.file;

import com.wasu.springboot.integration.entity.Task.TaskDO;

import java.util.List;

public interface FileRemoting {
    Long getSequence();

    void setSequence(long l);

    String pushMessage(List<String> targetList);

    TaskDO test();
}
