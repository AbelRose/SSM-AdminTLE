package com.abel.ssm.controller;


import com.abel.domain.Role;
import com.abel.domain.UserInfo;
import com.abel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PreDestroy;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("/addRoleToUser.do") // OR addRoleToUser.do 还是 addRoleToUser ??
    @PreAuthorize("authentication.principal.userName == 'sherlock'") //只有sherlock用户才能执行这个操作 这个语言是SPEL表达式
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do"; //添加就可以显示了
    }

    /**
     * 查询用户 以及用户可以添加的角色!!!
     * @param userid
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserByIdAndAllRole.do") 
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userid) throws Exception{
        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户
        UserInfo userInfo = userService.findById(userid);
        //根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userid);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 查询指定ID的用户
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 查找用户
     * @return
     * @throws Exception
     */
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
}
