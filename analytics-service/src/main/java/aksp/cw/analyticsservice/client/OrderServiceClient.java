package aksp.cw.analyticsservice.client;

import aksp.cw.analyticsservice.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class OrderServiceClient {
    private final RestTemplate restTemplate;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    public OrderServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Order> getAllOrders() {
        String query = """
               {
                   "query": "{ orders { id productId quantity status } }"
               }
           """;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(query, headers);

            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    orderServiceUrl,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<>() {}
            );

            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("data")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> orders = (List<Map<String, Object>>) data.get("orders");

                return orders.stream().map(order -> {
                    Order o = new Order();
                    o.setId(Long.valueOf(order.get("id").toString()));
                    o.setProductId(Long.valueOf(order.get("productId").toString()));
                    o.setQuantity(Integer.valueOf(order.get("quantity").toString()));
                    o.setStatus(order.get("status").toString());
                    return o;
                }).toList();
            }
            return List.of();
        } catch (Exception ex) {
            System.err.println("Error while calling order-service: " + ex.getMessage());
            return List.of();
        }
    }
}
