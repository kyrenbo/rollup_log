package com.ren.controller;

import com.alibaba.fastjson.JSONObject;
import com.ren.model.FileRecord;
import com.ren.model.LogResult;
import com.ren.service.ShowLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LoggerListController {

    @Autowired
    private ShowLogService logService;

    @GetMapping("/list")
    @ResponseBody
    public LogResult<List<FileRecord>> getList() {
        return logService.fileList();
    }

    @PostMapping("/")
    public void add(JSONObject params) {

    }
}
