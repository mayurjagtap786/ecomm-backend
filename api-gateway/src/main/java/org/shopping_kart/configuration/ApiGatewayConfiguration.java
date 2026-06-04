package org.shopping_kart.configuration;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {


    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder){
        System.out.println("getting loaded.....");
        return routeLocatorBuilder
                .routes()
                    .route("custom-order-path",
                            p -> p.path("/ecomm/order/**")
                                    .filters(f-> f.rewritePath("/ecomm/order/(?<segments>.*)","/${segments}")
                                            .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                                   // .filters(f-> f.stripPrefix(1))
                                    .uri("lb://ORDERSERVICE")
                    ).route("custome-inventory-path",
                        p-> p.path("/ecomm/inventory/**")
                        .filters(f-> f.rewritePath("/ecomm/inventory/(?<segments>.*)","/${segments}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://INVENTORYSERVICE"))
                                .build();
    }
}
