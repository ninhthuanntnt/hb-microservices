package com.ntnt.highblog.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Component
public class SetHeaderByCookieFilter
    extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(final Object config) {
        return (exchange, chain) -> {
            HttpCookie httpCookie  = exchange.getRequest().getCookies().getFirst("accessToken");
            HttpHeaders headers = exchange.getResponse().getHeaders();

            if (httpCookie != null) {
                exchange.getRequest()
                        .mutate()
                        .header("Authorization", "Bearer " + httpCookie.getValue());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if(!ObjectUtils.isEmpty(headers.get(HttpHeaders.UPGRADE.toLowerCase()))) {
                    headers.set(HttpHeaders.UPGRADE, headers.get(HttpHeaders.UPGRADE.toLowerCase()).get(0));
                }

                if(!ObjectUtils.isEmpty(headers.get("sec-websocket-accept"))) {
                    headers.set("Sec-Websocket-Accept", headers.get("sec-websocket-accept").get(0));
                }
            }));
        };
    }
}
