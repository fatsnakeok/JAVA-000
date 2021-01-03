package com.fatsnake.cache.web;

import com.alibaba.fastjson.JSONObject;
import com.fatsnake.cache.config.RedisRepository;
import com.fatsnake.cache.domain.Order;
import com.fatsnake.cache.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/3 08:16
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */
@RestController
public class RepertoryController {


    @Autowired
    private IOrderService orderService;

    @Autowired
    private RedisRepository redisRepository;


    @GetMapping(value = "reduceAmount")
    public String reduceAmount(HttpServletRequest request) {

        return orderService.denialOfService(getRemoteHost(request));
    }


    @GetMapping(value = "order")
    public String order(HttpServletRequest request) {

        Order order = new Order();
        order.setId(System.currentTimeMillis());
        order.setUserId(24008L);
        order.setGoodsId(2222L);
        order.setContext("胖子购买的商品");

        String jsonData = JSONObject.toJSONString(order);

        redisRepository.sendMessage("order", jsonData);
        redisRepository.sendMessage("repertory", jsonData);

        return "ok";
    }


    public static String getRemoteHost(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }




}
