package org.shopping_kart.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {


    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder){

        return routeLocatorBuilder
                .routes()
                .route(fn -> fn.path("/get")
                        .filters(f-> f.addRequestHeader("MyMeader","12345"))
                        .uri("http://httpbin.org:80"))
                    .route(
                            p -> p.path("/order-service/**")
                                    .filters(f-> f.stripPrefix(1))
                                    .uri("lb://order-service")
                    )
                .build();
    }
}
