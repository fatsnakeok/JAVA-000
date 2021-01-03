package com.fatsnake.cache.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2021/1/3 10:06
 * Copyright (c) 2021, zaodao All Rights Reserved.
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -3946734305303957850L;

    private Long id;

    private Long userId;

    private Long goodsId;

    private String context;

}
