package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/11/4 20:54
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
public class HttpRequestFilterImp implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("nio", "liuxin");
    }
}
