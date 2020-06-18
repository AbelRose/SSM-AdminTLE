package com.abel.dao;

import com.abel.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    @Select("select * from product") 
    //从product表里面查出所有的商品
    List<Product> findAll() throws Exception;

    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(replace(uuid(), '-', ''),#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    //注意在 MySQL中的id是不能自动增长的 需要手动添加 replace(uuid(), '-', '') 因为不是在页面中取到的数据 所以不需要# 
    void save(Product product);

    @Select("select * from product where id = #{id}")
    //根据ID查找
    public Product findById(String id) throws Exception;
}
