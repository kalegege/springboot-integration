package com.wasu.springboot.integration.base;

import com.wasu.springboot.integration.base.page.PageParam;
import com.wasu.springboot.integration.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    public BaseController() {
    }

    private int getPageNum(HttpServletRequest request){
        String pageNumStr=request.getParameter("pageNum");
        int pageNum = 1;
        if(StringUtils.isNotBlank(pageNumStr)){
            pageNum=Integer.valueOf(pageNumStr);
        }

        if(pageNum < 1){
            pageNum = 1;
        }
        return pageNum;
    }

    private int getNumPerPage(HttpServletRequest request){
        String numPerPageStr=request.getParameter("numPerPage");
        int numPerPage=10;
        if(StringUtils.isNotBlank(numPerPageStr)){
            numPerPage=Integer.parseInt(numPerPageStr);
        }
        return numPerPage;
    }

    public PageParam getPageParam(HttpServletRequest request){
        return new PageParam(this.getPageNum(request),this.getNumPerPage(request));
    }
}
