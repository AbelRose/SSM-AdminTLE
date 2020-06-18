package com.abel.service.impl;

import com.abel.dao.IUserDao;
import com.abel.domain.Role;
import com.abel.domain.UserInfo;
import com.abel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userid) throws Exception {
        return userDao.findOtherRoles(userid);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密
        userInfo.setPassword( bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));

//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),userInfo.getStatus() == 0 ? false :true,true,true,true,getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0 ? false :true,true,true,true,getAuthority(userInfo.getRoles())); //已经加秒了没必要 noop(使用明文加密)了
        return user;
    }

    //集合返回的是权限的描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role :roles){
//            list.add(new SimpleGrantedAuthority("ROLE_ADMIN" ));
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }
}
