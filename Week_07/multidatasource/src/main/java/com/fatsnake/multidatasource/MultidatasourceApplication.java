package com.fatsnake.multidatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@Slf4j
public class MultidatasourceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultidatasourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("早道Keeper API微服务启动完成!\n");
    }
}
