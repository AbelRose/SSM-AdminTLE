package com.abel.dao;

import com.abel.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    /**
     * Permission permission 根据domain可知 permission 有两个参数id permissionName url
     * @param permission
     * @throws Exception
     */
    @Insert("insert into permission(id,permissionName,url) values(replace(uuid(),'-',''),#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
