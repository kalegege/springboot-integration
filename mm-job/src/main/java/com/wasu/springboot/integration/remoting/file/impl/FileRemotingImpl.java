package com.wasu.springboot.integration.remoting.file.impl;

import com.wasu.springboot.integration.remoting.file.FileRemoting;
import org.springframework.stereotype.Component;

@Component
public class FileRemotingImpl implements FileRemoting {
    @Override
    public Long getSequence() {
        return null;
    }

    @Override
    public void setSequence(long l) {

    }
}
