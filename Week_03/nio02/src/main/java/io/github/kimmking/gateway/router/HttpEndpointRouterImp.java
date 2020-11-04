package io.github.kimmking.gateway.router;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Auther: fatsnake
 * @Description":
 * @Date:2020/11/4 21:13
 * Copyright (c) 2020, zaodao All Rights Reserved.
 */
public class HttpEndpointRouterImp implements HttpEndpointRouter {
    @Override
    public String route(List<String> endpoints) {

        int index = new Random().nextInt() % endpoints.size();

        return endpoints.get(index);
    }
}
