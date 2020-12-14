package com.ren.service.impl;

import com.ren.mapper.FileRecordMapper;
import com.ren.model.FileRecord;
import com.ren.model.LogResult;
import com.ren.service.ShowLogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ShowLogServiceImpl implements ShowLogService {

    private Logger logger = LoggerFactory.getLogger(ShowLogServiceImpl.class);

    private String localhost = "localhost";
    private String localIp = "127.0.0.1";

    private String endFlag = "==== end ====";

    @Resource
    private FileRecordMapper mapper;

    @Override
    public void readFile(Session session) {
        try {
            Long fileId = (Long) session.getUserProperties().get("fileId");
            FileRecord fileRecord = mapper.queryOne(fileId);
            // 判断是本地文件还是远程文件
            if(fileRecord != null && fileRecord.getId() != null) {
                String host = fileRecord.getHost();
                if(localhost.equals(host) || localIp.equals(host)) {
                    // 本地
                    readLocal(session, fileRecord);
                }else {
                    // 远程
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public LogResult<List<FileRecord>> fileList() {
        List<FileRecord> fileRecords = mapper.queryList();
        LogResult<List<FileRecord>> result = LogResult.success(fileRecords, "查询成功！！");
        System.out.println("fileList");
        return result;
    }

    private void readLocal(Session session, FileRecord record) {
        try (BufferedReader reader = new BufferedReader(new FileReader(record.getFilePath()))){
            String line;
            while (true) {
                line = reader.readLine();
                // 发送消息
                if(StringUtils.isNotBlank(line)) {
                    session.getBasicRemote().sendText(line);
                }
                if(endFlag.equals(line)) {
                    // 结束
                    return;
                }
                if(line == null) {
                    // 如果读到的当前行为空，则延时2000
                    TimeUnit.SECONDS.sleep(2);
                }
            }
        }catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void readRemote(Session session, FileRecord record) {

    }
}
