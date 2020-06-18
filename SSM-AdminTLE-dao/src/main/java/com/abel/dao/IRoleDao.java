package com.abel.dao;

import com.abel.domain.Permission;
import com.abel.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    /***
     * 根据用户id查询出所有对应的角色
     * javaType 如果是 one = @One   就对应的javaType = (domain).class  [javaType = Product.class]
     *          如果是 many = @Many 就对应  javaType = java.util.List.class
     * @param roleId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.abel.dao.IPermissionDao.findPermissionByRoleId")),
            @Result(property = "roleName",column = "roleName"),
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(id,roleName,roleDesc) values(replace(uuid(),'-',''),#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    /**
     * 为角色赋予权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(roleId ,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
