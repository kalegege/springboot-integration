package com.wasu.springboot.integration.system.remoting;

import com.wasu.springboot.integration.entity.system.UserInfo;

public interface UserRemoting {

    UserInfo getUserInfoByUserId(Long id);
}
