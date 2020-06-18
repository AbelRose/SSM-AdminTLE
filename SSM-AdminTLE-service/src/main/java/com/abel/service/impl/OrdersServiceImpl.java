package com.abel.service.impl;

import com.abel.dao.IOrdersDao;
import com.abel.domain.Orders;
import com.abel.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 在Impl里面写具体的类方法
 */
@Service  //一定要声明是一个Service 
@Transactional  // 事务处理
public class OrdersServiceImpl implements IOrdersService {

    @Autowired  //在Service层注入Dao
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page ,int size) throws Exception {
        PageHelper.startPage(page,size); //从1开始每页显示4条数据 写在findAll之前
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
