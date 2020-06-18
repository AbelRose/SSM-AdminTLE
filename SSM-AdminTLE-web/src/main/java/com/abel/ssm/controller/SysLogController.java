package com.abel.ssm.controller;

import com.abel.domain.SysLog;
import com.abel.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/sysLog") //一般像sysLog这种路径到aside.jsp里面去找
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    /**
     * 查找所有
     * @return
     * @throws Exception
     */
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll();
        mv.addObject("sysLogs",sysLogList);
        mv.setViewName("syslog-list"); 
        return mv;
    }
}
