package com.abel.dao;

import com.abel.domain.Role;
import com.abel.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface IUserDao {

    /**
     * 根据用户名查询(包含角色信息)
     * findRoleByUserId 返回的是一个List<> 所以用@Many 
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.abel.dao.IRoleDao.findRoleByUserId"))

    })
    public UserInfo findByUsername (String username) throws Exception;

    /**
     * 查询出所有信息
     * @return
     * @throws Exception
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /**
     * 保存用户
     * @param userInfo
     * @throws Exception
     */
    @Insert("insert into users(id,email,username,password,phoneNum,status) value(replace(uuid(), '-', ''),#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据id查询用户(包括角色信息)
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.abel.dao.IRoleDao.findRoleByUserId"))

    })
    UserInfo findById(String id) throws Exception;

    /**
     * 查找用户还可以添加的角色
     * @param userid
     * @return
     */
    @Select("select * from role where id not in (select roleId from  users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userid);

    /**
     * 给用户添加角色
     * 注意 @Param 注解
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId); //必须指定名字加上@Param 要不然会报MyBatis的错误
}
