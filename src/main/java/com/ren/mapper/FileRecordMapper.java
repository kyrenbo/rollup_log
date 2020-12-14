package com.ren.mapper;

import com.ren.model.FileRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FileRecordMapper {

    @Select({
            "select id,create_time,update_time,title,host,port,user,password,remark,deleted,file_path from file_record ",
            "where id = #{id} ",
            "order by id desc"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "host", property = "host", jdbcType = JdbcType.VARCHAR),
            @Result(column = "port", property = "port", jdbcType = JdbcType.INTEGER),
            @Result(column = "user", property = "user", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_path", property = "filePath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "deleted", property = "deleted", jdbcType = JdbcType.TINYINT)
    })
    FileRecord queryOne(Long id);

    @Select({
            "select id,create_time,update_time,title,host,port,user,password,remark,deleted,file_path from file_record ",
            "order by id desc"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "host", property = "host", jdbcType = JdbcType.VARCHAR),
            @Result(column = "port", property = "port", jdbcType = JdbcType.INTEGER),
            @Result(column = "user", property = "user", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_path", property = "filePath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "deleted", property = "deleted", jdbcType = JdbcType.TINYINT)
    })
    List<FileRecord> queryList();

    @Insert({
            "insert into file_record(title, host, port, user, password, remark, file_path, deleted) ",
            "values(#{title}, #{host}, #{port}, #{user}, #{password}, #{remark}, #{filePath}, 0)"
    })
    void insert(FileRecord record);

    @Update({
            "update file_record set",
            "title = #{title},",
            "host = #{host},",
            "port = #{port},",
            "user = #{user},",
            "password = #{password},",
            "remark = #{remark},",
            "deleted = #{deleted} ",
            "where id = #{id}"
    })
    void update(FileRecord record);
}
