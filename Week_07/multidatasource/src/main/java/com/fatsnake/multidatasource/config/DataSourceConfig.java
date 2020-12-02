package com.fatsnake.multidatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/12/2 20:42
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
@Configuration
public class DataSourceConfig {


    /***
     * 注意这里用的 Druid 连接池
     */
    @Bean(name = "dbMaster")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dbMaster() {
//        log.info("master数据源");
        return new DruidDataSource();
    }

    @Bean(name = "dbSlave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource dbSlave() {
//        log.info("slave数据源");
        return new DruidDataSource();
    }

    /***
     * @Primary： 相同的bean中，优先使用用@Primary注解的bean.
     * @Qualifier:： 这个注解则指定某个bean有没有资格进行注入。
     */
    @Primary
    @Bean(name = "dataSourceRouter") // 对应Bean: DataSourceRouter
    public DataSource dataSourceRouter(@Qualifier("dbMaster") DataSource master, @Qualifier("dbSlave") DataSource slave) {
        DataSourceRouter dataSourceRouter = new DataSourceRouter();

        //配置多数据源
        Map<Object, Object> map = new HashMap<>(5);
        map.put("master", master);    // key需要跟ThreadLocal中的值对应
        map.put("slave", slave);

        // master 作为默认数据源
        dataSourceRouter.setDefaultTargetDataSource(master);
        dataSourceRouter.setTargetDataSources(map);
        return dataSourceRouter;
    }

    // 注入动态数据源 DataSourceTransactionManager 用于事务管理(事务回滚只针对同一个数据源)
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("dataSourceRouter") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

