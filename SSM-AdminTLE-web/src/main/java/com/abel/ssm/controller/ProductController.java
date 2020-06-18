package com.abel.ssm.controller;

import com.abel.domain.Product;
import com.abel.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询全部产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN") //只有ADMIN的权限的人 才可以访问   [jsr250 的时候可以省略前缀]
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList",ps); //"productList" 对应<c:forEach items="${productList}" var="product">
        mv.setViewName("product-list"); //返回的页面
        return mv;
    }

    /**
     * 产品添加
     * @param product
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do"; //这个是SpringMVC 里面@RequestMapping的设置 返回重定向的页面
    }
}
