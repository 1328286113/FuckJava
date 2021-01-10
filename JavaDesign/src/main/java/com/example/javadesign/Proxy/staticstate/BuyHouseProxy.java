package com.example.javadesign.Proxy.staticstate;

/**
 * 创建代理类
 */
public class BuyHouseProxy implements BuyHouse {

    private BuyHouse buyHouse;

    public BuyHouseProxy(final BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buyHosue() {
        System.out.println("before buy house");
        buyHouse.buyHosue();
        System.out.println("after buy house");

    }
}
