package com.ren.controller;

import com.ren.service.ShowLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * 注意在使用websocket的时候，一定要向容器中注入一个bean：ServerEndpointExporter
 */

@ServerEndpoint("/showLog/{id}")
@Controller
public class ShowLog {

    /**
     * 这里为什么直接注入不了，需要用这种方式呢：因为websocket是多实例的，所以需要静态化
     */
    private static ShowLogService logService;

    @Autowired
    public void init(ShowLogService logService) {
        ShowLog.logService = logService;
    }

    public static Logger logger = LoggerFactory.getLogger(ShowLog.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("id") Long id) {
        logger.info("请求的日志文件id为：{}", id);
        session.getUserProperties().put("fileId", id);
        logService.readFile(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 开始通信
    }

    @OnClose
    public void onClose() {

    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
        logger.error(e.getMessage());
    }

}
