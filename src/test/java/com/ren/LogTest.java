package com.ren;

import com.ren.mapper.FileRecordMapper;
import com.ren.model.FileRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.List;

/** 单测试用如下注解参数，可以保证不启动项目 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LogTest {

    @Autowired
    private FileRecordMapper mapper;

    @Test
    public void addFile() {
        FileRecord fileRecord = new FileRecord();
        fileRecord.setHost("localhost");
        fileRecord.setFilePath("/Users/renbo/Desktop/test.log");
        fileRecord.setPassword("root");
        fileRecord.setUser("root");
        // fileRecord.setPort(22);
        fileRecord.setTitle(new String("本地日志文件读取".getBytes(), StandardCharsets.UTF_8));
        fileRecord.setRemark(new String("本地日志文件读取".getBytes(), StandardCharsets.UTF_8));

        mapper.insert(fileRecord);
    }

    @Test
    public void queryRecord() {
        List<FileRecord> fileRecords = mapper.queryList();
        System.out.println(fileRecords);
    }


}
