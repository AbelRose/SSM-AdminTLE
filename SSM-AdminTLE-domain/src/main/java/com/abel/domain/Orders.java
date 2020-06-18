package com.abel.domain;

import com.abel.ssm.utils.DateUtils;
import org.springframework.util.unit.DataUnit;

import java.util.Date;
import java.util.List;

public class Orders {

    private String id;
    private String orderNum;
    private Date orderTime;
    private String orderTimeStr; //注意这个成员变量 是为了以后能够将Date类型在页面上以字符串的形式显示出来
    private int orderStatus;
    private String orderStatusStr;
    private int peopleCount;
    private Product product;
    private List<Traveller> travellers;
    private Member member;
    private Integer payType;
    private String payTypeStr;
    private String orderDesc;


    public String getOrderStatusStr() {
        //解析状态码
        if (orderStatus == 0){
            orderStatusStr ="未支付";
        }else if(orderStatus == 1){
            orderStatusStr = "已支付";
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {

        if(orderTime!=null){
            //调用Utils包下的工具类
            orderTimeStr = DateUtils.date2String(orderTime, "yyyy-MM-dd HH:mm");  //使用DateUtils工具类进行转换 第二个参数patt是格式
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        //对支付方式进行解析
        if (payType == 0){
            payTypeStr = "支付宝";
        }else if(payType == 1){
            payTypeStr = "微信";
        }else if(payType == 2){
            payTypeStr = "其他";
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}