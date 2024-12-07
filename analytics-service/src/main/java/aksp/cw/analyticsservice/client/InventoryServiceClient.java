package aksp.cw.analyticsservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryServiceClient {

    private final RestTemplate restTemplate;

    public InventoryServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getProductById(Long productId) {
        String url = "http://localhost:8081/api/products/" + productId;
        return restTemplate.getForObject(url, Object.class);
    }
}
