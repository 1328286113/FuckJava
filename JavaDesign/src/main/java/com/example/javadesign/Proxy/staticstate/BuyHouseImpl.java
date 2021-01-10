package com.example.javadesign.Proxy.staticstate;

/**
 * 实现服务接口（委托类）
 */
public class BuyHouseImpl implements BuyHouse {
    @Override
    public void buyHosue() {
        System.out.println("I wanna buy a house");
    }
}
