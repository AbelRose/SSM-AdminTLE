package com.abel.dao;

import com.abel.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ISysLogDao {

    /**
     * 具体实现是在 LogAop.java 中实现的
     * @param sysLog
     * @throws Exception
     */
    @Insert("insert into syslog(id,visitTime,username,ip,url,executionTime,method) values(replace(uuid(), '-', ''),#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;

    @Select("select * from syslog")
    List<SysLog> findAll();

}
