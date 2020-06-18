package com.abel.ssm.controller;

import com.abel.domain.Permission;
import com.abel.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 保存权限
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     * 查找所有
     * @return
     * @throws Exception
     */
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
    }
}
