package com.fatsnake.multidatasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/12/2 20:40
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

    static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();
    @Override
    protected Object determineCurrentLookupKey() {
        String key = THREAD_LOCAL.get();
        return key == null ? "master" : key;
    }


    public static void setMater() {
        THREAD_LOCAL.set("master");
        System.out.println("设置为主库");
    }
    public static void setSlave() {
        THREAD_LOCAL.set("Slave");
        System.out.println("设置为主库");
    }
}