package com.abel.service;

import com.abel.domain.Orders;

import java.util.List;

/**
 * IOrdersService 要现有接口 再有OrdersServiceImpl implements IOrdersService 去实现接口中的方法
 * 具体原因见GitHub总结 Grocery->Java->Java语法
 * (总结的很详细)
 */
public interface IOrdersService {
    List<Orders> findAll(int page ,int size) throws Exception;
    Orders findById(String ordersId) throws Exception;
}
