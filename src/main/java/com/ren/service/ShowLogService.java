package com.ren.service;

import com.ren.model.FileRecord;
import com.ren.model.LogResult;

import javax.websocket.Session;
import java.util.List;

public interface ShowLogService {

    void readFile(Session session);

    LogResult<List<FileRecord>> fileList();

}
