//package aksp.cw.apigateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.*;
//import reactor.core.publisher.Mono;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public SimpleUrlHandlerMapping indexRedirect() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("/**", new IndexRedirectHandler());
//
//        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
//        handlerMapping.setOrder(1);
//        handlerMapping.setUrlMap(map);
//        return handlerMapping;
//    }
//
//    class IndexRedirectHandler implements HandlerFunction<ServerResponse> {
//        @Override
//        public Mono<ServerResponse> handle(ServerRequest request) {
//            return ServerResponse.ok()
//                    .contentType(MediaType.TEXT_HTML)
//                    .body(BodyInserters.fromResource(new ClassPathResource("static/index.html")));
//        }
//    }
//}
