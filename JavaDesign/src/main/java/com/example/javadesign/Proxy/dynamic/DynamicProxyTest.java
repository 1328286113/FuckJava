package com.example.javadesign.Proxy.dynamic;

import com.example.javadesign.Proxy.staticstate.BuyHouse;
import com.example.javadesign.Proxy.staticstate.BuyHouseImpl;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        //new Class[]{} 里面填的是需要被代理的类
        BuyHouse proxyBuyHouse = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(), new
                Class[]{BuyHouse.class}, new DynamicProxyHandler(buyHouse));
        proxyBuyHouse.buyHosue();
    }
}
