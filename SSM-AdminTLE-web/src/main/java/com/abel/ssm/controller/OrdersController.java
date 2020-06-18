package com.abel.ssm.controller;

import com.abel.domain.Orders;
import com.abel.service.IOrdersService;
import com.abel.service.impl.OrdersServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller  //声明Controller注解
@RequestMapping("/orders")  //声明映射路径
public class OrdersController {

    @Autowired  //注入Service层
    private IOrdersService ordersService;

    //未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//
//        ModelAndView mv = new ModelAndView();
//
//        List<Orders> ordersList = ordersService.findAll();
//
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");
//
//        return mv;
//    }

    /**
     * ModelAndView
     * 
     * 关于ModelAndView的总结在 Grocery-Java-ModelAndView下
     *
     * @param page @{@link RequestMapping} 是在URL上面的参数 默认为1 页面的起始位置 Integer page 应该是Integer 要不然他不是一个对象 要不以后AOP的时候拿不到
     * @param size @{@link RequestMapping} size 是页面上显示的数据的个数
     * @return
     * @throws Exception
     */

    /**
     * 分页查询所有
     */
    @RequestMapping("/findAll.do")  //声明映射方法
    @Secured("ROLE_ADMIN") //必须加上ROLE_ADMIN 不可省略前缀
    public ModelAndView findAll(@RequestParam(name ="page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception { //Integer page 应该是Integer 要不然他不是一个对象 无法传入 此处易出现BUG
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);
        //PageInfo就是一个分页的Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    /**
     * 根据ID查询
     * @param ordersId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
