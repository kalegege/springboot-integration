package com.wasu.springboot.integration.common.config;

import com.wasu.springboot.integration.job.filter.JobFilter;
import com.wasu.springboot.integration.utils.IpUtils;
import com.wasu.springboot.integration.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class BatchMachineJobFilter implements JobFilter {
    @Override
    public boolean preHandler() {
        String ips = StringUtils.substringBetween(IpUtils.getIpStr(), "[", "]");
        ips = StringUtils.deleteWhitespace(ips);
        String[] arrIps = StringUtils.split(ips, ".");
        for (String ip : arrIps) {
            boolean allow = allow(ip);
            if (allow) {
                return true;
            }
        }
        return false;
    }

    private boolean allow(String ip) {
        if (StringUtils.isBlank(ip)) return false;

        return true;
    }

    @Override
    public void afterHandler() {

    }
}
