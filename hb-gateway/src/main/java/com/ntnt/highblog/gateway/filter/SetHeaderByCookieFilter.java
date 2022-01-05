package com.ntnt.highblog.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;

@Component
public class SetHeaderByCookieFilter
    extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(final Object config) {
        return (exchange, chain) -> {
            HttpCookie httpCookie  = exchange.getRequest().getCookies().getFirst("accessToken");

            if (httpCookie != null) {
                exchange.getRequest()
                        .mutate()
                        .header("Authorization", "Bearer " + httpCookie.getValue());
            }

            return chain.filter(exchange);
        };
    }
}
