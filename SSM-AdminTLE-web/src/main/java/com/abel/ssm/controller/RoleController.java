package com.abel.ssm.controller;

import com.abel.domain.Permission;
import com.abel.domain.Role;
import com.abel.service.IRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 给添加权限的方法 一般添加什么东西的方法 之后返回的页面一般是findAll.do 
     * @param roleId
     * @param permissionIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId ,@RequestParam(name = "ids",required = true) String[] permissionIds) throws Exception{
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }

    /**
     * 根据roleid查询role 并根据结果查询出可以添加的权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId) throws Exception{
        ModelAndView mv = new ModelAndView();
        //根据roleId查询role
        Role role = roleService.findById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermissons = roleService.findOtherPermissions(roleId);
        mv.addObject("role",role);  //带的数据
        mv.addObject("permissionList",otherPermissons);  //带的数据
        mv.setViewName("role-permission-add"); //跳转的页面
        return mv;
    }

    /**
     * 保存角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }
    
    /**
     * 查找所有角色
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }
}
