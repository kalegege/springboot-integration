package com.wasu.springboot.integration.controller;


import com.wasu.springboot.integration.entity.system.Message;
import com.wasu.springboot.integration.utils.JsonResult;
import com.wasu.springboot.integration.utils.JsonResultUtils;
import com.wasu.springboot.integration.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/checkcenter")
public class WebSocketController {

//    @Autowired
//    private WebSocketServer webSocketServer;

    //页面请求
    @RequestMapping("/socket/{cid}")
    public String socket(ModelMap modelMap,@PathVariable String cid) {
        modelMap.addAttribute("cid",cid);
        return "websocket/chat";
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public JsonResult pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResultUtils.error(cid+"#"+e.getMessage());
        }
        return JsonResultUtils.success(cid);
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/pushNew/{cid}")
    public JsonResult pushNewToWeb(@PathVariable String cid, String message,String from) {
        try {
            WebSocketServer.sendInfo(new Message(from,cid,message));
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResultUtils.error(cid+"#"+e.getMessage());
        }
        return JsonResultUtils.success(cid);
    }

}
