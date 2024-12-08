//package aksp.cw.apigateway.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RouteConfig {
//
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("user-service", r -> r.path("/api/users/**")
//                        .uri("http://localhost:8084"))
//
//                .route("order-service", r -> r.path("/api/orders/**")
//                        .filters(f -> f.rewritePath("/api/orders", "/graphql"))
//                        .uri("http://localhost:8082"))
//
//                .route("delivery-service", r -> r.path("/api/deliveries/**")
//                        .uri("http://localhost:8085"))
//
//                .route("inventory-service", r -> r.path("/api/products/**")
//                        .uri("http://localhost:8081"))
//
//                .route("analytics-service", r -> r.path("/api/analytics/**")
//                        .uri("http://localhost:8083"))
//
//                .route("static-files", r -> r.path("/**")
//                        .uri("forward:/"))
//
//                .build();
//    }
//}
package aksp.cw.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Management Service
                .route("user-service", r -> r.path("/api/users/**")
                        .uri("http://localhost:8084"))

                // Order Service (GraphQL)
                .route("order-service", r -> r.path("/api/orders/**")
                        .filters(f -> f.rewritePath("/api/orders", "/graphql"))
                        .uri("http://localhost:8082"))

                // Delivery Service
                .route("delivery-service", r -> r.path("/api/deliveries/**")
                        .uri("http://localhost:8085"))

                // Inventory Service
                .route("inventory-service", r -> r.path("/api/products/**")
                        .uri("http://localhost:8081"))

                // Analytics Service
                .route("analytics-service", r -> r.path("/api/analytics/**")
                        .uri("http://localhost:8083"))

                .build();
    }
}
